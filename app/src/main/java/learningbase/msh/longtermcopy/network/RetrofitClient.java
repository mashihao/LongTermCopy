package learningbase.msh.longtermcopy.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import learningbase.msh.longtermcopy.BuildConfig;
import learningbase.msh.longtermcopy.ClientMessage;
import learningbase.msh.longtermcopy.Converter.RemoveShellConverter;
import learningbase.msh.longtermcopy.bean.LocalLogger;
import learningbase.msh.longtermcopy.util.ObjectToJson;
import learningbase.msh.longtermcopy.util.StompClientUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Retrofit 提供类
 */

public class RetrofitClient {
    private final int TIME_OUT = 30;
    private final String AUTHORZATION = "Authorization";
    private String GET = "GET";
    private String DELETE = "DELETE";
    private Retrofit retrofit = null;
    private OkHttpClient okHttpClient = null;
    private Gson gson = getGson();

    /**
     * 单例 禁止初始化
     */
    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        return RetrofitClientHolder.retrofitClient;
    }

    /**
     * 获取Retrofit实例
     *
     * @return
     */
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            if (okHttpClient == null) {
                initOkHttp();
            }
            retrofit = new Retrofit.Builder().client(okHttpClient)
                    //配置根地址：在 app.gradle 文件中配置
                    .baseUrl(BuildConfig.SELF_BASE_URL)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(RemoveShellConverter.create())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * 初始化 OKhttp ，并添加过滤器
     */
    private void initOkHttp() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (okHttpClientBuilder.interceptors() != null) {
            okHttpClientBuilder.interceptors().clear();
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                ClientMessage msg = new ClientMessage();
                msg.setName(message);
//                StompClientUtil.send(ObjectToJson.javabeanToJson(msg));
                Log.e("MSH",message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(interceptor);

        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                //发送服务器的包信息
                LocalLogger logger = new LocalLogger();

                //请求
                Request request = chain.request();
                String method = request.method();
                String url = request.url() + "";
//                RequestBody requestBody = request.body();

                //添加请求方式
                logger.setMethod(method);
                //添加链接
                logger.setUrl(url);

                //返回

                Response response = chain.proceed(request);
                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                Charset charset = Charset.forName("UTF-8");
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                String localResponseBody = buffer.clone().readString(charset);

                //返回包添加
                logger.setResponseBody(localResponseBody);

                StompClientUtil.send(ObjectToJson.javabeanToJson(logger));
                return response;
            }
        });

        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        okHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 延迟加载，线程安全（java中class加载时互斥的），也减少了内存消耗
     */
    private static class RetrofitClientHolder {
        public static final RetrofitClient retrofitClient = new RetrofitClient();
    }

    /**
     * 获取 Gson 对象
     *
     * @return
     */
    public Gson getGson() {
        return new GsonBuilder().disableHtmlEscaping().create();
    }

    class NullOnEmptyConverterFactory extends Converter.Factory {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate =
                    retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody value) throws IOException {
                    if (value.contentLength() == 0) return null;
                    return delegate.convert(value);
                }
            };
        }
    }
}


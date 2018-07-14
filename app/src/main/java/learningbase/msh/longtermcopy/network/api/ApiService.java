package learningbase.msh.longtermcopy.network.api;


import java.util.List;

import io.reactivex.Observable;
import learningbase.msh.longtermcopy.Converter.RemoveShell;
import learningbase.msh.longtermcopy.bean.Account;
import learningbase.msh.longtermcopy.bean.Subject;
import learningbase.msh.longtermcopy.network.ResponseRetrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口
 */

public interface ApiService {
    /**
     * 登录 测试
     *
     * @param start
     * @param count
     * @return
     */
    @GET("v2/movie/top250")
    Observable<ResponseRetrofit<List<Subject>>> getDatas(
            @Query("start") int start, @Query("count") int count);

    /**
     * 登录 测试
     *
     * @param username
     * @param password
     * @return
     */
    @GET("login")
    Observable<ResponseRetrofit<Boolean>> login(
            @Query("username") String username, @Query("password") String password);


    @RemoveShell
    @GET("account/list")
    Observable<List<Account>> getAccounts();


    @GET("account/{id}")
    Observable<ResponseRetrofit<Account>> getAccount(@Path("id") String id);



    @POST("account/post")
    Observable<ResponseRetrofit<Account>>post(@Body Account account);

}

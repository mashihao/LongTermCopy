package learningbase.msh.longtermcopy.presenter;

import android.support.annotation.NonNull;

import learningbase.msh.longtermcopy.network.ResponseRetrofit;
import learningbase.msh.longtermcopy.network.RetrofitPresenter;
import learningbase.msh.longtermcopy.network.api.CallBackListener;
import learningbase.msh.longtermcopy.view.LoginView;
import learningbase.msh.longtermcopy.network.api.IRetrofitView;

/**
 * Created by lenovo on 2017/8/8.
 */

public class LoginPresenter extends RetrofitPresenter {
    LoginView iView;
    public LoginPresenter(@NonNull IRetrofitView IRetrofitView) {
        super(IRetrofitView);
        this.iView = (LoginView) IRetrofitView;
    }

    public void login(String username,String password){
        addRequest(getSelfDriverApi().login(username, password), new CallBackListener<ResponseRetrofit<Boolean>>() {
            @Override
            public void onSuccess(ResponseRetrofit<Boolean> result) {
                iView.loginsucc();
            }

            @Override
            public void onFail(int url, ResponseRetrofit result) {
                iView.loginError();
            }
        });
    }

}

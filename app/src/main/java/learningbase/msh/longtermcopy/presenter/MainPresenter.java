package learningbase.msh.longtermcopy.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import learningbase.msh.longtermcopy.bean.Account;
import learningbase.msh.longtermcopy.network.ResponseRetrofit;
import learningbase.msh.longtermcopy.network.RetrofitPresenter;
import learningbase.msh.longtermcopy.network.api.CallBackListener;
import learningbase.msh.longtermcopy.view.MainView;

public class MainPresenter extends RetrofitPresenter {
    private MainView iView;
    private Context mContext;

    public MainPresenter(@NonNull learningbase.msh.longtermcopy.network.api.IRetrofitView IRetrofitView) {
        super(IRetrofitView);
        this.iView = (MainView) IRetrofitView;
        this.mContext = (Context) IRetrofitView;
    }

    public void getData() {
        addRequest(getSelfDriverApi().getAccount("1"), new CallBackListener<ResponseRetrofit<Account>>() {
            @Override
            public void onSuccess(ResponseRetrofit<Account> result) {
                iView.onSucc(result.getResult());
            }

            @Override
            public void onFail(int url, ResponseRetrofit result) {
                iView.onError();
            }
        });
    }

    public void getDatas() {

        addRequest(getSelfDriverApi().getAccounts(), new CallBackListener<List<Account>>() {
            @Override
            public void onSuccess(List<Account> result) {
                iView.onSuccs(result);
            }

            @Override
            public void onFail(int url, ResponseRetrofit result) {
                iView.onError();
            }
        });
    }

    public void post(Account account){
        addRequest(getSelfDriverApi().post(account), new CallBackListener<ResponseRetrofit<Account>>() {
            @Override
            public void onSuccess(ResponseRetrofit<Account> result) {
                iView.showPostSucc(result.getMessage());
            }

            @Override
            public void onFail(int url, ResponseRetrofit result) {

            }
        });
    }
}

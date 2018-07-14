package learningbase.msh.longtermcopy.view;


import learningbase.msh.longtermcopy.network.api.IRetrofitView;

/**
 * Created by lenovo on 2017/8/8.
 */

public interface LoginView extends IRetrofitView {
    void loginsucc();

    void loginError();
}

package learningbase.msh.longtermcopy.newmvp;

/**
 * TODO V层的基类
 * @author msh
 * @time 2017/11/23 22:00
*/

public interface BaseView {

    void showProgress(int type);

    void hideProgress(int type);

    void showErrorView(int type);

    void showEmptyView(boolean show);

    void onError(int type);

}

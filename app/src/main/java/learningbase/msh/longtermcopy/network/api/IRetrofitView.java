package learningbase.msh.longtermcopy.network.api;


import learningbase.msh.longtermcopy.network.RetrofitPresenter;

/**
 * 界面网络回调IView基础接口
 */

public interface IRetrofitView {
  void showRetrofitProgress(int urlType);

  void hideRetrofitProgress(int urlType);

  void setPresenter(RetrofitPresenter presenter);
}

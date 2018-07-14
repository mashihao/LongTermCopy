package learningbase.msh.longtermcopy.network.api;


import learningbase.msh.longtermcopy.network.ResponseRetrofit;

/**
 * 回调的接口
 */

public interface CallBackListener<T> {
  void onSuccess(T result);

  void onFail(int url, ResponseRetrofit result);
}

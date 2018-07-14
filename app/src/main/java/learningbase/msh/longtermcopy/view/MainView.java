package learningbase.msh.longtermcopy.view;


import java.util.List;

import learningbase.msh.longtermcopy.bean.Account;
import learningbase.msh.longtermcopy.network.api.IRetrofitView;

/**
 * 主界面的处理
 */
public interface MainView extends IRetrofitView {
  void showView();

  void onError();

  void onSuccs(List<Account> da);

  void onSucc(Account account);

  void showPostSucc(String msg);
}

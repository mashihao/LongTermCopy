package learningbase.msh.longtermcopy.network;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;

import learningbase.msh.longtermcopy.network.api.IRetrofitView;
import learningbase.msh.longtermcopy.util.DialogUtils;
import learningbase.msh.longtermcopy.util.MyLogger;

/**
 *
 */

public class RetrofitActivity extends AppCompatActivity implements IRetrofitView {

  protected MyLogger logger = MyLogger.getLogger(this.getClass().getSimpleName());
  private RetrofitPresenter presenter;
  private Dialog progress;


  @Override
  public void showRetrofitProgress(int urlType) {
    if (progress == null) {
      progress = DialogUtils.progressDialog(this);

    } else if (!progress.isShowing()) {
      progress.show();
    }
  }

  @Override
  public void hideRetrofitProgress(int urlType) {
    if (progress != null && progress.isShowing()) {
      progress.dismiss();
    }
  }

  @Override
  public void setPresenter(RetrofitPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.unSubscribe(false);
    }
    if (progress != null && progress.isShowing()) {
      progress.dismiss();
    }
  }
}

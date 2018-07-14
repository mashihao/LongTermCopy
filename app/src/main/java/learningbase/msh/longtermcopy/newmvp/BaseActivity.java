package learningbase.msh.longtermcopy.newmvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import learningbase.msh.longtermcopy.util.DialogUtils;

/**
 * TODO 新的基类activity
 * @author msh
 * @time 2017/11/23 22:00
*/

public class BaseActivity<T extends BasePresenter> extends FragmentActivity implements BaseView{

    protected T presenter;
    private Unbinder unbinder;
    private Dialog progressDialog;

    @Nullable
//    @BindView(R.id.empty_view)
    View emptyView;
    @Nullable
//    @BindView(R.id.error_view)
            View errorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setStatusBar();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 设置 应用状态栏
     */
    protected void setStatusBar() {
    }

    @Override
    public void showProgress(int type) {
        if (progressDialog == null){
            progressDialog = DialogUtils.progressDialog(this);
        }
        if (progressDialog == null){
            return;
        }
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress(int type) {
        if (progressDialog == null){
            return;
        }
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showErrorView(int type) {
        if(errorView != null){
            errorView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showEmptyView(boolean show) {
        if(emptyView != null){
            if (show){
                emptyView.setVisibility(View.VISIBLE);
            }else {
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onError(int type) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.unSubscribe();
        }
        if (unbinder != null){
            unbinder.unbind();
        }
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}

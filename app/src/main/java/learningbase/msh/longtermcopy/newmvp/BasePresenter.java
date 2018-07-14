package learningbase.msh.longtermcopy.newmvp;

import io.reactivex.disposables.CompositeDisposable;


/**
 * TODO 新的BasePresenter
 *
 * @author msh
 * @time 2017/11/23 22:00
 */

public class BasePresenter<T extends BaseView> {


    protected T iView;
    /**
     * 管理所有的Disposable
     */
    protected CompositeDisposable compositeSubscription = new CompositeDisposable();

    public BasePresenter(T iView) {
        this.iView = iView;
    }

    /**
     * 取消注册
     */
    public void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.clear();
        }
    }

}

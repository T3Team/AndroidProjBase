package base.android.t3t.netrequestdemo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import base.android.t3t.netrequestdemo.entity.NetDemoBean;
import base.android.t3t.netrequestdemo.http.MNetService;
import base.t3t.companybusinesslib.http.BaseObserver;
import base.t3t.companybusinesslib.http.LoadState;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by dale on 2018/6/19.
 * <p>
 * 基本规制是确保在你的 ViewModels 类中没有任何 android.* 的类导入（android.arch.* 例外）。
 */
public class NetDemoViewModel extends ViewModel {

    // 因为ViewModel独立于Activity和Fragment，所以不能引用任何View，或者任何引用了Context 的对象。
    // 如果ViewModel需要一个Application类型的Context，可以继承于AndroidViewModel类，构造方法中会有一个Application对象。

    public MutableLiveData<LoadState> getLoadState() {
        return mLoadState;
    }

    private MutableLiveData<LoadState> mLoadState = new MutableLiveData<>();

    private LoadState mState = new LoadState();

    public NetDemoViewModel() {
        mNetDemoData = PublishSubject.create();
    }

    public PublishSubject<NetDemoBean> getmNetDemoData() {
        return mNetDemoData;
    }

    private final PublishSubject<NetDemoBean> mNetDemoData;


    public void requestNetDemoData() {

        MNetService.getTestContent().subscribe(new BaseObserver<NetDemoBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                mState.setLoading(true);
                mLoadState.setValue(mState);
            }

            @Override
            public void onComplete() {
                mState.setLoading(false);
                mLoadState.setValue(mState);
            }

            @Override
            public void onSuccess(NetDemoBean netDemoBean) {
                mNetDemoData.onNext(netDemoBean);
            }

            @Override
            public void onFailure(int code, String message) {
            }
        });


    }


    @Override
    protected void onCleared() {
        super.onCleared();
        //mNetDemoData = null;
    }
}

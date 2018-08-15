package base.android.t3t.netrequestdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import base.android.t3t.netrequestdemo.entity.NetDemoBean;
import base.android.t3t.netrequestdemo.http.MNetService;
import base.t3t.baseprojlib.base.BaseViewModel;
import base.t3t.companybusinesslib.http.BaseObserver;
import base.t3t.companybusinesslib.http.LoadState;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by dale on 2018/6/19.
 * <p>
 * 基本规制是确保在你的 ViewModels 类中没有任何 android.* 的类导入（android.arch.* 例外）。
 */
public class NetDemoViewModel extends BaseViewModel {

    public NetDemoViewModel(@NonNull Application application) {
        super(application);
    }

    // 因为ViewModel独立于Activity和Fragment，所以不能引用任何View，或者任何引用了Context 的对象。
    // 如果ViewModel需要一个Application类型的Context，可以继承于AndroidViewModel类，构造方法中会有一个Application对象。

    @Override
    public void loadData() {
        MNetService.getTestContent().subscribe(new BaseObserver<NetDemoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSuccess(NetDemoBean netDemoBean) {
                setUiObservableData(netDemoBean);
            }

            @Override
            public void onFailure(int code, String message) {
            }
        });

    }
}

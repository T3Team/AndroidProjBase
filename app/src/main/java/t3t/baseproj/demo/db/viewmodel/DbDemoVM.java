package t3t.baseproj.demo.db.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import base.t3t.baseprojlib.base.BaseViewModel;
import base.t3t.companybusinesslib.http.BaseObserver;
import io.reactivex.disposables.Disposable;
import t3t.baseproj.demo.db.model.CityList;
import t3t.baseproj.net.MDemoService;

/**
 * Created by dale on 2018/8/14.
 */
public class DbDemoVM extends BaseViewModel {
    public DbDemoVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void loadData() {
    }

    private void getDataByNet(final boolean isFreash) {
        MDemoService.getCityList().subscribe(new BaseObserver<CityList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSuccess(CityList netDemoBean) {
                netDemoBean.setRefreash(isFreash);
                setUiObservableData(netDemoBean);
            }

            @Override
            public void onFailure(int code, String message) {
            }
        });
    }

    public void freashData() {
        getDataByNet(true);
    }

    public void loadMoreData() {
        getDataByNet(false);
    }


}

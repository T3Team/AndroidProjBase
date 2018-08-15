package t3t.baseproj.demo.db.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.List;

import base.t3t.baseprojlib.base.BaseViewModel;
import base.t3t.baseprojlib.utils.DevUtil;
import base.t3t.companybusinesslib.http.BaseObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.rx.CollectionChange;
import t3t.baseproj.demo.db.model.CityList;
import t3t.baseproj.demo.db.model.ListBean;
import t3t.baseproj.net.MDemoService;

/**
 * Created by dale on 2018/8/14.
 */
public class DbDemoVM extends BaseViewModel {
    private Realm realm;
    private boolean isFromCache = false;

    public DbDemoVM(@NonNull Application application) {
        super(application);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void loadData() {
        //首次加载模拟先加载realm db 里面的数据
        final List<ListBean> cities = realm.where(ListBean.class).findAll();
        Observable<CityList> cache = Observable.create(new ObservableOnSubscribe<CityList>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<CityList> e) {
                // 在操作符 concat 中，只有调用 onComplete 之后才会执行下一个 Observable
                if (cities != null && cities.size() > 0) { // 如果缓存数据不为空，则直接读取缓存数据，而不读取网络数据
                    CityList cityList = new CityList();
                    cityList.setRefreash(true);
                    cityList.setList(cities);
                    isFromCache = true;
                    e.onNext(cityList);
                } else {
                    isFromCache = false;
                    e.onComplete();
                }
            }
        });
        Observable<CityList> network = MDemoService.getCityList();
        // 两个 Observable 的泛型应当保持一致
        Observable.concat(cache, network)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityList>() {
                    @Override
                    public void accept(@NonNull final CityList cityList) {
                        if (!isFromCache) {//网络获取
                            if (cityList != null && cityList.getList() != null) {
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.insertOrUpdate(cityList.getList());
                                    }
                                });
                            }
                        }
                        setUiObservableData(cityList);
                    }
                });
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


    @Override
    protected void onCleared() {
        super.onCleared();
        realm.close();
    }
}

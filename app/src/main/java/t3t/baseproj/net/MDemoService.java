package t3t.baseproj.net;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import t3t.baseproj.demo.db.model.CityList;


public class MDemoService {


    public static Observable<CityList> getCityList() {
        return DemoHttpCall.getInstance()
                .getApiService()
                .getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

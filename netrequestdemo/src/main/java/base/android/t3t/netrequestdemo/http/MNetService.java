package base.android.t3t.netrequestdemo.http;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MNetService {

    public static Observable<String> getTestContent() {
        return NetDemoHttpCall.getInstance()
                .getApiService()
                .getTesContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

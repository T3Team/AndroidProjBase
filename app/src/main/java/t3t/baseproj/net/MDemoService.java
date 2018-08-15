package t3t.baseproj.net;


import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MDemoService {

    public static void getDemoLists(final Context context) {
        DemoHttpCall.getInstance()
                .getApiService().getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

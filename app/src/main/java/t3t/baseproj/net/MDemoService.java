package t3t.baseproj.net;


import android.content.Context;

import base.t3t.companybusinesslib.http.BaseObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MDemoService {

    public static void getDemoLists(final Context context) {
         DemoHttpCall.getInstance()
                .getApiService().getList()
                .subscribe(new BaseObserver<String>(context) {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onFailure(int code, String message) {

                    }
                });
    }

}

package base.android.t3t.netrequestdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import base.android.t3t.netrequestdemo.entity.NetDemoBean;
import base.android.t3t.netrequestdemo.http.MNetService;
import base.android.t3t.netrequestdemo.http.NetDemoHttpCall;
import base.t3t.companybusinesslib.http.BaseObserver;
import io.reactivex.disposables.Disposable;


/**
 * Created by dale on 2018/6/19.
 */
public class NetDemoViewModel extends ViewModel {
    private MutableLiveData<NetDemoBean> mNetDemoData = new MutableLiveData<>();

    public LiveData<NetDemoBean> getNetDemoData() {
        return mNetDemoData;
    }

    public void requestNetDemoData(final Context ctx) {
        MNetService.getTestContent().subscribe(new BaseObserver<NetDemoBean>(ctx) {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(ctx,"onSubscribe",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(NetDemoBean netDemoBean) {
                Toast.makeText(ctx,"-----",Toast.LENGTH_LONG).show();
                mNetDemoData.setValue(netDemoBean);
            }

            @Override
            public void onFailure(int code, String message) {
                Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();
            }
        });


    }

}

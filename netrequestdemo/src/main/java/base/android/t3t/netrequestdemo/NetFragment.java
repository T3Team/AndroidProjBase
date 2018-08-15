package base.android.t3t.netrequestdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.concurrent.TimeUnit;

import base.android.t3t.netrequestdemo.entity.NetDemoBean;
import base.android.t3t.netrequestdemo.viewmodel.NetDemoViewModel;
import base.t3t.baseprojlib.utils.DevUtil;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;
import base.t3t.companybusinesslib.http.LoadState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

@Route(path = NetDemoArouterParams.jumpToNetDemoMainragment)
public class NetFragment extends BaseFragment {

    private Button mSentRequest;
    private TextView mNetBackContent, mCntTv;
    private QMUITopBar mTopBar;
    private NetDemoViewModel netDemoViewModel;

    @Override
    protected View onCreateView() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_net_demo, null);
        mSentRequest = layout.findViewById(R.id.sentRequest);
        mTopBar = layout.findViewById(R.id.topbar);
        mNetBackContent = layout.findViewById(R.id.netBackContent);
        mCntTv = layout.findViewById(R.id.cntTv);
        mTopBar.setTitle("网络请求demo");
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mSentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netDemoViewModel.loadData();
            }
        });

        // 当执行onDestory()时， 自动解除订阅
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        DevUtil.v("dale", "Unsubscribing subscription from onCreate()");
                    }
                })
                .compose(this.<Long>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        mCntTv.setText(num + "");
                        DevUtil.v("dale", "Started in onCreate(), running until onDestory(): " + num);
                    }
                });
        return layout;
    }

    private Gson gson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netDemoViewModel = ViewModelProviders.of(this).get(NetDemoViewModel.class);
        gson = new Gson();
        netDemoViewModel.getObservable()
                .compose(this.<NetDemoBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NetDemoBean>() {
                    @Override
                    public void accept(NetDemoBean netDemoBean) throws Exception {
                        if (netDemoBean == null) {
                            return;
                        }
                        String json = gson.toJson(netDemoBean);
                        mNetBackContent.setText(String.format("返回的数据-->%s", json));
                    }
                });
//        netDemoViewModel.getLoadState().observe(this, new Observer<LoadState>() {
//            @Override
//            public void onChanged(@Nullable LoadState loadState) {
//                if (loadState == null) {
//                    return;
//                }
//                if (loadState.isLoading()) {
//                    mSentRequest.setEnabled(false);
//                    mSentRequest.setText("数据加载中");
//                } else {
//                    mSentRequest.setEnabled(true);
//                    mSentRequest.setText("发送请求");
//                }
//            }
//        });
    }

    @Override
    protected boolean canDragBack() {
        return true;
    }


}
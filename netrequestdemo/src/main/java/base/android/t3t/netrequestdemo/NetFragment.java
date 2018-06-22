package base.android.t3t.netrequestdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.QMUITopBar;

import base.android.t3t.netrequestdemo.entity.NetDemoBean;
import base.android.t3t.netrequestdemo.viewmodel.NetDemoViewModel;
import base.t3t.baseprojlib.utils.DevUtil;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;
import base.t3t.companybusinesslib.http.LoadState;

@Route(path = NetDemoArouterParams.jumpToNetDemoMainragment)
public class NetFragment extends BaseFragment {

    private Button mSentRequest;
    private TextView mNetBackContent;
    private QMUITopBar mTopBar;
    private NetDemoViewModel netDemoViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DevUtil.v("dale", "onActivityCreated--");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View onCreateView() {
        DevUtil.v("dale", "onCreateView--");
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_net_demo, null);
        mSentRequest = layout.findViewById(R.id.sentRequest);
        mTopBar = layout.findViewById(R.id.topbar);
        mNetBackContent = layout.findViewById(R.id.netBackContent);
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
                netDemoViewModel.requestNetDemoData();
            }
        });
        return layout;
    }

    private Gson gson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DevUtil.v("dale", "onCreate--");
        netDemoViewModel = ViewModelProviders.of(this).get(NetDemoViewModel.class);
        gson = new Gson();
        netDemoViewModel.getNetDemoData().observe(this, new Observer<NetDemoBean>() {
            @Override
            public void onChanged(@Nullable NetDemoBean netDemoBean) {
                if (netDemoBean == null) {
                    return;
                }
                String json = gson.toJson(netDemoBean);
                mNetBackContent.setText(String.format("返回的数据-->%s", json));
            }
        });
        netDemoViewModel.getLoadState().observe(this, new Observer<LoadState>() {
            @Override
            public void onChanged(@Nullable LoadState loadState) {
                if (loadState == null) {
                    return;
                }
                if(loadState.isLoading()){
                    mSentRequest.setEnabled(false);
                    mSentRequest.setText("数据加载中");
                }else{
                    mSentRequest.setEnabled(true);
                    mSentRequest.setText("发送请求");
                }
            }
        });
    }

    @Override
    protected boolean canDragBack() {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DevUtil.v("dale", "onDestroy--");
    }
}
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

import base.android.t3t.netrequestdemo.entity.NetDemoBean;
import base.android.t3t.netrequestdemo.viewmodel.NetDemoViewModel;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;

@Route(path = NetDemoArouterParams.jumpToNetDemoMainragment)
public class NetFragment extends BaseFragment {

    private Button mSentRequest;
    private TextView mNetBackContent;
    private QMUITopBar mTopBar;
    private NetDemoViewModel netDemoViewModel;

    @Override
    protected View onCreateView() {
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
                netDemoViewModel.requestNetDemoData(getContext());
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
    }

    @Override
    protected boolean canDragBack() {
        return true;
    }
}
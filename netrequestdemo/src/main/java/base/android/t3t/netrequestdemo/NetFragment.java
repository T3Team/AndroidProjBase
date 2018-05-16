package base.android.t3t.netrequestdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.widget.QMUITopBar;

import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;

@Route(path = NetDemoArouterParams.jumpToNetDemoMainFragment)
public class NetFragment extends BaseFragment {

    private Button mSentRequest;
    private TextView mNetBackContent;
    private QMUITopBar mTopBar;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_net_demo, null);
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
        return layout;
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}
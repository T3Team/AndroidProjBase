package base.android.t3t.netrequestdemo;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import base.android.t3t.netrequestdemo.utils.ArouterUtils;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.base.BaseFragmentActivity;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;

@Route(path = NetDemoArouterParams.jumpToNetDemoMain)
public class MainActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.main_net_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = (NetFragment) ArouterUtils.getInstance().getFragment(NetDemoArouterParams.jumpToNetDemoMainragment).navigation();
            if (fragment != null) {
                startFragment(fragment);
            }
        }
    }
}

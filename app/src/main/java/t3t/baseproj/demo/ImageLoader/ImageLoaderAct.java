package t3t.baseproj.demo.ImageLoader;

import android.os.Bundle;

import base.android.t3t.netrequestdemo.NetFragment;
import base.android.t3t.netrequestdemo.utils.ArouterUtils;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.base.BaseFragmentActivity;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;
import t3t.baseproj.R;

/**
 * Created by dale on 2018/7/19.
 */
public class ImageLoaderAct extends BaseFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.app_main_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = (NetFragment) ArouterUtils.getInstance().getFragment(NetDemoArouterParams.jumpToNetDemoMainragment).navigation();
            startFragment(fragment);
        }
    }

}

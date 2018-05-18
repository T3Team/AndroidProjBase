package t3t.baseproj;

import android.os.Bundle;

import base.android.t3t.netrequestdemo.utils.ArouterUtils;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.base.BaseFragmentActivity;
import base.t3t.companybusinesslib.constant.AppArouterParams;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;
import t3t.baseproj.fragment.home.HomeFragment;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.app_main_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = (BaseFragment) ArouterUtils.getInstance().getFragment(AppArouterParams.fragmentHome).navigation();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}

package t3t.baseproj.model;

import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.base.BaseFragmentActivity;

public class ItemDescription {
    public Class<? extends BaseFragment> getmKitDemoClass() {
        return mKitDemoClass;
    }

    public BaseFragmentActivity getmKitDemoActClass() {
        return mKitDemoActClass;
    }

    public String getmKitName() {
        return mKitName;
    }

    private Class<? extends BaseFragment> mKitDemoClass;
    private BaseFragmentActivity mKitDemoActClass;
    private String mKitName;

    public ItemDescription(Class<? extends BaseFragment> kitDemoClass, String kitName) {
        mKitDemoClass = kitDemoClass;
        mKitName = kitName;
    }


    public ItemDescription(BaseFragmentActivity kitDemoActClass, String kitName) {
        mKitDemoActClass = kitDemoActClass;
        mKitName = kitName;
    }

}

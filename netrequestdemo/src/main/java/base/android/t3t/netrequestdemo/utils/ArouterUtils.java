package base.android.t3t.netrequestdemo.utils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import base.android.t3t.netrequestdemo.R;


public class ArouterUtils {

    private static ArouterUtils utils;


    public static ArouterUtils getInstance() {
        if (utils == null) {
            synchronized (ArouterUtils.class) {
                if (utils == null) {
                    utils = new ArouterUtils();
                }
            }
        }
        return utils;
    }

    public Postcard build(String aruoter) {
        return ARouter.getInstance().build(aruoter).withTransition(R.anim.slide_still, R.anim.slide_out_right);
    }

    public Postcard getFragment(String aruoter) {
        return ARouter.getInstance().build(aruoter);
    }


}

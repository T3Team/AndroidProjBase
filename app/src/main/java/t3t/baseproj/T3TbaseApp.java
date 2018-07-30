package t3t.baseproj;

import com.alibaba.android.arouter.launcher.ARouter;

import base.t3t.baseprojlib.image.loader.ImageLoader;
import base.t3t.companybusinesslib.base.BaseApp;

/**
 * application
 */
public class T3TbaseApp extends BaseApp {
    private static T3TbaseApp instance;

    @Override
    protected void init() {
        super.init();

        if (BuildConfig.IS_DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        ImageLoader.init(getApplicationContext());
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
    public static T3TbaseApp getInstance() {
        return instance;
    }

}

package base.t3t.baseprojlib.base;

import android.app.Application;
import android.content.Context;

public abstract class BaseLibApp extends Application {

    /**
     * 初始化非耗时操作
     */
    protected abstract void init();

    /**
     * 耗时操作初始化后台进行
     */
    protected abstract void initInBackground();

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initInBackground();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

package base.t3t.companybusinesslib.base;

import base.t3t.baseprojlib.base.BaseLibApp;
import base.t3t.baseprojlib.utils.DevUtil;


public class BaseApp extends BaseLibApp {
    /**
     * 初始化非耗时操作
     */
    @Override
    protected void init() {
        // 开发用工具
        DevUtil.initialize(this);
    }

    /**
     * 耗时操作初始化后台进行
     */
    @Override
    protected void initInBackground() {

    }
}

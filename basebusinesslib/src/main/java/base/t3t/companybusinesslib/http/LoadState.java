package base.t3t.companybusinesslib.http;

/**
 * Created by dale on 2018/6/21.
 */
public class LoadState {
    //loading 状态 是否加载中 用来控制loading 提示view
    private boolean isLoading = true;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
    }
}

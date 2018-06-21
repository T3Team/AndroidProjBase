package base.t3t.companybusinesslib.utils;

/**
 * Created by dale on 2018/5/9.
 */
public class HttpUrlUtil {
    private static HttpUrlUtil httpUrlUtils;
    private String DemoURL = "http://result.eolinker.com/";
    private String DetTestURL = "http://result.eolinker.com/";

    private HttpUrlUtil() {

    }

    public static HttpUrlUtil getInstance() {
        if (httpUrlUtils == null) {
            synchronized (HttpUrlUtil.class) {
                if (httpUrlUtils == null) {
                    httpUrlUtils = new HttpUrlUtil();
                }
            }
        }
        return httpUrlUtils;
    }


    public String getDemoUrl() {
        return DemoURL;
    }

    public String getNetTestUrl() {
        return DetTestURL;
    }
}

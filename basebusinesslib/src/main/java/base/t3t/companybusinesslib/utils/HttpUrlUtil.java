package base.t3t.companybusinesslib.utils;

import base.t3t.companybusinesslib.http.HttpClient;

/**
 * Created by dale on 2018/5/9.
 */
public class HttpUrlUtil {
    private static HttpUrlUtil httpUrlUtils;
    private String DemoURL = "http://xx.xxx";
    private String DetTestURL = "http://app.net";

    private HttpUrlUtil() {

    }

    public static HttpUrlUtil getInstance() {
        if (httpUrlUtils == null) {
            synchronized (HttpClient.class) {
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

package t3t.baseproj.net;

import base.t3t.companybusinesslib.http.HttpClient;
import t3t.baseproj.utils.HttpUrlUtil;

/**
 * Created by dale on 2018/5/9.
 */
public class DemoHttpCall {
    private static DemoHttpCall call;
    private HttpClient client;

    private DemoHttpCall() {
        client = new HttpClient() {
            @Override
            protected String setBaseUrl() {
                return HttpUrlUtil.getInstance().getDemoUrl();
            }
        };
    }

    public static DemoHttpCall getInstance() {
        if (call == null) {
            synchronized (DemoHttpCall.class) {
                if (call == null) {
                    call = new DemoHttpCall();
                }
            }
        }
        return call;
    }

    public IDemoService getApiService() {
        return client.getHttpClient().create(IDemoService.class);
    }

}

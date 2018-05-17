package base.android.t3t.netrequestdemo.http;

import base.t3t.companybusinesslib.http.HttpClient;
import base.t3t.companybusinesslib.utils.HttpUrlUtil;

public class NetDemoHttpCall {


    private static NetDemoHttpCall call;
    private HttpClient client;

    private NetDemoHttpCall() {
        client = new HttpClient() {
            @Override
            protected String setBaseUrl() {
                return HttpUrlUtil.getInstance().getNetTestUrl();
            }
        };
    }

    public static NetDemoHttpCall getInstance() {
        if (call == null) {
            synchronized (NetDemoHttpCall.class) {
                if (call == null) {
                    call = new NetDemoHttpCall();
                }
            }
        }
        return call;
    }

    public INetService getApiService() {
        return client.getHttpClient().create(INetService.class);
    }


}

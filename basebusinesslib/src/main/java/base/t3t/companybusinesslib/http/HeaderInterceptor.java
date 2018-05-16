package base.t3t.companybusinesslib.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * headers 拦截器
 */
public class HeaderInterceptor implements Interceptor {
    //添加全局header信息
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        //会覆盖以前的请求头信息
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("content-type", "application/json");
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

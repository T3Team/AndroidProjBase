package base.t3t.companybusinesslib.http;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * api corresponding 拦截器
 */
public class ApiStatusCodeInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        dealWithStatusCode(response);
        return response;
    }

    private void dealWithStatusCode(Response response) {
        int code = response.code();//HTTP status code
        if (code == 200 || code == 201) {
            return;
        } else {
            //message TODO 根据业务处理
            String message = "TODO根据业务处理 取response的哪个部分";
            throw new NetErrorException(code, message);
        }
    }
}

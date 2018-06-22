package base.t3t.companybusinesslib.http;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.Observer;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {
    private int RESPONSE_CODE_FAILED = -1;

    /**
     * 根据具体的Api 业务逻辑去重写 onSuccess 方法！
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 业务代码处理 底层不处理状态码业务逻辑
     *
     * @param code
     * @param message
     */
    public abstract void onFailure(int code, final String message);


    @Override
    public final void onNext(T response) {
        onSuccess(response);
    }

    private int code;
    private String errorMessage;

    @Override
    public final void onError(Throwable t) {
        code = 0;
        errorMessage = "未知错误";
        if (t instanceof NetErrorException) {
            NetErrorException httpException = (NetErrorException) t;
            code = httpException.getErrorCode();
            String meg = httpException.getErrorMessage();
            //TODO 拦截的特殊错误码
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            String meg = httpException.response().toString();   //
            code = httpException.code();
            errorMessage = httpException.getMessage();
        } else if (t instanceof SocketTimeoutException) {  //VPN open
            code = RESPONSE_CODE_FAILED;
            errorMessage = "服务器响应超时";
        } else if (t instanceof ConnectException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "网络连接异常，请检查网络";
        } else if (t instanceof RuntimeException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "运行时错误";
        } else if (t instanceof UnknownHostException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "无法解析主机，请检查网络连接";
        } else if (t instanceof UnknownServiceException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "未知的服务器错误";
        } else if (t instanceof IOException) {  //飞行模式等
            code = RESPONSE_CODE_FAILED;
            errorMessage = "没有网络，请检查网络连接";
        }
        onFailure(code, errorMessage);
    }
}

package base.t3t.companybusinesslib.http;

import android.content.Context;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import base.t3t.baseprojlib.widget.DialogLoading;
import io.reactivex.Observer;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {
    public final int RESPONSE_CODE_FAILED = -1;
    private DialogLoading dialogLoading;
    //是否需要显示Http 请求的进度，默认的是需要，但是Service 和 预取数据不需要
    private boolean showProgress = true;
    private Context cxt;

    private DialogLoading getDialogLoading() {
        if (dialogLoading == null) {
            dialogLoading = DialogLoading.createDialog(cxt);
        }
        return dialogLoading;
    }

    public void showLoadingDialog() {
        getDialogLoading();
        if (null != dialogLoading) {
            try {
                dialogLoading.show();
            } catch (Exception ex) {

            }
        }
    }

    public void closeLoadingDialog() {
        if (showProgress)
            try {
                if (dialogLoading != null) {
                    dialogLoading.dismiss();
                }
            } catch (Exception ex) {
            }
    }

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

    public BaseObserver(Context cxt, boolean showProgress) {
        baseObserver_(cxt, showProgress);
    }

    public BaseObserver(Context context) {
        baseObserver_(cxt, true);
    }

    private void baseObserver_(Context cxt, boolean showProgress) {
        this.cxt = cxt;
        this.showProgress = showProgress;
        initLoadingDialog();
    }

    private void initLoadingDialog() {
        if (showProgress) {
            try {
                showLoadingDialog();
            } catch (Exception ex) {
            }
        }
    }


    @Override
    public final void onNext(T response) {
        closeLoadingDialog();
        onSuccess(response);
    }

    private int code;
    private String errorMessage;

    @Override
    public final void onError(Throwable t) {
        closeLoadingDialog();
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

    /**
     * 简单的把Dialog 处理掉
     */
    @Override
    public final void onComplete() {
        closeLoadingDialog();
    }

}

package base.t3t.companybusinesslib.http;

public class HttpResponse<T> {
    private int code;
    private String message;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return message;
    }

    public void setError(String error) {
        this.message = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "httpResponse{" +
                "code=" + code +
                ", error='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}

package base.t3t.companybusinesslib.http;

public class NetErrorException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public NetErrorException(int errorCode, String errorMessage) {
        this(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public NetErrorException(String message) {
        super(message);

    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

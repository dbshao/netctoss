package netctoss.service;

/**
 * Created by itachi on 2017/1/18.
 * 自定义应用异常
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

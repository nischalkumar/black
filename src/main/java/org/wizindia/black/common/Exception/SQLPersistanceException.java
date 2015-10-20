package org.wizindia.black.common.Exception;

/**
 * Created by hari_om on 1/10/15.
 */
public class SQLPersistanceException extends RuntimeException {
    int code;
    String msg;

    public SQLPersistanceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SQLPersistanceException(String message, int code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public SQLPersistanceException(String message, Throwable cause, int code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public SQLPersistanceException(Throwable cause, int code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public SQLPersistanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }
}

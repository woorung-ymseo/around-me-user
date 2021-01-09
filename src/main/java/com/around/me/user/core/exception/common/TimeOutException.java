package com.around.me.user.core.exception.common;



import com.around.me.user.core.enums.response.ResponseCodeEnum;

import java.io.Serializable;

/**
 * 시관초과
 */
public class TimeOutException extends RuntimeException implements Serializable {

    private String code = ResponseCodeEnum.TIME_OUT.code();
    private String message = ResponseCodeEnum.TIME_OUT.message();

    public TimeOutException() {
        super();
    }

    public TimeOutException(String message) {
        super(message);
    }

    public TimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeOutException(Throwable cause) {
        super(cause);
    }

    public TimeOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TimeOutException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

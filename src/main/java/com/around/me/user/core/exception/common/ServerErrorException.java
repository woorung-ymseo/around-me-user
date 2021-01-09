package com.around.me.user.core.exception.common;



import com.around.me.user.core.enums.response.ResponseCodeEnum;

import java.io.Serializable;

/**
 * 서버에러
 */
public class ServerErrorException extends RuntimeException implements Serializable {

    private String code = ResponseCodeEnum.SERVER_ERROR.code();
    private String message = ResponseCodeEnum.SERVER_ERROR.message();

    public ServerErrorException() {
        super();
    }

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerErrorException(Throwable cause) {
        super(cause);
    }

    public ServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServerErrorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

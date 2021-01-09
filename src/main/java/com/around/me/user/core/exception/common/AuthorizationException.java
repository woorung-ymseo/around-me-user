package com.around.me.user.core.exception.common;



import com.around.me.user.core.enums.response.ResponseCodeEnum;

import java.io.Serializable;

/**
 * 권한에러
 */
public class AuthorizationException extends RuntimeException implements Serializable {

    private String code = ResponseCodeEnum.NONE_AUTH.code();
    private String message = ResponseCodeEnum.NONE_AUTH.message();

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }

    public AuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AuthorizationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

package com.around.me.user.core.exception.handler;

import com.around.me.user.core.dto.Response;
import com.around.me.user.core.enums.response.ResponseCodeEnum;
import com.around.me.user.core.exception.common.AuthorizationException;
import com.around.me.user.core.exception.common.BadRequestException;
import com.around.me.user.core.exception.common.ServerErrorException;
import com.around.me.user.core.exception.common.TimeOutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class AroundMeRestExceptionHandler {

    /**
     * 공통 에러
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response serverException(Exception e) {
        log.error(">>>>>>> serverException {}", e.getMessage());

        return Response.error(ResponseCodeEnum.SERVER_ERROR.code(), e.getMessage());

    }

    /**
     * 잘못된 아규먼츠 에러
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Response illegalArgumentExceptionException(IllegalArgumentException e) {
        log.error(">>>>>>> illegalArgumentExceptionException {}", e.getMessage());

        return Response.badRequest(e.getMessage());

    }

    /**
     * 권한 에러
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public Response authorizationException(AuthorizationException e) {
        log.error(">>>>>>> authorizationException [{}] [{}]", e.getCode(), e.getMessage());

        return Response.unAuthorized(e.getCode(), e.getMessage());
    }

    /**
     * 잘못된 요청 에러
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadRequestException.class)
    public Response badRequestException(BadRequestException e) {
        log.error(">>>>>>> badRequestException [{}] [{}]", e.getCode(), e.getMessage());

        return Response.badRequest(e.getCode(), e.getMessage());
    }

    /**
     * 서버에러
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServerErrorException.class)
    public Response serverErrorException(ServerErrorException e) {
        log.error(">>>>>>> serverErrorException [{}] [{}]", e.getCode(), e.getMessage());

        return Response.error(e.getCode(), e.getMessage());
    }

    /**
     * 타임아웃 에러
     *
     * @param e
     * @return
     */
    @ExceptionHandler(TimeOutException.class)
    public Response timeOutException(TimeOutException e) {
        log.error(">>>>>>> TimeOutException [{}] [{}]", e.getCode(), e.getMessage());

        return Response.error(e.getCode(), e.getMessage());
    }

    /**
     * 바인딩에러
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionException(MethodArgumentNotValidException e) {
        log.error(">>>>>>> methodArgumentNotValidExceptionException {}", e.getMessage());

        return getErrors(e.getBindingResult());
    }

    /**
     * 바인딩에러
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Response bindExceptionExceptionException(BindException e) {
        log.error(">>>>>>> bindExceptionExceptionException {}", e.getMessage());

        return getErrors(e.getBindingResult());
    }

    private Response getErrors(BindingResult bindingResult2) {
        BindingResult bindingResult = bindingResult2;

        List<String> errors = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");

            errors.add(builder.toString());

            builder.setLength(0);
        }

        return Response.badRequest(errors);
    }
}

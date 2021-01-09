package com.around.me.user.core.dto;

import com.around.me.user.core.enums.response.ResponseCodeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class Response<T> {

    @JsonProperty("status")
    private int status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("content")
    private T content;

    protected Response(int status, String code, String message, T content) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 응답 객체 생성
     *
     * @param status
     * @param code
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> of(int status, String code, String message, T content) {

        return new Response<>(status, code, message, content);
    }

    /**
     * 응답 객체 생성
     *
     * @param status
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> of(int status, String code, String message) {

        return of(status, code, message, null);
    }

    /*================================*/

    /**
     * 정상 응답 개체 생성
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok() {
        return ok(ResponseCodeEnum.OK.code(), ResponseCodeEnum.OK.message(), null);
    }

    /**
     * 정상 응답 개체 생성
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(T content) {
        return ok(ResponseCodeEnum.OK.code(), ResponseCodeEnum.OK.message(), content);
    }

    /**
     * 정상 응답 객체 생성
     *
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(String message, T content) {
        return ok(ResponseCodeEnum.OK.code(), message, content);
    }

    /**
     * 정상 응답 객체 생성
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(String code, String message) {
        return ok(code, message, null);
    }

    /**
     * 정상 응답 객체 생성
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(String message) {
        return ok(ResponseCodeEnum.OK.code(), message, null);
    }

    /**
     * 정상 응답 객체 생성
     *
     * @param code
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(String code, String message, T content) {

        return of(HttpStatus.OK.value(), code, message, content);
    }

    /*================================*/

    /**
     * 잘못된 요청 응답 객체 생성
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> badRequest() {
        return badRequest(ResponseCodeEnum.BAD_REQUEST.code(), ResponseCodeEnum.BAD_REQUEST.message(), null);
    }

    /**
     * 잘못된 요청 응답 객체 생성
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> badRequest(T content) {
        return badRequest(ResponseCodeEnum.BAD_REQUEST.code(), ResponseCodeEnum.BAD_REQUEST.message(), content);
    }

    /**
     * 잘못된 요청 응답 객체 생성
     *
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> badRequest(String message, T content) {
        return badRequest(ResponseCodeEnum.BAD_REQUEST.code(), message, content);
    }

    /**
     * 잘못된 요청 응답 객체 생성
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> badRequest(String code, String message) {
        return badRequest(code, message, null);
    }

    /**
     * 잘못된 요청 응답 객체 생성
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> badRequest(String message) {
        return badRequest(ResponseCodeEnum.BAD_REQUEST.code(), message, null);
    }

    /**
     * 잘못된 요청 응답 객체 생성
     *
     * @param code
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> badRequest(String code, String message, T content) {
        return of(HttpStatus.BAD_REQUEST.value(), code, message, content);
    }

    /*================================*/

    /**
     * 잘못된 권한 응답 객체 생성
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> unAuthorized() {
        return unAuthorized(ResponseCodeEnum.NONE_AUTH.code(), ResponseCodeEnum.NONE_AUTH.message(), null);
    }

    /**
     * 잘못된 권한 응답 객체 생성
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> unAuthorized(T content) {

        return unAuthorized(ResponseCodeEnum.NONE_AUTH.code(), ResponseCodeEnum.NONE_AUTH.message(), content);
    }

    /**
     * 잘못된 권한 응답 객체 생성
     *
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> unAuthorized(String message, T content) {
        return unAuthorized(ResponseCodeEnum.NONE_AUTH.code(), message, content);
    }

    /**
     * 잘못된 권한 응답 객체 생성
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> unAuthorized(String code, String message) {
        return unAuthorized(code, message, null);
    }

    /**
     * 잘못된 권한 응답 객체 생성
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> unAuthorized(String message) {
        return unAuthorized(ResponseCodeEnum.NONE_AUTH.code(), message, null);
    }


    /**
     * 잘못된 권한 응답 객체 생성
     *
     * @param code
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> unAuthorized(String code, String message, T content) {

        return of(HttpStatus.UNAUTHORIZED.value(), code, message, content);
    }


    /**
     * 에러 응답 객체 생성
     *
     * @param code
     * @param message
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(String code, String message, T content) {

        return of(HttpStatus.INTERNAL_SERVER_ERROR.value(), code, message, content);
    }

    /**
     * 에러 응답 객체 생성
     *
     * @param code
     * @param content
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(String code, T content) {

        return error(code, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), content);
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public T getContent() {
        return content;
    }
}

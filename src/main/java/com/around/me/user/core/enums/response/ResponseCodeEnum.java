package com.around.me.user.core.enums.response;

/**
 * 000000 => 성공
 * 0(성공0/실패1)  00(서비스번호)   000 (에러타입 : 각 서비스에서 세분화 시켜서 사용)
 *
 * [서비스번호]
 * 00 : 서버장애
 * 01 : 공통
 * 02 : 유저
 * 03 : 점포
 * 04 : 이벤트
 */
public enum ResponseCodeEnum {
    OK("000000", "성공"),
    SERVER_ERROR("101100", " 서버장애입니다."),
    BAD_REQUEST("101200", " 잘못된 요청입니다."),
    TIME_OUT("101300", "요청시간초과"),
    NONE_AUTH("101400", "권한이 없습니다."),
    AUTH_EXPIRED("101401", "권한 만료")
    ;

    private final String code;
    private final String message;

    ResponseCodeEnum(String code, String message){
        this.code = code;
        this.message = message;

    }

    public String code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }
}

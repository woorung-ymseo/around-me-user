package com.around.me.user.api.v1.user.dto;

import com.around.me.user.core.enums.user.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@ApiModel("회원가입 Param")
@Getter
public class PostUserSignUpParamDTO {

    @ApiModelProperty(value = "회원번호", hidden = true)
    private long userNo;

    @ApiModelProperty(required = true, value = "회원비밀번호", example = "test7144")
    @NotNull(message = "비밀번호 필수입니다.")
    private String userPassword;

    @ApiModelProperty(required = true, value = "회원이름", example = "김진근")
    private String userName;

    @ApiModelProperty(required = true, value = "회원이메일", example = "rlawlsrms789@naver.com")
    private String userEmail;

    @ApiModelProperty(required = true, value = "휴대폰", example = "01099687144")
    private String userMobile;

    @ApiModelProperty(required = true, value = "성별", example = "M")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @ApiModelProperty(required = true, value = "생년월일", example = "19931116")
    private String birth;

}



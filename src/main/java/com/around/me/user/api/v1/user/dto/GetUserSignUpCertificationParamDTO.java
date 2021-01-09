package com.around.me.user.api.v1.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("이메일 인증 Param")
@Builder
@Getter
public class GetUserSignUpCertificationParamDTO {

    @ApiModelProperty(value = "이메일 인증 아이디", example = "geun7144@naver.com")
    String userEmail;

    @ApiModelProperty(value = "인증번호")
    String certificationNum;

}

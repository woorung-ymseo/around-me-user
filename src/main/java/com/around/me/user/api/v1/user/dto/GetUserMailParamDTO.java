package com.around.me.user.api.v1.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("이메일 인증 Param")
@Builder
@Getter
public class GetUserMailParamDTO {

    @ApiModelProperty(value = "이메일 인증 아이디", example = "geun7144@naver.com")
    String userEmail;

    @ApiModelProperty(hidden = true)
    String title;

    @ApiModelProperty(hidden = true)
    String contents;

    @ApiModelProperty(hidden = true)
    String certificationNum;
}

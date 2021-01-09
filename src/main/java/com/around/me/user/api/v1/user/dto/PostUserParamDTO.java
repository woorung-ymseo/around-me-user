package com.around.me.user.api.v1.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel("Post 회원 Param DTO")
@Getter
public class PostUserParamDTO {

    @ApiModelProperty(value = "회원아이디", example = "rlawlsrms789@naver.com")
    String userEmail;

    @ApiModelProperty(value = "비밀번호", example = "test7144")
    String userPassword;

}


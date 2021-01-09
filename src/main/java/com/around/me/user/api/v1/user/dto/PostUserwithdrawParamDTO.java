package com.around.me.user.api.v1.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel("Post 회원 Param DTO")
@Getter
public class PostUserwithdrawParamDTO {

    @ApiModelProperty(value = "회원아이디", example = "rlawlsrms789@naver.com")
    String userEmail;

    @ApiModelProperty(value = "탈퇴사유타입", example = "")
    String withdrawReasonType;

    @ApiModelProperty(value = "탈퇴사유", example = "")
    String withdrawReason;
}


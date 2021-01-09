package com.around.me.user.api.v1.user.dto;

import com.around.me.user.core.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel("Post 인가 Param DTO")
@Getter
public class PostAuthParamDTO {

    @ApiModelProperty(value = "회원아이디", example = "geun7144@naver.com")
    String userEmail;

    @ApiModelProperty(hidden = true)
    public void setUserParam(User user) {
        this.userEmail = user.getUserEmail();
    }
}


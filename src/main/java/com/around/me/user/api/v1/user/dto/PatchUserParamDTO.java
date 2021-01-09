package com.around.me.user.api.v1.user.dto;

import com.around.me.user.core.enums.user.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@ApiModel("회원수정 Param")
@Getter
public class PatchUserParamDTO {

    @ApiModelProperty(value = "회원번호", hidden = true)
    private long userNo;

    @ApiModelProperty(value = "회원비밀번호", example = "test7144")
//    @JsonIgnore
    private String userPassword;

    @ApiModelProperty(value = "회원이름", example = "김진근")
    private String userName;

    @ApiModelProperty(value = "회원이메일", example = "rlawlsrms789@naver.com")
    private String userEmail;

    @ApiModelProperty(value = "휴대폰", example = "01099687144")
    private String userMobile;

    @ApiModelProperty(value = "성별", example = "M")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @ApiModelProperty(value = "생년월일", example = "19931116")
    private String birth;
}



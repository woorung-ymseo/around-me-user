package com.around.me.user.core.domain;


import com.around.me.user.api.v1.user.dto.PatchUserParamDTO;
import com.around.me.user.api.v1.user.dto.PostUserSignUpParamDTO;
import com.around.me.user.core.enums.common.YnEnum;
import com.around.me.user.core.enums.user.GenderEnum;
import com.around.me.user.core.enums.user.UserSortEnum;
import com.around.me.user.core.enums.user.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel("User 정보")
@Table(name = "user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

//    @ApiModelProperty(value = "회원번호", hidden = true)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;

//    @ApiModelProperty(value = "회원비밀번호", example = "test7144")
    @JsonIgnore
    @Column(nullable = false)
    private String userPassword;

//    @ApiModelProperty(value = "회원이름", example = "김진근")
    @Column(nullable = false)
    private String userName;

//    @ApiModelProperty(value = "회원이메일", example = "rlawlsrms789@naver.com")
    @Column(nullable = false, unique = true)
    private String userEmail;

//    @ApiModelProperty(value = "휴대폰", example = "01099687144")
    @Column(nullable = false)
    private String userMobile;

//    @ApiModelProperty(value = "성별")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum gender;

//    @ApiModelProperty(value = "생년월일", example = "19931116")
    @Column(nullable = false)
    private String birth;

//    @ApiModelProperty(value = "회원구분", hidden = true)
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "NR")
//    @Column(nullable = false)
    private UserSortEnum userSort;

//    @ApiModelProperty(value = "최근로그인일자", hidden = true)
    private LocalDateTime lastLoginDate;

//    @ApiModelProperty(value = "최근비밀번호수정일자", hidden = true)
    private LocalDateTime lastPasswordModDate;

//    @ApiModelProperty(value = "로그인시도횟수", hidden = true)
    @ColumnDefault(value = "0")
//    @Column(nullable = false)
    private int loginTryCount;

//    @ApiModelProperty(value = "회원상태코드", hidden = true)
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "NR")
//    @Column(nullable = false)
    private UserStatusEnum userStatus;

//    @ApiModelProperty(value = "블랙리스트여부", hidden = true)
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "N")
//    @Column(nullable = false)
    private YnEnum blackListYn;

//    @ApiModelProperty(value = "등록일시", hidden = true)
//    @Column(nullable = false)
    private LocalDateTime regDatetime;

//    @ApiModelProperty(value = "수정일시", hidden = true)
    private LocalDateTime modDatetime;

//    @ApiModelProperty(value = "등록회원번호", hidden = true)
    @ColumnDefault(value = "1")
//    @Column(nullable = false)
    private long regUserNo;

//    @ApiModelProperty(value = "수정회원번호", hidden = true)
    private long modUserNo;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void signUp(PostUserSignUpParamDTO postUserSignUpParamDto) {
        this.userName = postUserSignUpParamDto.getUserName();
        this.userEmail = postUserSignUpParamDto.getUserEmail();
        this.userMobile = postUserSignUpParamDto.getUserMobile();
        this.gender = postUserSignUpParamDto.getGender();
        this.birth = postUserSignUpParamDto.getBirth();
    }

    public void update(PatchUserParamDTO patchUserParamDTO) {

        if (this.userName != patchUserParamDTO.getUserName()) {
            this.userName = patchUserParamDTO.getUserName();
        }

        if (this.userEmail != patchUserParamDTO.getUserEmail()) {
            this.userEmail = patchUserParamDTO.getUserEmail();
        }

        if (this.userMobile != patchUserParamDTO.getUserMobile()) {
            this.userMobile = patchUserParamDTO.getUserMobile();
        }

        if (this.gender != patchUserParamDTO.getGender()) {
            this.gender = patchUserParamDTO.getGender( );
        }

        if (this.birth != patchUserParamDTO.getBirth()) {
            this.birth = patchUserParamDTO.getBirth();
        }

        this.modUserNo = 1L;
        this.modDatetime = LocalDateTime.now();
    }
}

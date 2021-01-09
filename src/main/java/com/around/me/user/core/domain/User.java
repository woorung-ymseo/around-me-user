package com.around.me.user.core.domain;


import com.around.me.user.api.v1.user.dto.PatchUserParamDTO;
import com.around.me.user.api.v1.user.dto.PostUserSignUpParamDTO;
import com.around.me.user.core.enums.user.GenderEnum;
import com.around.me.user.core.enums.user.UserSortEnum;
import com.around.me.user.core.enums.user.UserStatusEnum;
import com.around.me.user.core.enums.user.YnEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails {

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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    // getUsername을 통해 spring security에서 사용하는 username을 가져감
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

//    @Builder
//    public User(String userPassword, String regDatetime, List<String> roles) {
//        this.userPassword = userPassword;
//        this.regDatetime = regDatetime;
//        this.roles = roles;
//    }

/*    @Builder(builderClassName = "SignUpBuilder", builderMethodName = "SignUpBuilder")
    public User(String userPassword, String userName, String userEmail, String userMobile,
                GenderEnum gender, String birth, UserSortEnum userSort, String lastLoginDate,
                String lastPasswordModDate, int loginTryCount, UserStatusEnum userStatus, YnEnum blackListYn,
                String regDatetime, String modDatetime, long regUserNo, long modUserNo) {

        Assert.notNull(userPassword, "userPassword must not be null");
        Assert.notNull(userName, "userName must not be null");
        Assert.notNull(userEmail, "userEmail must not be null");
        Assert.notNull(userMobile, "userMobile must not be null");
        Assert.notNull(gender, "gender must not be null");
        Assert.notNull(birth, "birth must not be null");
        Assert.notNull(userSort, "userSort must not be null");
        Assert.notNull(loginTryCount, "loginTryCount must not be null");
        Assert.notNull(userStatus, "userStatus must not be null");
        Assert.notNull(blackListYn, "blackListYn must not be null");
        Assert.notNull(regDatetime, "regDatetime must not be null");
        Assert.notNull(regUserNo, "regUserNo must not be null");

        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobile = userMobile;
        this.gender = gender;
        this.birth = birth;
        this.userSort = userSort;
        this.lastLoginDate = lastLoginDate;
        this.lastPasswordModDate = lastPasswordModDate;
        this.loginTryCount = loginTryCount;
        this.userStatus = userStatus;
        this.blackListYn = blackListYn;
        this.regDatetime = regDatetime;
        this.modDatetime = modDatetime;
        this.regUserNo = regUserNo;
        this.modUserNo = modUserNo;
    }*/

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

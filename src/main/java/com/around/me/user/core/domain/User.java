package com.around.me.user.core.domain;


import com.around.me.user.core.enums.user.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "user")
@Entity
@Getter
@ToString
public class User implements Serializable {

    @Id @GeneratedValue
    @Setter
    private long userNo;

    @JsonIgnore
    @Setter
    private String userPassword;

    @Setter
    private String userName;

    @Setter
    private String userEmail;

    @Setter
    private String userMobile;

    @Setter
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Setter
    private String birth;

    @Setter
    private String userSort;

    @Setter
    private LocalDate lastLoginDate;

    @Setter
    private String lastPasswordModDate;

    @Setter
    private int loginTryCount;

    @Setter
    private String userStatus;

    @Setter
    private String blackListYn;

    @Setter
    private LocalDateTime regDatetime;

    @Setter
    private LocalDateTime modDatetime;

    @Setter
    private Long regUserNo;

    @Setter
    private Long modUserNo;
}

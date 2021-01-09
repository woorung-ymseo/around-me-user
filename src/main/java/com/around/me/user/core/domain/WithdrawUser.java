package com.around.me.user.core.domain;


import com.around.me.user.core.enums.user.WithdrawReasonEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@ApiModel("탈퇴회원")
@Table(name = "withdraw_user")
@Entity
@Getter
public class WithdrawUser{

//    @ApiModelProperty(value = "회원번호", hidden = true)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long withdrawUserNo;

//    @ApiModelProperty(value = "회원비밀번호", example = "test7144")
//    @JsonIgnore
//    @Column(nullable = false)
//    private long userNo;

//    @ApiModelProperty(value = "회원이름", example = "김진근")
    @Column(nullable = false)
    private long userNo;

//    @ApiModelProperty(value = "회원이메일", example = "rlawlsrms789@naver.com")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WithdrawReasonEnum withdrawReasonType;

//    @ApiModelProperty(value = "휴대폰", example = "01099687144")
    @Column
    private String withdrawReason;

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


}

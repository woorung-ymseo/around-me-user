package com.around.me.user.core.context;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserContextHolder implements Serializable {

    private long userNo;
    
    private String userName;
    
    private String userEmail;
    
    private String userMobile;

    private String gender;
    
    private String birth;
    
    private String userSort;

    private String userStatus;

    private String blackListYn;
}

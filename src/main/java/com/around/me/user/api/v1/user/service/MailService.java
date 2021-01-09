package com.around.me.user.api.v1.user.service;

import com.around.me.user.api.v1.user.dto.GetUserMailParamDTO;
import com.around.me.user.api.v1.user.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;
    private static final String FROM_ADDRESS = "wafc7144@gmail.com";

    public GetUserMailParamDTO mailCertification(String userEmail){
        String str = this.getMailNumber();

        GetUserMailParamDTO getUserMailParamDTO = GetUserMailParamDTO.builder()
                .userEmail(userEmail)
                .title("Around Me 인증번호 안내")
                .contents("인증번호 : " + str)
                .certificationNum(str).build();

        return getUserMailParamDTO;
    }

    public String getMailNumber(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 6; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public GetUserMailParamDTO mailPasswordModifcation(String userEmail){
        String str = this.getMailNumber();

        GetUserMailParamDTO getUserMailParamDTO = GetUserMailParamDTO.builder()
                .userEmail(userEmail)
                .title("Around Me 비밀번호 변경 주소 안내")
                .contents("비밀번호 변경 주소 : test.com")
                .build();

        return getUserMailParamDTO;
    }

    private Boolean mailSend(GetUserMailParamDTO getUserMailParamDTO){

        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(getUserMailParamDTO.getUserEmail());
            message.setFrom(FROM_ADDRESS);
            message.setSubject(getUserMailParamDTO.getTitle());
            message.setText(getUserMailParamDTO.getContents());

            mailSender.send(message);
        } catch (Exception e) {

            return false;
        }

        return true;
    }
}

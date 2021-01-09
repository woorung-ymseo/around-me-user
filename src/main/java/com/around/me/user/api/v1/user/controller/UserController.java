package com.around.me.user.api.v1.user.controller;

import com.around.me.user.api.v1.user.dto.GetUserMailParamDTO;
import com.around.me.user.api.v1.user.dto.PatchUserParamDTO;
import com.around.me.user.api.v1.user.dto.PostUserParamDTO;
import com.around.me.user.api.v1.user.dto.PostUserSignUpParamDTO;
import com.around.me.user.api.v1.user.service.MailService;
import com.around.me.user.api.v1.user.service.UserService;
import com.around.me.user.api.v1.user.util.RedisUtil;
import com.around.me.user.core.annoitation.version.RestMappingV1;
import com.around.me.user.core.domain.User;
import com.around.me.user.core.dto.Response;
import com.around.me.user.core.enums.user.UserSortEnum;
import com.around.me.user.core.enums.user.UserStatusEnum;
import com.around.me.user.core.enums.user.YnEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * User 관련 API Controller
 */
@Api(tags = "회원")
@Slf4j
@RequiredArgsConstructor
@RestMappingV1
public class UserController {

    // 회원가입 비밀번호 암호화 방식
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MailService mailService;
    private final RedisUtil redisUtil;

    @ApiOperation(value = "회원가입")
    @PostMapping("/sign-up")
    Response<User> signUp(@RequestBody @Valid PostUserSignUpParamDTO postUserSignUpParamDTO, @ApiIgnore Error errors) {

        User user = User.builder()
                .userEmail(postUserSignUpParamDTO.getUserEmail())
                .userPassword(passwordEncoder.encode(postUserSignUpParamDTO.getUserPassword()))
                .userName(postUserSignUpParamDTO.getUserName())
                .userMobile(postUserSignUpParamDTO.getUserMobile())
                .gender(postUserSignUpParamDTO.getGender())
                .birth(postUserSignUpParamDTO.getBirth())
                .userSort(UserSortEnum.NR)
                .lastLoginDate(null)
                .lastPasswordModDate(null)
                .loginTryCount(0)
                .userStatus(UserStatusEnum.NR)
                .blackListYn(YnEnum.N)
                .regDatetime(LocalDateTime.now())
                .modDatetime(null)
                .regUserNo(1)
                .modUserNo(0)
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build();

        User resultUser = userService.signUp(user);

        return Response.ok(resultUser);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/sign-in")
    Response<User> signIn(@RequestBody PostUserParamDTO postUserParamDTO, @ApiIgnore Error errors) {

        User user = userService.signIn(postUserParamDTO.getUserEmail(), postUserParamDTO.getUserPassword());

        userService.generateToken(user);

        // 토큰객체 내려주기
        return Response.ok(user);
    }

    @ApiOperation(value = "인증번호 메일발송")
    @GetMapping("/mail/certification")
    Response<String> mailCertification(@ApiParam(value = "이메일", required = true, example = "geun7144@naver.com") @RequestParam String userEmail, @ApiIgnore Error errors) {

        // 중복체크 로직


        GetUserMailParamDTO getUserMailParamDTO = mailService.mailCertification(userEmail);
        
        // 분기처리
//        String result = mailService.mailSend(getUserMailParamDTO);

/*        if ("fail".equals(result)) {
            return Response.badRequest(null);
        } else {
            redisUtil.setDataExpire("certification_email_" + userEmail, getUserMailParamDTO.getCertificationNum(), 5 * 60 * 1000L);

            return Response.ok("성공");
        }*/

        return Response.ok("성공");
    }

    @ApiOperation(value = "인증번호 확인")
    @GetMapping("/sign-up/certification")
    Response<String> signUpCertification(@ApiParam(value = "이메일", required = true, example = "geun7144@naver.com") @RequestParam String userEmail, @ApiParam(value = "인증번호", required = true) @RequestParam String certificationNum, @ApiIgnore Error errors) {

        String result = userService.signUpCertification(userEmail, certificationNum);

        // 서비스에서
        if ("fail".equals(result)) {
            return Response.badRequest(null);
        } else {
            return Response.ok("");
        }
    }

    @ApiOperation(value = "비밀번호확인")
    @PostMapping("/password/checking")
    Response<User> passwordChecking(@RequestBody PostUserParamDTO postUserParamDTO, @ApiIgnore Error errors) {

        User user = userService.passwordChecking(postUserParamDTO.getUserEmail(), postUserParamDTO.getUserPassword());

        // true false
        return Response.ok(user);
    }

    @ApiOperation(value = "회원정보")
    @GetMapping("/user")
    Response<User> user(@ApiParam(value = "이메일", required = true, example = "geun7144@naver.com") @RequestHeader String userEmail, @ApiIgnore Error errors) {

        User user = userService.getUser(userEmail);

        if (user == null) {
            return Response.badRequest(null);
        } else {
            return Response.ok(user);
        }
    }

    @ApiOperation(value = "비밀번호 변경 메일 전송")
    @GetMapping("/mail/password/modifcation") // 메일용 토큰
    Response<String> mailPasswordModifcation(@ApiParam(value = "이메일", required = true, example = "rlawlsrms789@naver.com") @RequestParam String userEmail, @ApiIgnore Error errors) {

        User user = userService.getUser(userEmail);

        if (user == null) {

            return Response.badRequest(null);
        } else {
            GetUserMailParamDTO getUserMailParamDTO = mailService.mailPasswordModifcation(userEmail);

            /*String result = mailService.mailSend(getUserMailParamDTO);

            if ("fail".equals(result)) {
                return Response.badRequest(null);
            } else {

                return Response.ok("성공");
            }*/

            return Response.ok("성공");
        }
    }

    @ApiOperation(value = "비밀번호 변경")
    @PatchMapping("/password") // 다시만들어
    Response<String> password(@ApiParam(value = "이메일", required = true, example = "rlawlsrms789@naver.com") @RequestParam String userEmail, @ApiIgnore Error errors) {

        User user = userService.getUser(userEmail);

        if (user == null) {

            return Response.badRequest(null);
        } else {
            GetUserMailParamDTO getUserMailParamDTO = mailService.mailPasswordModifcation(userEmail);

/*            String result = mailService.mailSend(getUserMailParamDTO);

            if ("fail".equals(result)) {
                return Response.badRequest(null);
            } else {

                return Response.ok("성공");
            }*/
        }

        return Response.ok("성공");

    }

    @ApiOperation(value = "회원정보수정")
    @PatchMapping("/user")
    Response<Long> user(@RequestBody PatchUserParamDTO patchUserParamDTO, @ApiIgnore Error errors) {

        User user = userService.getUser(patchUserParamDTO.getUserEmail());

        user.update(patchUserParamDTO);

        User result = userService.patchUser(user);

        if(result==null) {
            return Response.badRequest(null);
        }else {
            return Response.ok(result.getUserNo());
        }
    }

    @ApiOperation(value = "탈퇴사유 리스트")
    @GetMapping("/user/withdraw/reasons")
    Response<List> getWithdrawsReasons() {

        Response<List> withdrawsReasons = userService.withdrawsReasons();

        return withdrawsReasons;
    }

    @ApiOperation(value = "탈퇴하기")
    @PostMapping("/user/withdraw")
    Response<User> withdraw(@RequestBody PostUserParamDTO postUserParamDTO, @ApiIgnore Error errors) {

        User user = userService.passwordChecking(postUserParamDTO.getUserEmail(), postUserParamDTO.getUserPassword());

        return Response.ok(user);
    }
}

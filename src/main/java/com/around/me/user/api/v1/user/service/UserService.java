package com.around.me.user.api.v1.user.service;

import com.around.me.user.api.v1.user.dto.PostAuthParamDTO;
import com.around.me.user.api.v1.user.repository.UserRepository;
import com.around.me.user.api.v1.user.util.RedisUtil;
import com.around.me.user.core.domain.User;
import com.around.me.user.core.dto.Response;
import com.around.me.user.core.support.ResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ResourceClient resourceClient;

    private final RedisUtil redisUtil;

    public User findUserId() {
        Optional<User> user = userRepository.findById(1L);

        return user.orElse(null);
    }

    public Response<String> term() {
        Response<String> data = resourceClient.getForResponse("http://127.0.0.1:8082/common/api/v1/term", String.class);

        return data;
    }

    @Transactional
    public User signUp(User user) {

        userRepository.save(user);

        return user;
    }

    public User signIn(String userEmail, String userPassword) {

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL입니다."));

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        return user;

    }

    public Response<String> generateToken(User user) {

        PostAuthParamDTO postAuthParamDto = new PostAuthParamDTO();

        postAuthParamDto.setUserParam(user);

        Response<String> data = resourceClient.postForResponse("http://127.0.0.1:8070/authentication/api/v1/authentication", postAuthParamDto, String.class);

        return data;
    }

    public String signUpCertification(String userEmail, String certificationNum) {

        String redisCertificationNum = redisUtil.getData("certification_email_" + userEmail);

        if (redisCertificationNum.equals(certificationNum)) {
            return "success";
        } else {
            return "fail";
        }
    }

    public User passwordChecking(String userEmail, String userPassword) {

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL입니다."));

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        return user;

    }

    public User getUser(String userEmail) {
        Optional<User> user = userRepository.findByUserEmail(userEmail);

        return user.orElse(null);
    }

    @Transactional
    public User patchUser(User user) {

        User result = userRepository.save(user);

        return result;
    }

    public Response<List> withdrawsReasons() {
        Response<List> data = resourceClient.getForResponse("http://127.0.0.1:8765/common/api/v1//codes/withd", List.class);

        return data;
    }
}

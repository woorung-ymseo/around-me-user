package com.around.me.user.api.v1.user.service;

import com.around.me.user.api.v1.user.repository.UserRepository;
import com.around.me.user.core.domain.User;
import com.around.me.user.core.dto.Response;
import com.around.me.user.core.support.ResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ResourceClient resourceClient;

    public User findUserId() {
        Optional<User> user = userRepository.findById(1L);

        return user.orElse(null);
    }

    public Response<String> term() {
        Response<String> data = resourceClient.getForResponse("http://127.0.0.1:8082/common/api/v1/term", String.class);

        return data;
    }
}

package com.around.me.user.api.v1.user.service;

import com.around.me.user.api.v1.user.repository.UserRepository;
import com.around.me.user.core.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUserId() {
        Optional<User> user = userRepository.findById(1L);

        return user.get();
    }
}

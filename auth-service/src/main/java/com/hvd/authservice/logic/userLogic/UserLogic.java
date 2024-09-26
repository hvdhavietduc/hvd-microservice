package com.hvd.authservice.logic.userLogic;

import com.hvd.authservice.model.user.User;
import com.hvd.authservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class UserLogic {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public User getUserById(String userId) {
        return userRepository.findUsersById(userId);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User getUserByUsernameForCreating(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User getUserByUsernameForUpdating(String username) {
        return userRepository.findUserByUsername(username);
    }

}

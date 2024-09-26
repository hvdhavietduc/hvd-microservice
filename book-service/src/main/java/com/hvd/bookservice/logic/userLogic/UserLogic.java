package com.hvd.bookservice.logic.userLogic;

import com.hvd.bookservice.model.user.User;
import com.hvd.bookservice.repository.user.UserRepository;
import com.hvd.kafkamessageservice.auth_book.InsertUserMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class UserLogic {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
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

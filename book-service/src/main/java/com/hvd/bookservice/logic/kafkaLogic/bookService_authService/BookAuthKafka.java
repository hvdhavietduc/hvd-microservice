package com.hvd.bookservice.logic.kafkaLogic.bookService_authService;

import com.hvd.bookservice.logic.userLogic.UserLogic;
import com.hvd.bookservice.model.user.User;
import com.hvd.kafkamessageservice.auth_book.InsertUserMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class BookAuthKafka {
    private final UserLogic userLogic;

    @KafkaListener(topics = "user-created", groupId = "book-service-group")
    public User insertUser(InsertUserMsg insertUserMsg) {
        User user = User.builder()
                .id(insertUserMsg.getId())
                .username(insertUserMsg.getUsername())
                .password(insertUserMsg.getPassword())
                .build();
        return userLogic.saveUser(user);
    }
}

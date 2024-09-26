package com.hvd.authservice.logic.kafkaLogic.authService_bookService;

import com.hvd.authservice.model.user.User;
import com.hvd.kafkamessageservice.auth_book.InsertUserMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class AuthBookKafka {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String CREATE_USER_TOPIC = "user-created";

    public void sendUserCreated(User user) {
        InsertUserMsg msg = new InsertUserMsg(user.getId(), user.getUsername(), user.getPassword());
        kafkaTemplate.send(CREATE_USER_TOPIC, msg);
    }
}

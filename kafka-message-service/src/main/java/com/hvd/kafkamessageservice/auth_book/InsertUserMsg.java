package com.hvd.kafkamessageservice.auth_book;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertUserMsg {
    private String id;
    private String username;
    private String password;

    public InsertUserMsg(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public InsertUserMsg() {
    }
}

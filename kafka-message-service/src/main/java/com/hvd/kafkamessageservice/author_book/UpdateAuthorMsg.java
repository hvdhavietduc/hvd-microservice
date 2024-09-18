package com.hvd.kafkamessageservice.author_book;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateAuthorMsg {
    private String id;
    private String name;

    public UpdateAuthorMsg(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public UpdateAuthorMsg() {
    }
}

package com.hvd.bookservice.request;

import lombok.Data;

@Data
public class UpdateBook {
    private String title;
    private String authorName;
}

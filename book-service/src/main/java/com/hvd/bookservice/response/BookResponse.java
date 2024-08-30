package com.hvd.bookservice.response;

import com.hvd.bookservice.Author;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookResponse {
    private String id;
    private String title;
    private Author author;
}

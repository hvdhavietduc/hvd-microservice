package com.hvd.bookservice;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class Book {
    String id;
    String title;
    String authorId;
}

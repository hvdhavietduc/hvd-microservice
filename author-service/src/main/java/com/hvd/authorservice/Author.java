package com.hvd.authorservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    public String id;
    public String name;
}

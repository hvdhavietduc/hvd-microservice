package com.hvd.bookservice.client;

import com.hvd.bookservice.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author-service")
public interface AuthorServiceClient {

    @GetMapping("/api/authors/{id}")
    Author getAuthorById(@PathVariable("id") String id);
}
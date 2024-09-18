package com.hvd.authorservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hvd.kafkamessageservice.author_book.UpdateAuthorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static String DELETE_AUTHOR_EVENT = "author-deleted";

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.insert(author);
    }

    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);
        kafkaTemplate.send("author-deleted", id);
    }

    @KafkaListener(topics = "update-book", groupId = "author-service-group")
    public Author updateAuthor(UpdateAuthorMsg updateAuthorMsg) throws JsonProcessingException {
        System.out.println(updateAuthorMsg);
        Author author = authorRepository.findById(updateAuthorMsg.getId());
        author.setName(updateAuthorMsg.getName());
        return author;
    }

}
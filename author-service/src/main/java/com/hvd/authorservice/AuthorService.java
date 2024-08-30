package com.hvd.authorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);
        kafkaTemplate.send("author-deleted", id.toString());
    }
}
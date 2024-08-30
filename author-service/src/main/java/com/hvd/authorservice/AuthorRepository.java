package com.hvd.authorservice;

import lombok.Builder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Builder
@Repository
public class AuthorRepository {
    List<Author> authors;

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public Author findByName(String name) {
        return authors.stream().filter(author -> author.getName().equals(name)).findFirst().orElse(null);
    }

    public Author save(Author author) {
        System.out.println(author);
        authors.add(author);
        return author;
    }

    public Author deleteById(String id) {
        Author author = findById(id);
        authors.remove(author);
        return author;
    }


}

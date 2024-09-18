package com.hvd.bookservice;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Builder
@Repository
public class BookRepository {
    List<Book> books;

    public Book findBookById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    public Book deleteBookById(String id) {
        Book book = findBookById(id);
        books.remove(book);
        return book;
    }

    public List<Book> findBooksByTitle(String title) {
        return books.stream().filter(book -> book.getTitle().equals(title)).toList();
    }

    public Book insertBook(Book book) {
        books.add(book);
        return book;
    }

    public List<Book> findAll() {
        return books;
    }

    public List<Book> findByAuthorId(String authorId){
        return books.stream().filter(book -> book.getAuthorId().equals(authorId)).toList();
    }

    public Book updateBookById(String id, String title) {
        Book book = findBookById(id);
        book.setTitle(title);
        return book;
    }
}

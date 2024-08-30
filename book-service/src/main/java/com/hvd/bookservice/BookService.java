package com.hvd.bookservice;

import com.hvd.bookservice.client.AuthorServiceClient;
import com.hvd.bookservice.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorServiceClient authorServiceClient;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookResponse getBookById(String id) {

        Book book = bookRepository.findBookById(id);
        Author author = authorServiceClient.getAuthorById(book.getAuthorId());
        System.out.println(author);
        BookResponse r =  BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(author)
                .build();
        System.out.println(r);
        return r;
    }

    public Book saveBook(Book book) {
        return bookRepository.insertBook(book);
    }

    public void deleteBook(String id) {
        bookRepository.deleteBookById(id);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }


}
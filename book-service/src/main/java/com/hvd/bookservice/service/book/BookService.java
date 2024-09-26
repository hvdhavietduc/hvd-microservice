package com.hvd.bookservice.service.book;

import com.hvd.bookservice.Author;
import com.hvd.bookservice.client.AuthorServiceClient;
import com.hvd.bookservice.logic.bookLogic.BookLogic;
import com.hvd.bookservice.model.book.Book;
import com.hvd.bookservice.response.BookResponse;
import com.hvd.kafkamessageservice.author_book.UpdateAuthorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookLogic bookLogic;

    @Autowired
    private AuthorServiceClient authorServiceClient;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String DELETE_AUTHOR_EVENT = "author-deleted";

    public List<Book> getAllBooks() {
        return bookLogic.findAll();
    }

    public BookResponse getBookById(Long id) {

        Book book = bookLogic.findBookById(id);
        Author author = authorServiceClient.getAuthorById(book.getAuthorId());
        BookResponse r =  BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(author)
                .build();
        return r;
    }

    public Book saveBook(Book book) {
        return bookLogic.insertBook(book);
    }

    public void deleteBook(Long id) {
        bookLogic.deleteBookById(id);
    }


    public Book updateBook(Long id, String title, String authorName) {
        Book book = bookLogic.findBookById(id);
        UpdateAuthorMsg updateAuthorMsg = UpdateAuthorMsg.builder()
                .id(book.getAuthorId())
                .name(authorName)
                .build();
        kafkaTemplate.send("update-book", updateAuthorMsg);
        return bookLogic.updateBookById(id, title);
    }

    @KafkaListener(topics = "author-deleted", groupId = "book-service-group")
    public void handleAuthorDeleted(String authorId) {
        List<Book> books = bookLogic.findByAuthorId(authorId);
        for (Book book : books) {
            bookLogic.deleteBookById(book.getId());
        }
    }


}
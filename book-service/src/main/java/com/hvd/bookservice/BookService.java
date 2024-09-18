package com.hvd.bookservice;

import com.hvd.bookservice.client.AuthorServiceClient;
import com.hvd.bookservice.response.BookResponse;
import com.hvd.kafkamessageservice.author_book.UpdateAuthorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorServiceClient authorServiceClient;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String DELETE_AUTHOR_EVENT = "author-deleted";

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookResponse getBookById(String id) {

        Book book = bookRepository.findBookById(id);
        Author author = authorServiceClient.getAuthorById(book.getAuthorId());
        BookResponse r =  BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(author)
                .build();
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

    public Book updateBook(String id, String title, String authorName) {
        Book book = bookRepository.findBookById(id);
        UpdateAuthorMsg updateAuthorMsg = UpdateAuthorMsg.builder()
                .id(book.getAuthorId())
                .name(authorName)
                .build();
        kafkaTemplate.send("update-book", updateAuthorMsg);
        return bookRepository.updateBookById(id, title);
    }

    @KafkaListener(topics = "author-deleted", groupId = "book-service-group")
    public void handleAuthorDeleted(String authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);
        for (Book book : books) {
            bookRepository.deleteBookById(book.getId());
        }
    }


}
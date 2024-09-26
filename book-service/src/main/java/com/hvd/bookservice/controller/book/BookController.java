package com.hvd.bookservice.controller.book;

import com.hvd.bookservice.request.UpdateBook;
import com.hvd.bookservice.model.book.Book;
import com.hvd.bookservice.response.BookResponse;
import com.hvd.bookservice.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse getBookById(@PathVariable Long id) {
        BookResponse book = bookService.getBookById(id);
        return book;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@PathVariable Long id, @RequestBody UpdateBook book) {
        return bookService.updateBook(id, book.getTitle(), book.getAuthorName());
    }
}
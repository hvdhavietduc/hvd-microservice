package com.hvd.bookservice.logic.bookLogic;

import com.hvd.bookservice.model.book.Book;
import com.hvd.bookservice.repository.book.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLogic {
    private final BookRepository bookRepository;


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    public Book insertBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBookById(
            Long id,
            String title
    ) {
        Book book = findBookById(id);
        book.setTitle(title);
        return bookRepository.save(book);
    }

    public List<Book> findByAuthorId(String authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}

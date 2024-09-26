package com.hvd.bookservice.repository.book;

import com.hvd.bookservice.model.book.Book;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    Book findBookById(Long id);

    List<Book> findByAuthorId(String authorId);
}

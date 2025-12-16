package com.LucasVicentee.repository;

import com.LucasVicentee.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

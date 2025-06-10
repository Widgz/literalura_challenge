package com.literalura.repository;

import com.literalura.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookInfo, Long> {

    @Query("SELECT b FROM BookInfo b WHERE LOWER(b.languages) LIKE %:language%")
    List<BookInfo> findByLanguage(String language);
}

package com.literalura.repository;

import com.literalura.model.AuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorInfo, Long> {
    Optional<AuthorInfo> findByName(String name);

    @Query("SELECT a FROM AuthorInfo a WHERE a.birth_year <= :year AND (a.death_year IS NULL OR a.death_year > :year)")
    List<AuthorInfo> findAuthorsAliveInYear(int year);
}

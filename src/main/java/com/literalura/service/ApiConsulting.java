package com.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.model.*;
import com.literalura.repository.AuthorRepository;
import com.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ApiConsulting {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final String apiAddress = "https://gutendex.com/books/?search=";

    public void bookSearch(String searchedBook) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiAddress + searchedBook))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        ObjectMapper mapper = new ObjectMapper();
        BookResponse books = mapper.readValue(json, BookResponse.class);

        for (Book book : books.results()) {
            for (Author author : book.authors()) {
                var existingAuthor = authorRepository.findByName(author.name());
                AuthorInfo authorInfo;
                if (existingAuthor.isPresent()) {
                    authorInfo = existingAuthor.get();
                } else {
                    AuthorInfo newAuthor = new AuthorInfo();
                    newAuthor.setName(author.name());
                    newAuthor.setBirth_year(author.birth_year());
                    newAuthor.setDeath_year(author.death_year());
                    authorInfo = authorRepository.save(newAuthor);
                }

                BookInfo bookInfo = new BookInfo();
                bookInfo.setTitle(book.title());
                bookInfo.setLanguages(String.join(",", book.languages()));
                bookInfo.setDownload_count(book.download_count());
                bookInfo.setAuthor(authorInfo);

                bookRepository.save(bookInfo);
            }
        }
    }

    public List<BookInfo> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<AuthorInfo> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<AuthorInfo> listAuthorsAliveInYear(int year) {
        return authorRepository.findAuthorsAliveInYear(year);
    }

    public List<BookInfo> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language.toLowerCase());
    }
}

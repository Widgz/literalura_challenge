package com.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.model.Author;
import com.literalura.model.Book;
import com.literalura.model.BookResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ApiConsulting {

    private final String apiAddress = "https://gutendex.com/books/?search=";

    public String bookSearch(String searchedBook) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiAddress + searchedBook))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        ObjectMapper mapper = new ObjectMapper();
        BookResponse books = mapper.readValue(json, BookResponse.class);

        for (int i = 0; i < books.results().size(); i++) {
            Book book = books.results().get(i);
            System.out.println("Title: " + book.title());

            for (int j = 0; j < book.authors().size(); j++) {
                Author author = book.authors().get(j);
                System.out.println("Author: " + author.name());
            }

            System.out.println("Language: " + book.languages());
            System.out.println("Downloads: " + book.download_count());
        }
        return json;
    }
}

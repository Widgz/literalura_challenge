package com.literalura.controller;

import com.literalura.model.AuthorInfo;
import com.literalura.model.BookInfo;
import com.literalura.service.ApiConsulting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class BookController {

    @Autowired
    private ApiConsulting apiConsulting;

    private final Scanner sc = new Scanner(System.in);

    public void bookSearchByTitle() throws IOException, InterruptedException {
        System.out.print("Digite o nome do livro que deseja buscar: ");
        String bookName = sc.nextLine();
        bookName = bookName.replace(" ", "+").replace(",", "+");

        apiConsulting.bookSearch(bookName);
    }

    public void listAllBooks() {
        List<BookInfo> books = apiConsulting.listAllBooks();
        for (BookInfo book : books) {
            System.out.printf("Título: %s | Autor: %s | Idiomas: %s | Downloads: %d%n",
                    book.getTitle(), book.getAuthor().getName(), book.getLanguages(), book.getDownload_count());
        }
    }

    public void listAllAuthors() {
        List<AuthorInfo> authors = apiConsulting.listAllAuthors();
        for (AuthorInfo author : authors) {
            System.out.printf("Nome: %s | Ano de nascimento: %d | Ano de falecimento: %s%n",
                    author.getName(), author.getBirth_year(),
                    author.getDeath_year() != null ? author.getDeath_year() : "N/A");
        }
    }

    public void listAuthorsAliveInYear() {
        System.out.print("Digite o ano para listar autores vivos: ");
        int year = Integer.parseInt(sc.nextLine());
        List<AuthorInfo> authors = apiConsulting.listAuthorsAliveInYear(year);
        for (AuthorInfo author : authors) {
            System.out.printf("Nome: %s | Ano de nascimento: %d%n",
                    author.getName(), author.getBirth_year());
        }
    }

    public void listBooksByLanguage() {
        System.out.print("Digite o idioma para listar livros: ");
        String language = sc.nextLine().toLowerCase();
        List<BookInfo> books = apiConsulting.listBooksByLanguage(language);
        for (BookInfo book : books) {
            System.out.printf("Título: %s | Autor: %s | Idiomas: %s%n",
                    book.getTitle(), book.getAuthor().getName(), book.getLanguages());
        }
    }
}

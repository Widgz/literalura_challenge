package com.literalura.controller;

import com.literalura.service.ApiConsulting;

import java.io.IOException;
import java.util.Scanner;

public class BookController {
    Scanner sc = new Scanner(System.in);

    public void bookSearchByTitle() throws IOException, InterruptedException {
        System.out.print("Digite o nome do livro que deseja buscar: ");
        String bookName = sc.nextLine();
        bookName = bookName.replace(" ", "+");
        bookName = bookName.replace(",", "+");

        var apiConsulting = new ApiConsulting();
        apiConsulting.bookSearch(bookName);
    }
}

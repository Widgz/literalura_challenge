package com.literalura.view;

import com.literalura.controller.BookController;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);

    public void showMenu() throws IOException, InterruptedException {
        System.out.println("1- Buscar livro pelo título");
        System.out.println("2- Listar livros registrados");
        System.out.println("3- Listar autores registrados");
        System.out.println("4- Listar autores vivos em determinado ano");
        System.out.println("5- Listar livros em determinado idioma");
        System.out.print("Digite a opção escolhida: ");

        Integer input = sc.nextInt();
        sc.nextLine();

        if (input == 1) {
            var controller = new BookController();
            controller.bookSearchByTitle();
        }

    }
}

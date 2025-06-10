package com.literalura.view;

import com.literalura.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Menu {

    @Autowired
    private BookController bookController;

    public void showMenu() throws Exception {
        var sc = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1- Buscar livro pelo título");
            System.out.println("2- Listar livros registrados");
            System.out.println("3- Listar autores registrados");
            System.out.println("4- Listar autores vivos em determinado ano");
            System.out.println("5- Listar livros em determinado idioma");
            System.out.println("0- Sair");
            System.out.print("Digite a opção escolhida: ");

            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1 -> bookController.bookSearchByTitle();
                case 2 -> bookController.listAllBooks();
                case 3 -> bookController.listAllAuthors();
                case 4 -> bookController.listAuthorsAliveInYear();
                case 5 -> bookController.listBooksByLanguage();
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}

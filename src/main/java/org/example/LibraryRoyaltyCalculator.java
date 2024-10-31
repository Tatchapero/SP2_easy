package org.example;

import org.example.calculator.*;
import org.example.enums.LiteratureType;

public class LibraryRoyaltyCalculator {
    public static void main(String[] args) {
        Author a1 = new Author("Sussi Bech");
        a1.addTitle(new AudioBook("Hello World!", LiteratureType.FICTION.type, 10, 400));
        a1.addTitle(new PrintedBook("Super-Dev", LiteratureType.COMICS.type, 140, 72));
        a1.addTitle(new PrintedBook("Java 1.0", LiteratureType.NONFICTION.type, 0, 1000));

        printResult(a1);
    }

    public static void printResult(Author author) {
        System.out.println(author.getName() + ": " + author.calculateTotalPay() + "kr");
    }
}

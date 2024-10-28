package org.example;

import org.example.calculator.*;
import org.example.enums.LiteratureType;
import org.example.utility.Helper;

public class LibraryRoyaltyCalculator {
    public static void main(String[] args) {
        Author a1 = new Author(Helper.getRandomFullName());
        a1.addTitle(new AudioBook("Hello World!", LiteratureType.LYRICS.type, Helper.getRandomInt(0, 100), Helper.getRandomInt(100, 3000)));
        a1.addTitle(new PrintedBook("The Lord of the Exceptions", LiteratureType.FICTION.type, Helper.getRandomInt(0, 100), Helper.getRandomInt(100, 3000)));
        a1.addTitle(new PrintedBook("Java 1.0", LiteratureType.NONFICTION.type, Helper.getRandomInt(0, 100), Helper.getRandomInt(100, 3000)));

        printResult(a1);
    }

    public static void printResult(Author author) {
        System.out.println(author.getName() + ": " + author.calculateTotalPay() + "kr");
    }
}

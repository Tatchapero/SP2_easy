package org.example.calculator;

import org.example.enums.LiteratureType;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PrintedBookTest {

    @Test
    void setPages() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setPages(0);

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void setPagesCannotBeLessThanZero() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setPages(-1);

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void calculatePoints() {
        for (String literatureType : LiteratureType.typesAsList()) {
            // Arrange
            int pages = 72;
            double pointsPerMinute = LiteratureType.valueOfPointsPerPage(literatureType);
            int copies = 140;
            PrintedBook printedBook = new PrintedBook("Title", literatureType, copies, pages);
            double expected = pages * pointsPerMinute * copies;

            // Act
            double actual = printedBook.calculatePoints();

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Test
    void calculateLiteraturePoints() {
    }
}
package org.example.calculator;

import org.example.enums.LiteratureType;
import org.junit.jupiter.api.Test;

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
        // Arrange
        var pictureBooksPoints = 3f;
        var comicsPoints = 3f;
        var lyricsPoints = 6f;
        var fictionPoints = 1.7f;
        var nonFictionPoints = 1f;
        PrintedBook printedBook1 = new PrintedBook("Title", "BI", 1, 1);
        PrintedBook printedBook2 = new PrintedBook("Title", "TE", 1, 1);
        PrintedBook printedBook3 = new PrintedBook("Title", "LYRIK", 1, 1);
        PrintedBook printedBook4 = new PrintedBook("Title", "SKØN", 1, 1);
        PrintedBook printedBook5 = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        var actual1 = printedBook1.calculateLiteraturePoints();
        var actual2 = printedBook2.calculateLiteraturePoints();
        var actual3 = printedBook3.calculateLiteraturePoints();
        var actual4 = printedBook4.calculateLiteraturePoints();
        var actual5 = printedBook5.calculateLiteraturePoints();

        // Assert
        assertEquals(pictureBooksPoints, actual1);
        assertEquals(comicsPoints, actual2);
        assertEquals(lyricsPoints, actual3);
        assertEquals(fictionPoints, actual4);
        assertEquals(nonFictionPoints, actual5);
    }
}
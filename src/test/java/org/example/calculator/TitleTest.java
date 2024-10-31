package org.example.calculator;

import org.example.enums.LiteratureType;
import org.example.utility.ConfigFileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleTest {

    @Test
    void setTitle() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setTitle("Untitled");

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void setTitleCannotBeNull() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setTitle(null);

        // Assert
        assertThrowsExactly(NullPointerException.class, actual::run);
    }

    @Test
    void setTitleCannotBeEmpty() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setTitle("");

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void setTitleCannotBeBlank() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setTitle(" ");

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void setTitleCannotBeOverThousandChars() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        StringBuilder overThousandCharacters = new StringBuilder();
        while (overThousandCharacters.length() <= 1000) {
            overThousandCharacters.append("A");
        }
        String title = overThousandCharacters.toString();
        Runnable actual = () -> printedBook.setTitle(title);

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void getAndSetLiteratureType() {
        // Arrange
        var expected = "BI";
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        printedBook.setLiteratureType(expected);

        // Assert
        assertEquals(expected, printedBook.getLiteratureType());
    }

    @Test
    void setLiteratureTypeMustBeValid() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setLiteratureType("FAKE");

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void setCopies() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setCopies(0);

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void setCopiesCannotBeLessThanZero() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setCopies(-1);

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void setRate() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setRate(1);

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void setRateCannotBeLessThanZero() {
        // Arrange
        PrintedBook printedBook = new PrintedBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> printedBook.setRate(-1);

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void calculateRoyalty() {
        for (String literatureType : LiteratureType.typesAsList()) {
            // Arrange
            int copies = 72;
            int pages = 140;
            int durationInMinutes = 400;
            double rate = ConfigFileReader.getRate();

            double expected1 = (pages * (double)LiteratureType.valueOfPointsPerPage(literatureType) * copies) * rate;
            double expected2 = ((durationInMinutes * 0.5f) * (double)LiteratureType.valueOfPointsPerMinute(literatureType) * copies) * rate;

            PrintedBook printedBook = new PrintedBook("Title", literatureType, copies, pages);
            AudioBook audioBook = new AudioBook("Title", literatureType, copies, durationInMinutes);

            // Act
            double actual1 = printedBook.calculateRoyalty();
            double actual2 = audioBook.calculateRoyalty();

            // Assert
            assertEquals(expected1, actual1);
            assertEquals(expected2, actual2);
        }
    }
}
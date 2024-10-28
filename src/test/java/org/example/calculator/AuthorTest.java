package org.example.calculator;

import org.junit.jupiter.api.Test;
import org.example.enums.LiteratureType;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void setAndGetName() {
        // Arrange
        var name = "John Doe";
        var author = new Author("Placeholder");

        // Act
        author.setName(name);

        // Assert
        assertEquals(name, author.getName());
    }

    @Test
    void setNameCannotBeNull() {
        // Arrange
        var expected = NullPointerException.class;

        // Act
        Runnable actual = () -> new Author(null);

        // Assert
        assertThrowsExactly(expected, actual::run);
    }

    @Test
    void setNameCannotBeEmpty() {
        // Arrange
        var expected = IllegalArgumentException.class;

        // Act
        Runnable actual = (() -> new Author(""));

        // Assert
        assertThrowsExactly(expected, actual::run);
    }

    @Test
    void setNameCannotBeBlank() {
        // Arrange
        var expected = IllegalArgumentException.class;

        // Act
        Runnable actual = (() -> new Author(" "));

        // Assert
        assertThrowsExactly(expected, actual::run);
    }

    @Test
    void setNameCannotBeLessThanTwoChars() {
        // Arrange
        var expected = IllegalArgumentException.class;

        // Act
        Runnable actual = (() -> new Author("A"));

        // Assert
        assertThrowsExactly(expected, actual::run);
    }

    @Test
    void setNameCannotBeGreaterThanThousandChars() {
        // Arrange
        var expected = IllegalArgumentException.class;

        // Act
        StringBuilder overThousandCharacters = new StringBuilder();
        while (overThousandCharacters.length() <= 1000) {
            overThousandCharacters.append("A");
        }

        String name = overThousandCharacters.toString();
        Runnable actual = () -> new Author(name);

        // Assert
        assertThrowsExactly(expected, actual::run);
    }

    @Test
    void addTitle() {
        // Arrange
        var author = new Author("John Doe");
        var printedBook = new PrintedBook("Title", LiteratureType.FICTION.type, 1, 1);
        var audioBook = new AudioBook("Song", LiteratureType.LYRICS.type, 1, 1);

        // Act
        Runnable addPrintedBook = () -> author.addTitle(printedBook);
        Runnable addAudioBook = () -> author.addTitle(audioBook);

        // Assert
        assertDoesNotThrow(addPrintedBook::run);
        assertDoesNotThrow(addAudioBook::run);
    }

    @Test
    void addTitleCannotBeNull() {
        // Arrange
        var author = new Author("John Doe");

        // Act
        Runnable addNullBook = () -> author.addTitle(null);

        // Assert
        assertThrows(NullPointerException.class, addNullBook::run);
    }

    @Test
    void calculateTotalPayForPrintedBooks() {
        for (String literatureType : LiteratureType.typesAsList()) {
            // Arrange
            int pages = 72;
            float pointsPerPage = LiteratureType.valueOfPointsPerPage(literatureType);
            int copies = 140;
            float rate = 0.067574f;
            var formula = (pages * pointsPerPage * copies) * rate;
            var expected = BigDecimal.valueOf(formula).setScale(2, RoundingMode.HALF_UP).floatValue();
            var author = new Author("John Doe");


            // Act
            author.addTitle(new PrintedBook("Title", literatureType, copies, pages));
            var actual = author.calculateTotalPay();

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Test
    void calculateTotalPayForAudioBooks() {
        for (String literatureType : LiteratureType.typesAsList()) {
            // Arrange
            int durationInMinutes = 72;
            float pointsPerMinute = LiteratureType.valueOfPointsPerMinute(literatureType);
            int copies = 140;
            float rate = 0.067574f;
            var formula = (durationInMinutes * 0.5 * pointsPerMinute * copies) * rate;
            var expected = BigDecimal.valueOf(formula).setScale(2, RoundingMode.HALF_UP).floatValue();
            var author = new Author("John Doe");


            // Act
            author.addTitle(new AudioBook("Title", literatureType, copies, durationInMinutes));
            var actual = author.calculateTotalPay();

            // Assert
            assertEquals(expected, actual);
        }
    }
}
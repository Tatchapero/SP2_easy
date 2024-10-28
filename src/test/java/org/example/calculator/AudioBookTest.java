package org.example.calculator;

import org.example.enums.LiteratureType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudioBookTest {

    @Test
    void setDurationInMinutes() {
        // Arrange
        AudioBook audioBook = new AudioBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> audioBook.setDurationInMinutes(0);

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void setDurationInMinutesCannotBeLessThanZero() {
        // Arrange
        AudioBook audioBook = new AudioBook("Title", "FAG", 1, 1);

        // Act
        Runnable actual = () -> audioBook.setDurationInMinutes(-1);

        // Assert
        assertThrowsExactly(IllegalArgumentException.class, actual::run);
    }

    @Test
    void calculatePoints() {
        for (String literatureType : LiteratureType.typesAsList()) {
            // Arrange
            int durationInMinutes = 72;
            double pointsPerMinute = LiteratureType.valueOfPointsPerMinute(literatureType);
            int copies = 140;
            AudioBook audioBook = new AudioBook("Title", literatureType, copies, durationInMinutes);
            var expected = (durationInMinutes * 0.5) * pointsPerMinute * copies;

            // Act
            var actual = audioBook.calculatePoints();

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Test
    void calculateLiteraturePoints() {
        // Arrange
        var pictureBooksPoints = 1.5f;
        var comicsPoints = 1.5f;
        var lyricsPoints = 3f;
        var fictionPoints = 0.85f;
        var nonFictionPoints = 0.5f;
        AudioBook audioBook1 = new AudioBook("Title", "BI", 1, 1);
        AudioBook audioBook2 = new AudioBook("Title", "TE", 1, 1);
        AudioBook audioBook3 = new AudioBook("Title", "LYRIK", 1, 1);
        AudioBook audioBook4 = new AudioBook("Title", "SKØN", 1, 1);
        AudioBook audioBook5 = new AudioBook("Title", "FAG", 1, 1);

        // Act
        var actual1 = audioBook1.calculateLiteraturePoints();
        var actual2 = audioBook2.calculateLiteraturePoints();
        var actual3 = audioBook3.calculateLiteraturePoints();
        var actual4 = audioBook4.calculateLiteraturePoints();
        var actual5 = audioBook5.calculateLiteraturePoints();

        // Assert
        assertEquals(pictureBooksPoints, actual1);
        assertEquals(comicsPoints, actual2);
        assertEquals(lyricsPoints, actual3);
        assertEquals(fictionPoints, actual4);
        assertEquals(nonFictionPoints, actual5);
    }
}
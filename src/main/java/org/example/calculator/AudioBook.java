package org.example.calculator;

import org.example.enums.LiteratureType;

public class AudioBook extends Title {
    private int durationInMinutes;

    public AudioBook(String title, String literatureType, int copies, int durationInMinutes) {
        super(title, literatureType, copies);
        setDurationInMinutes(durationInMinutes);
    }

    public void setDurationInMinutes(int durationInMinutes) {
        if (durationInMinutes < 0) { throw new IllegalArgumentException("Duration in minutes cannot be less than 0"); }
        this.durationInMinutes = durationInMinutes;
    }

    /*
    # Calculation of points

    ## Description
    Points are calculated based on:
    - Pages
    - Literature type
    - Copies

    ## Formula
    (durationInMinutes * 0.5) * literatureType * copies

    ## Output
    Points
     */
    @Override
    protected double calculatePoints() {
        return (durationInMinutes * 0.5f) * calculateLiteraturePoints() * copies;
    }

    /*
    # Calculation of literature points

    ## Description
    Literature points are predefined for each type of
    literature.

    | Type                  | Points per minute |
    |-----------------------|-------------------|
    | Picture books (BI)    | 1.5               |
    | Comics (TE)           | 1.5               |
    | Lyrics (LYRIK)        | 3                 |
    | Fiction (SKØN)        | 0.85              |
    | Non-fiction (FAG)     | 0.5               |
     */
    @Override
    protected double calculateLiteraturePoints() {
        return LiteratureType.valueOfPointsPerMinute(getLiteratureType());
    }
}

package org.example.calculator;

import org.example.enums.LiteratureType;

public class PrintedBook extends Title {
    private int pages;

    public PrintedBook(String title, String literatureType, int copies, int pages) {
        super(title, literatureType, copies);
        setPages(pages);
    }

    public void setPages(int pages) {
        if (pages < 0) { throw new IllegalArgumentException("Pages cannot be less than 0"); }
        this.pages = pages;
    }

    /*
    # Calculation of points

    ## Description
    Points are calculated based on:
    - Pages
    - Literature type
    - Copies

    ## Formula
    pages * literatureType * copies

    ## Output
    Points
     */
    @Override
    protected double calculatePoints() {
        return pages * calculateLiteraturePoints() * copies;
    }

    /*
    # Calculation of literature points

    ## Description
    Literature points are predefined for each type of
    literature.

    | Type                  | Points per page   |
    |-----------------------|-------------------|
    | Picture books (BI)    | 3                 |
    | Comics (TE)           | 3                 |
    | Lyrics (LYRIK)        | 6                 |
    | Fiction (SKØN)        | 1.7               |
    | Non-fiction (FAG)     | 1                 |
     */
    @Override
    protected double calculateLiteraturePoints() {
        return LiteratureType.valueOfPointsPerPage(getLiteratureType());
    }
}

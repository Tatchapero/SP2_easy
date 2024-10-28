package org.example.calculator;

import org.example.enums.LiteratureType;
import org.example.utility.ConfigFileReader;

public abstract class Title {
    private String title;
    private String literatureType;
    protected int copies;
    protected double rate;

    public Title(String title, String literatureType, int copies) {
        setTitle(title);
        setLiteratureType(literatureType);
        setCopies(copies);
        setRate(ConfigFileReader.getRate());
    }

    public void setTitle(String title) {
        if (title == null) { throw new NullPointerException("Title cannot be null"); }
        if (title.isEmpty()) { throw new IllegalArgumentException("Title cannot be empty"); }
        if (title.isBlank()) { throw new IllegalArgumentException("Title cannot be blank"); }
        if (title.length() > 1000) { throw new IllegalArgumentException("Title cannot be greater than 1000 characters"); }
        this.title = title;
    }

    public String getLiteratureType() {
        return this.literatureType;
    }

    public void setLiteratureType(String literatureType) {
        if (!LiteratureType.typesAsList().contains(literatureType)) { throw new IllegalArgumentException("Invalid literature type: " + literatureType); }
        this.literatureType = literatureType;
    }

    public void setCopies(int copies) {
        if (copies < 0) { throw new IllegalArgumentException("Copes cannot be less than 0"); }
        this.copies = copies;
    }

    public void setRate(double rate) {
        if (rate < 0) { throw new IllegalArgumentException("Rate cannot be less than 0"); }
        this.rate = rate;
    }

    /*
    # Calculation of royalties

    ## Description
    Authors earn royalties based on points of a Title,
    and the current rate for library books.

    ## Formula
    points * rate

    ## Output
    Royalties in Danish Krone (DDK)
     */
    public double calculateRoyalty() {
        return calculatePoints() * this.rate;
    }

    protected abstract double calculatePoints();
    protected abstract double calculateLiteraturePoints();
}

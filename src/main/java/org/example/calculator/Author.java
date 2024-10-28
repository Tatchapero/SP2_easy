package org.example.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Author {
    private String name;
    private ArrayList<Title> titles;

    public Author(String name) {
        setName(name);
        this.titles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) { throw new NullPointerException("Name cannot be null"); }
        if (name.isEmpty()) { throw new IllegalArgumentException("Name cannot be empty"); }
        if (name.isBlank()) { throw new IllegalArgumentException("Name cannot be blank"); }
        if (name.length() < 2) { throw new IllegalArgumentException("Name cannot be less than 2 characters"); }
        if (name.length() > 1000) { throw new IllegalArgumentException("Name cannot be greater than 1000 characters"); }
        this.name = name;
    }

    public ArrayList<Title> getTitles() {
        return titles;
    }

    public void addTitle(Title title) {
        if (title == null) { throw new NullPointerException("Title cannot be null"); }
        this.titles.add(title);
    }

    /*
    # Calculate total pay

    ## Description
    Calculates royalties for each of the Author's Titles,
    and adds them together.

    ## Output
    Sum of royalties across Titles with 2 decimal
    precision.
     */
    public float calculateTotalPay() {
        float totalPay = 0;

        for (Title t : titles) {
            totalPay += (float)t.calculateRoyalty();
        }

        totalPay = BigDecimal.valueOf(totalPay)
                .setScale(2, RoundingMode.HALF_UP)
                .floatValue();

        return totalPay;
    }
}

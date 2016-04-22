package edu.gordon.cs.bibleanimals;

/**
 * Created by James on 4/13/2016.
 * Representation of an animal
 */
public class Animal {
    private String[] terms;
    private Integer eolID;
    private String color;
    private String textColor;

    /**
     * Constuctor
     * @param terms the various names for this animal
     * @param eolID the Encyclopedia of Life ID for this animal
     * @param color the color for this animal
     * @param textColor the text color for this animal
     */
    public Animal(String terms[], Integer eolID, String color, String textColor) {
        this.terms = terms;
        this.eolID = eolID;
        this.color = color;
        this.textColor = textColor;
    }

    /**
     * Get the terms (names) of this animal
     * @return the names of this animal
     */
    public String[] getTerms() { return terms; };

    /**
     * Get the color of this animal
     * @return the color of this animal
     */
    public String getColor() {
        return color;
    }

    /**
     * Get the text color for this animal
     * @return the text color for this animal
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     * Get the Encyclopedia of Life ID for this animal
     * @return the Encyclopedia of Life ID for this animal
     */
    public Integer getEolID() {
        return eolID;
    }
}

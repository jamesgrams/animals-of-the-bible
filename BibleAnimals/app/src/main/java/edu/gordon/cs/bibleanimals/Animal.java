package edu.gordon.cs.bibleanimals;

/**
 * Created by James on 4/13/2016.
 * Representation of an animal
 */
public class Animal {
    private String[] terms;
    private Integer eolID;

    /**
     * Constuctor
     * @param terms the various names for this animal
     * @param eolID the Encyclopedia of Life ID for this animal
     */
    public Animal(String terms[], Integer eolID) {
        this.terms = terms;
        this.eolID = eolID;
    }

    /**
     * Get the terms (names) of this animal
     * @return the names of this animal
     */
    public String[] getTerms() { return terms; }

    /**
     * Get the terms for this animal as a string with the specified separator
     */
    public String getTermsString(String separator) {
        String animalNames = "";
        for(Integer i = 0; i < this.getTerms().length; i++) {
            if(i > 0) {
                animalNames += separator;
            }
            animalNames += this.getTerms()[i];
        }
        return animalNames;
    }

    /**
     * Get the Encyclopedia of Life ID for this animal
     * @return the Encyclopedia of Life ID for this animal
     */
    public Integer getEolID() {
        return eolID;
    }
}

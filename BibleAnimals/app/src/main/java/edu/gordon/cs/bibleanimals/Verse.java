package edu.gordon.cs.bibleanimals;

import android.support.annotation.NonNull;

/**
 * Class representing a Bible verse
 * A collection of these can be sorted into order of books of the Bible
 * Created by James on 5/5/2016.
 */
public class Verse implements Comparable<Verse> {
    String book;
    Integer chapter;
    Integer verseReference;
    String verse;
    BibleBooks booksOfBible;

    /**
     * Constructor
     * @param reference the reference of the verse
     * @param verse the verse text
     */
    public Verse(String reference, String verse) {
        String tempString;
        try {
            tempString = reference.substring(0, reference.indexOf(":"));
        }
        catch (Exception e) {
            // No colon in the reference -> move on
            tempString = reference;
        }
        try {
            this.book = tempString.substring(0, tempString.lastIndexOf(" "));
            this.chapter = Integer.parseInt(tempString.substring(tempString.lastIndexOf(" ") + 1));
            this.verseReference = Integer.parseInt(reference.substring(reference.indexOf(":") + 1));
        }
        catch (Exception e) {
            // There is no verse (Just a book and chapter)
            this.verseReference = -1;
        }
        this.verse = verse;
        this.booksOfBible = BibleBooks.getInstance();
    }

    /**
     * Get the Reference text
     * @return the Reference
     */
    public String getReference() {
        String reference = book + " " + chapter.toString();
        if(verseReference != -1) reference += ":" + verseReference.toString();
        return reference;
    }

    /**
     * Get the Book
     * @return the book
     */
    public String getBook() {
        return book;
    }

    /**
     * Get the Chapter
     * @return the chapter
     */
    public Integer getChapter() {
        return chapter;
    }

    /**
     * Get the Verse Reference
     * @return the verse reference
     */
    public Integer getVerseReference() {
        return verseReference;
    }


    /**
     * Get the Verse text
     * @return the Verse text
     */
    public String getVerse() {
        return verse;
    }

    /**
     * Compare to another verse
     * @param otherVerse the other verse to compare to
     * @return -1 if less than, 1 if greater than, 0 if same verse
     */
    public int compareTo(@NonNull Verse otherVerse) {
        Integer myIndex = this.booksOfBible.getBooks().indexOf(this.book);
        Integer otherIndex = this.booksOfBible.getBooks().indexOf(otherVerse.getBook());
        if(myIndex < otherIndex) return -1;
        if(otherIndex < myIndex) return 1;
        // In the same book
        if(this.chapter < otherVerse.getChapter()) return -1;
        if(otherVerse.getChapter() < this.chapter) return 1;
        // In the same chapter
        if(this.verseReference < otherVerse.getVerseReference()) return -1;
        if(otherVerse.getVerseReference() < this.verseReference) return 1;
        // Same verse
        return 0;
    }
}

package edu.gordon.cs.bibleanimals;

import java.util.ArrayList;

/**
 * Contains the books of the Bible
 * This class follows the Singleton pattern
 * Created by James on 5/5/2016.
 */
public class BibleBooks {
    public static BibleBooks instance = null;
    private ArrayList<String> booksOfBible;

    /**
     * Get the instance of this class
     * @return the single instance of this class
     */
    public static BibleBooks getInstance() {
        if(instance == null) {
            instance = new BibleBooks();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private BibleBooks() {
        this.booksOfBible = new ArrayList<>();
        booksOfBible.add("Genesis");
        booksOfBible.add("Exodus");
        booksOfBible.add("Leviticus");
        booksOfBible.add("Numbers");
        booksOfBible.add("Deuteronomy");
        booksOfBible.add("Joshua");
        booksOfBible.add("Judges");
        booksOfBible.add("Ruth");
        booksOfBible.add("1 Samuel");
        booksOfBible.add("2 Samuel");
        booksOfBible.add("1 Kings");
        booksOfBible.add("2 Kings");
        booksOfBible.add("1 Chronicles");
        booksOfBible.add("2 Chronicles");
        booksOfBible.add("Ezra");
        booksOfBible.add("Nehemiah");
        booksOfBible.add("Esther");
        booksOfBible.add("Job");
        booksOfBible.add("Psalm");
        booksOfBible.add("Proverbs");
        booksOfBible.add("Ecclesiastes");
        booksOfBible.add("Song of Solomon");
        booksOfBible.add("Isaiah");
        booksOfBible.add("Jeremiah");
        booksOfBible.add("Lamentations");
        booksOfBible.add("Ezekiel");
        booksOfBible.add("Daniel");
        booksOfBible.add("Hosea");
        booksOfBible.add("Joel");
        booksOfBible.add( "Amos");
        booksOfBible.add("Obadiah");
        booksOfBible.add("Jonah");
        booksOfBible.add("Micah");
        booksOfBible.add("Nahum");
        booksOfBible.add("Habakkuk");
        booksOfBible.add("Zephaniah");
        booksOfBible.add("Haggai");
        booksOfBible.add("Zechariah");
        booksOfBible.add("Malachi");
        booksOfBible.add("Matthew");
        booksOfBible.add("Mark");
        booksOfBible.add("Luke");
        booksOfBible.add("John");
        booksOfBible.add("Acts");
        booksOfBible.add("Romans");
        booksOfBible.add("1 Corinthians");
        booksOfBible.add("2 Corinthians");
        booksOfBible.add("Galatians");
        booksOfBible.add("Ephesians");
        booksOfBible.add("Philippians");
        booksOfBible.add("Colossians");
        booksOfBible.add("1 Thessalonians");
        booksOfBible.add("2 Thessalonians");
        booksOfBible.add("1 Timothy");
        booksOfBible.add("2 Timothy");
        booksOfBible.add("Titus");
        booksOfBible.add("Philemon");
        booksOfBible.add("Hebrews");
        booksOfBible.add("James");
        booksOfBible.add("1 Peter");
        booksOfBible.add("2 Peter");
        booksOfBible.add("1 John");
        booksOfBible.add("2 John");
        booksOfBible.add("3 John");
        booksOfBible.add("Jude");
        booksOfBible.add("Revelation");
    }

    /**
     * Get the books of the Bible
     * @return the books of the Bible
     */
    public ArrayList<String> getBooks() { return this.booksOfBible; }
}

package edu.gordon.cs.bibleanimals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Representation of an animal
 * Created by James on 4/13/2016.
 */
public class Animal {
    private String[] terms;
    private Integer eolID;
    private String category;
    private ArrayList<Verse> verses;
    // This is only here in order to avoid overflowing the heap -> this can be persistent
    private ArrayList<String> books;
    private TreeMap<String, String> description;
    private String scientificName;
    private Bitmap image;

    /**
     * Constuctor
     * @param terms the various names for this animal
     * @param eolID the Encyclopedia of Life ID for this animal
     * @param category the category of this animal
     */
    public Animal(String terms[], Integer eolID, String category) {
        this.terms = terms;
        this.eolID = eolID;
        this.category = category;
    }

    /**
     * Fetch information needing for sorting by books of the Bible
     */
    public void fetchSortingInformation() {
        this.setVerses(false);
        this.books = new ArrayList<>();
        for(int i = 0; i < this.verses.size(); i++) {
            this.books.add(this.verses.get(i).getBook());
        }
        this.verses = null;
    }


    /**
     * Fetch all information needed to display comprehensive
     * information about this animal
     */
    public void fetchInformation() {
        // Fetch the animal information from online
        this.setDetails();
        // Fetch the Bible verses from online
        this.setVerses(true);
    }

    /**
     * Clear out the animal information to save space on the heap
     */
    public void clear() {
        this.verses = null;
        this.description = null;
        this.scientificName = null;
        this.image = null;
        System.gc();
    }

    /**
     * Get the category of this animal
     * @return the category of this animal
     */
    public String getCategory() { return category; }

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
     * Get the books of this animal
     * @return the books of this animal
     */
    public ArrayList<String> getBooks() { return books; }

    /**
     * Set the books of this animal
     * @param books the books of this animal
     */
    public void setBooks(ArrayList<String> books) { this.books = books; }

    /**
     * Get the verses of this animal
     * @return the category of this animal
     */
    public ArrayList<Verse> getVerses() { return verses; }

    /**
     * Get the description of this animal
     * @return the description of this animal
     */
    public TreeMap<String, String> getDescription() { return description; }

    /**
     * Get the scientific name of this animal
     * @return the scientific of this animal
     */
    public String getScientificName() { return scientificName; }

    /**
     * Get the image of this animal
     * @return the image of this animal
     */
    public Bitmap getImage() { return image; }

    /**
     * Set the image for the animal
     * @param urlString the url to get the image from
     * Note: This method should only be called from WITHIN AN ASYNCHRONOUS TASK
     */
    public void setImage(String urlString) {
        Bitmap image = null;
        try {
            URL url = new URL(urlString);
            //http://stackoverflow.com/questions/5776851/load-image-from-url
            URLConnection v = url.openConnection();
            InputStream i = v.getInputStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
            BitmapFactory.Options finalOptions = new BitmapFactory.Options();
            // The first time we connect, we will just check the size
            // This way, we won't load an image too big into the heap
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(i, null, options);
            if(options.outWidth > SIZE_TO_SCALE_IMAGE) {
                finalOptions.inSampleSize = SCALE_DIVIDE;
            }

            url = new URL(urlString);
            v = url.openConnection();
            i = v.getInputStream();
            image = BitmapFactory.decodeStream(i, null, finalOptions);
        }
        catch (Exception e) {
            // Could not fetch an image
            // This is OK and is checked for in AnimalActivity
        }

        this.image = image;
    }

    /**
     * Set the details for this animal that are fetched from the EOL
     * Note: This method should only be called from WITHIN AN ASYNCHRONOUS TASK
     */
    public void setDetails() {
        String json = "";
        try {
            URL url = new URL("http://eol.org/api/pages/"+eolID+".json?details=true&vetted=1" +
                    "&images_per_page=1&videos_per_page=0&sounds_per_page=0&maps_per_page=0" +
                    "&texts_per_page=75&texts_page=1&subjects=all&licenses=all&details=true" +
                    "&common_names=false&synonyms=false&references=false&taxonomy=true" +
                    "&language=en");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            while ((inputLine = buf.readLine()) != null)
                json += inputLine;
            in.close();
        }
        catch (Exception e) {
            // Could not fetch the details
            json = null;
        }
        this.description = new TreeMap<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("dataObjects");
            //JSONObject dataObject = array.getJSONObject(0);
            JSONObject imageObject = array.getJSONObject(array.length() - 1);
            JSONObject taxonConcept = jsonObject.getJSONArray("taxonConcepts").getJSONObject(0);
            // http://stackoverflow.com/questions/4432560/remove-html-tags-from-string-using-java
            for(int i = 0; i < array.length() - 1; i++) {
                JSONObject current = array.getJSONObject(i);
                try {
                    String subject = current.getString("subject");
                    subject = subject.substring(subject.lastIndexOf("#") + 1);
                    for(int j = 1; j < subject.length(); j++ ) {
                        if(Character.isUpperCase(subject.charAt(j))) {
                            subject = subject.substring(0, j) + " " + subject.substring(j);
                            j++;
                        }
                    }
                    if (!description.containsKey(subject)) {
                        String language = current.getString("language");
                        if (language.equals("en") && !subject.equals("Wikipedia")) {
                            String des = current.getString("description");
                            description.put(subject, des);
                        }
                    }
                }
                catch (Exception e) {
                    // The subject or description is not present in the JSON object
                    // from EOL or it is not formatted correctly
                    // This is OK, we will just skip it and not include it.
                }

            }

            String scientificName = taxonConcept.getString("canonicalForm");
            String imageUrl = imageObject.getString("eolMediaURL");
            this.scientificName = scientificName;

            this.setImage(imageUrl);
        }
        catch (Exception e) {
            this.scientificName = "";
            this.description = null;
            // This is also checked for in Animal Activity
        }
    }

    /**
     * Set the verses view based on the response from the search on Biblia
     * @param verseText whether or not to load the text for the verse
     * Note: This method should only be called from WITHIN AN ASYNCHRONOUS TASK
     */
    public void setVerses(boolean verseText) {
        String urlString = "http://api.biblia.com/v1/bible/search/LEB.js?query="
                + terms[0].replaceAll(" ","") + "&key=b23da4e7ad84e911a8f2f3d1f46be194";
        if(!verseText) {
            urlString += "&preview=none";
        }
        String json = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            while ((inputLine = buf.readLine()) != null)
                json += inputLine;
            in.close();
        }
        catch (Exception e) {
            // Could not fetch the verses
            json = null;
        }
        this.verses = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultArray = jsonObject.getJSONArray("results");

            for(Integer i = 0; i < resultArray.length(); i++) {
                JSONObject currentObject = resultArray.getJSONObject(i);
                // http://stackoverflow.com/questions/1529068/is-it-possible-to-have-multiple-styles-inside-a-textview
                String title = currentObject.getString("title");
                switch (this.terms[0]) {
                    case "Bear":
                        if (!Arrays.asList(BEAR_VERSES).contains(title)) {
                            continue;
                        }
                        break;
                    case "Swallow":
                        if (!Arrays.asList(SWALLOW_VERSES).contains(title)) {
                            continue;
                        }
                        break;
                    case "Fly":
                        if (!Arrays.asList(FLY_VERSES).contains(title)) {
                            continue;
                        }
                        break;
                }
                String preview = "";
                if(verseText) {
                    preview = currentObject.getString("preview");
                }
                Verse verse = new Verse(title, preview);
                verses.add(verse);
            }
        }
        catch (Exception e) {
            // An error occurred while parsing the verses. This is OK
            // This is checked in Animal Activity.
            this.verses = null;
        }
    }

    //These lists are for animals with names that have multiple meaning
    //We are essentially hard coding the verses that they are in, so that
    //we don't get any puns
    private static final String[] BEAR_VERSES = new String[]{"Hosea 13:8", "Proverbs 17:12", "Proverbs 28:15",
            "Job 9:9", "Job 38:32", "Isaiah 59:11", "1 Samuel 17:34", "Amos 5:19", "Revelation 13:2", "Lamentations 3:10",
            "2 Kings 2:24", "Daniel 7:5", "2 Samuel 17:8"};
    private static final String[] FLY_VERSES = new String[]{"Isaiah 7:8"};
    private static final String[] SWALLOW_VERSES = new String[]{"Psalm 84:3", "Proverbs 26:2", "Jeremiah 8:7"};

    private static final int SIZE_TO_SCALE_IMAGE = 3000;
    private static final int SCALE_DIVIDE = 2;

}

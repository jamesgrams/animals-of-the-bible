package edu.gordon.cs.bibleanimals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Activity for viewing an animal
 */
public class AnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        // Get the passed intent
        Intent intent = getIntent();
        Animals animals = Animals.getInstance();
        TreeMap<String, Animal> animalsList = animals.get();
        String animalName = intent.getStringExtra(MainActivity.ANIMAL);
        // Get the appropriate animal object for this animal
        Animal animal = animalsList.get(animalName);

        // Begin filling in data for this animal
        String animalNames = animal.getTermsString(", ");

        TextView textView = (TextView) findViewById(R.id.nameText);
        textView.setText(animalNames);

        // Fetch the animal information from online
        new EolOnlineContentTask().execute(animal.getEolID());
        // Fetch the Bible verses from online
        new BibleTask().execute(animalNames);

        Toast.makeText(this, "Loading Information", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Act as the back button
        if (id == R.id.backLabel) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Toggle between the verses and description view
     * @param view the current view
     */
    public void toggleViews(View view) {
        LinearLayout descriptionLayout = (LinearLayout) findViewById(R.id.descriptionLayout);
        Button descriptionButton = (Button) findViewById(R.id.descriptionButton);
        LinearLayout versesLayout = (LinearLayout) findViewById(R.id.versesLayout);
        Button versesButton = (Button) findViewById(R.id.versesButton);
        if(descriptionButton.isEnabled()) {
            descriptionButton.setEnabled(false);
            versesButton.setEnabled(true);
            versesLayout.setVisibility(View.GONE);
            descriptionLayout.setVisibility(View.VISIBLE);
        }
        else {
            versesButton.setEnabled(false);
            descriptionButton.setEnabled(true);
            descriptionLayout.setVisibility(View.GONE);
            versesLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set the animal's image
     * @param image the bitmap to display for the animal
     */
    public void setAnimalImage(Bitmap image) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(image);
    }

    /**
     * Set the animal's details based on information from EOL
     * @param json the JSON response for the animal from EOL
     */
    public void setAnimalDetails(String json) {
        TextView descriptionText = (TextView) findViewById(R.id.descriptionText);
        TextView scientificNameText = (TextView) findViewById(R.id.scientificNameText);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject = jsonObject.getJSONArray("dataObjects").getJSONObject(0);
            JSONObject imageObject = jsonObject.getJSONArray("dataObjects").getJSONObject(1);
            JSONObject taxonConcept = jsonObject.getJSONArray("taxonConcepts").getJSONObject(0);
            // http://stackoverflow.com/questions/4432560/remove-html-tags-from-string-using-java
            String description = dataObject.getString("description").replaceAll("\\<.*?>", "").
                    replaceAll("&nbsp;", "");
            String scientificName = taxonConcept.getString("canonicalForm");
            String imageUrl = imageObject.getString("eolMediaURL");
            descriptionText.setText(Html.fromHtml(description));
            scientificNameText.setText(scientificName);

            new ImageTask().execute(imageUrl);
            Toast.makeText(this, "Loading Image", Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e) {
            descriptionText.setText(json);
            scientificNameText.setText("");
        }

    }

    /**
     * Set the verses view based on the response from the search on Biblia
     * @param json the JSON response by Biblia
     */
    public void setVerses(String json) {
        //TextView versesText = (TextView) findViewById(R.id.versesText);
        //versesText.setVisibility(View.GONE);

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultArray = jsonObject.getJSONArray("results");
            ArrayList<String> references = new ArrayList<>();
            ArrayList<Object> verses = new ArrayList<>();
            for(Integer i = 0; i < resultArray.length(); i++) {
                JSONObject currentObject = resultArray.getJSONObject(i);
                // http://stackoverflow.com/questions/1529068/is-it-possible-to-have-multiple-styles-inside-a-textview
                references.add(currentObject.getString("title"));
                ArrayList<String> child = new ArrayList<>();
                child.add(currentObject.getString("preview"));
                verses.add(child);
            }

            ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
            expandableListView.setGroupIndicator(null);
            expandableListView.setClickable(true);

            MyExpandableAdapter adapter = new MyExpandableAdapter(references, verses);
            adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
            expandableListView.setAdapter(adapter);
        }
        catch (JSONException e) {
            //versesText.setText(json);
        }

    }

    /**
     * This private class is used for requesting an image
     */
    private class ImageTask extends AsyncTask<String, Void, Bitmap> {
        /**
         * Fetch an image from a URL
         * @param urls the URL to fetch the image from
         * @return the Image
         */
        protected Bitmap doInBackground(String... urls) {
            Bitmap image = null;
            try {
                URL url = new URL(urls[0]);
                //http://stackoverflow.com/questions/5776851/load-image-from-url
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
            catch (Exception e) {
                // Display error image
            }

            return image;
        }

        protected void onPostExecute(Bitmap image) {
            setAnimalImage(image);
        }
    }

    /**
     * This private class is for requesting information from the Encyclopedia of Life
      */
    private class EolOnlineContentTask extends AsyncTask<Integer, Void, String> {
        /**
         * Fetch information for an animal
         * @param ids the eol id for the animal to fetch data from
         * @return the JSON response by the eol
         */
        protected String doInBackground(Integer... ids) {
            String eolID = ids[0].toString();
            String json = "";
            try {
                URL url = new URL("http://eol.org/api/pages/"+eolID+".json?details=true&vetted=1");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                String inputLine;
                while ((inputLine = buf.readLine()) != null)
                    json += inputLine;
                in.close();
            }
            catch (Exception e) {
                json =  "Error loading animal description. Are you connected to the internet?";
            }

            return json;
        }

        protected void onPostExecute(String json) {
            setAnimalDetails(json);
        }
    }

    /**
     * This private class is for requesting Bible verses based on a given search term
      */
    private class BibleTask extends AsyncTask<String, Void, String> {
        /**
         * Fetch the Bible verses that an animal is in
         * @param terms the term to search Biblia for
         * @return the JSON response from Biblia
         */
        protected String doInBackground(String... terms) {
            String urlString = "http://api.biblia.com/v1/bible/search/LEB.js?query="
                    + terms[0].replaceAll(" ","") + "&key=b23da4e7ad84e911a8f2f3d1f46be194";
            String verses = "";
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                String inputLine;
                while ((inputLine = buf.readLine()) != null)
                    verses += inputLine;
                in.close();
            }
            catch (Exception e) {
                verses = "Error loading Bible verses. Are you connected to the internet?";
            }

            return verses;
        }

        protected void onPostExecute(String json) {
            setVerses(json);
        }
    }
}

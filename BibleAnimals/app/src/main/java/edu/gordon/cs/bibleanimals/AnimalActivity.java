package edu.gordon.cs.bibleanimals;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * Activity for viewing an animal
 */
public class AnimalActivity extends AppCompatActivity {
    private AnimalTask task;

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

        task = new AnimalTask();
        task.execute(animal);

        Toast.makeText(this, "Loading Information", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animal, menu);
        return true;
    }

    @Override
    public void onStop() {
        if(task != null) task.cancel(true);
        super.onStop();
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
     * Set up the view based on an animal
     * @param animal the animal to display information for
     */
    public void setUpView(Animal animal) {
        TextView scientificNameText = (TextView) findViewById(R.id.scientificNameText);

        // Set up the about section
        if(animal.getDescription() != null) {
            ArrayList<String> set = new ArrayList<>(animal.getDescription().keySet());
            ArrayList<String> collection = new ArrayList<>(animal.getDescription().values());
            ArrayList<String> subjects = new ArrayList<>();
            ArrayList<Object> descriptions = new ArrayList<>();
            subjects.addAll(set);
            for (int i = 0; i < collection.size(); i++) {
                ArrayList<String> child = new ArrayList<>();
                child.add(collection.get(i));
                descriptions.add(child);
            }

            ExpandableListView expandableListViewAbout = (ExpandableListView) findViewById(R.id.expandableListViewAbout);
            expandableListViewAbout.setClickable(true);
            BibleAnimalsExpandableAdapter adapterAbout = new BibleAnimalsExpandableAdapter(subjects, descriptions, false, true, this);
            adapterAbout.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
            expandableListViewAbout.setAdapter(adapterAbout);
        }

        // Set up the scientific name - OK, when an error is thrown, this is just set to ""
        scientificNameText.setText(animal.getScientificName());

        // Set up the image view
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if(animal.getImage() != null) {
            imageView.setImageBitmap(animal.getImage());
        }

        // Set up the verses section
        ArrayList<Verse> allVerses = animal.getVerses();
        if(allVerses != null) {
            Collections.sort(allVerses);
            ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
            expandableListView.setClickable(true);

            ArrayList<String> references = new ArrayList<>();
            ArrayList<Object> verses = new ArrayList<>();
            for (int i = 0; i < allVerses.size(); i++) {
                references.add(allVerses.get(i).getReference());
                ArrayList<String> child = new ArrayList<>();
                child.add(allVerses.get(i).getVerse());
                verses.add(child);
            }

            BibleAnimalsExpandableAdapter adapter = new BibleAnimalsExpandableAdapter(references, verses, false, false, this);
            adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
            expandableListView.setAdapter(adapter);
        }

        // Some information was not loaded -> Display an error toast
        if(animal.getVerses() == null)
            Toast.makeText(this, R.string.could_not_load_verses,Toast.LENGTH_LONG).show();
        if(animal.getDescription() == null)
            Toast.makeText(this, R.string.could_not_load_about,Toast.LENGTH_LONG).show();
        if(animal.getImage() == null)
            Toast.makeText(this, R.string.could_not_load_picture,Toast.LENGTH_LONG).show();
    }

    /**
     * This private class is for waiting for the information that animal fetches
     */
    private class AnimalTask extends AsyncTask<Animal, Void, Animal> {
        /**
         * Fetch information for an animal
         * @param animals the animal to fetch data for
         * @return the animal object to call setUpView for
         */
        protected Animal doInBackground(Animal... animals) {
            Animal animal = animals[0];
            animal.fetchInformation();
            return animal;
        }

        protected void onPostExecute(Animal animal) {
            setUpView(animal);
        }
    }


}

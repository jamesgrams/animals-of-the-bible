package edu.gordon.cs.bibleanimals;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Main activity class
 */
public class MainActivity extends AppCompatActivity {
    private boolean categoriesDisplayed;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        categoriesDisplayed = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        Animals animals = Animals.getInstance();

        boolean load =  animals.loadBooks(context);
        if(load)  animals.setGotBooks(true);

        setDisplayToCategories();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // Act as the back button
        if (id == R.id.settingsLabel) {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
            return true;
        }
        else if(id == R.id.sortLabel) {
            toggleSorts();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set the display to be sorted by categories
     */
    public void setDisplayToCategories() {
        Animals animals = Animals.getInstance();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.listView);
        expandableListView.setClickable(true);

        ArrayList<String> animalCategories = new ArrayList<>();
        ArrayList<Object> animalNames = new ArrayList<>();
        for(int i = 0; i < animals.getCategories().size(); i++) {
            String category = animals.getKeysCategories().get(i);
            animalCategories.add(category);
            ArrayList<String> animalArray = animals.getKeys(animals.getCategories().get(category));
            animalNames.add(animalArray);
        }

        BibleAnimalsExpandableAdapter adapter = new BibleAnimalsExpandableAdapter(animalCategories,
                animalNames, true, false, this);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableListView.setAdapter(adapter);
    }

    /**
     * Set the display to be sorted by books
     */
    public void setDisplayToBooks() {
        Animals animals = Animals.getInstance();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.listView);
        expandableListView.setClickable(true);
        BibleBooks booksOfBible = BibleBooks.getInstance();
        animals.populateBooksMap();
        ArrayList<String> animalBooks = new ArrayList<>();
        ArrayList<Object> animalNames = new ArrayList<>();
        for(int i = 0; i < booksOfBible.getBooks().size(); i++) {
            String book = booksOfBible.getBooks().get(i);
            animalBooks.add(book);
            TreeMap<String, Animal> treeMap = animals.getBooksOfBible().get(book);
            ArrayList<String> animalArray = animals.getKeys(treeMap);
            animalNames.add(animalArray);
        }

        BibleAnimalsExpandableAdapter adapter = new BibleAnimalsExpandableAdapter(animalBooks,
                animalNames, true, false, this);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableListView.setAdapter(adapter);
    }

    /**
     * Toggle between the two methods of sorting
     */
    public void toggleSorts() {
        Animals animals = Animals.getInstance();
        MenuItem menuItem = menu.findItem(R.id.sortLabel);
        if(categoriesDisplayed) {
            if(animals.getGotBooks()) {
                this.setDisplayToBooks();
                menuItem.setTitle(R.string.sort_by_categories);
                categoriesDisplayed = false;
            }
            else {
                Toast.makeText(this, R.string.need_to_download_books_error,
                        Toast.LENGTH_LONG).show();
            }
        }
        else {
            this.setDisplayToCategories();
            menuItem.setTitle(R.string.sort_by_books);
            categoriesDisplayed = true;
        }
    }

    public final static String ANIMAL = "edu.gordon.cs.bibleshare.ANIMAL";
}

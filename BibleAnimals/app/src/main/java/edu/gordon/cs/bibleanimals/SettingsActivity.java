package edu.gordon.cs.bibleanimals;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activity for viewing settings
 */
public class SettingsActivity extends AppCompatActivity {
    Animals animals;
    Context context;
    DownloadTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = this;

        animals = Animals.getInstance();

        TextView databaseIndication = (TextView) findViewById(R.id.databaseIndication);
        if(animals.getGotBooks()) {
            databaseIndication.setText(R.string.download_message);
        }
        else {
            databaseIndication.setText(R.string.no_download_message);
        }

        TextView creditsText = (TextView) findViewById(R.id.creditsText);
        creditsText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onStop() {
        if(task != null) task.cancel(true);
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Act as the back button
        if (id == R.id.backLabelSettings) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Begin downloading the data
     * @param view the current view
     */
    public void downloadData(View view) {
        Button button = (Button) findViewById(R.id.button);
        button.setEnabled(false);
        task = new DownloadTask();
        task.execute();
    }

    /**
     * This private class is for downloading the animal database
     */
    private class DownloadTask extends AsyncTask<Void, String, Boolean> {
        protected Boolean doInBackground(Void... nothing) {
            try {
                ArrayList<Animal> animalObjects = animals.getValues(animals.get());
                Integer size = animalObjects.size();
                for (Integer i = 1; i < size+1; i++) {
                    String string = getResources().getString(R.string.downloading_book_data);
                    publishProgress(string + " " + i.toString() + "/" + size.toString());
                    animalObjects.get(i-1).fetchSortingInformation();
                }
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(String... message) {
            TextView downloadStatus = (TextView) findViewById(R.id.downloadStatus);
            downloadStatus.setText(message[0]);
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success) {
                animals.saveBooks(context);
                TextView databaseIndication = (TextView) findViewById(R.id.databaseIndication);
                databaseIndication.setText(R.string.download_message);
                TextView downloadStatus = (TextView) findViewById(R.id.downloadStatus);
                downloadStatus.setText(R.string.downloading_complete);
                Button button = (Button) findViewById(R.id.button);
                button.setEnabled(true);
                animals.setGotBooks(true);
            }
            else {
                try {
                    TextView downloadStatus = (TextView) findViewById(R.id.downloadStatus);
                    downloadStatus.setText(R.string.downloading_error);
                    Button button = (Button) findViewById(R.id.button);
                    button.setEnabled(true);
                }
                catch (Exception e) {
                    // Left activity
                }
            }
        }
    }
}

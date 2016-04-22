package edu.gordon.cs.bibleanimals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Map;
import java.util.TreeMap;

/**
 * Main activity class
 */
public class MainActivity extends AppCompatActivity {
    public final static String ANIMAL= "edu.gordon.cs.bibleshare.ANIMAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animals animals = Animals.getInstance();
        TreeMap<String, Animal> animalsList = animals.get();
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

        final Context context = this;
        Integer count = 0;
        LinearLayout linearLayout = null;
        // Create all the buttons
        for(final Map.Entry<String, Animal> entry : animalsList.entrySet()) {
            if(count % 2 == 0) {
                linearLayout = new LinearLayout(this);
                layout.addView(linearLayout);
            }
            Button button = new Button(this);
            button.setText(entry.getKey());

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AnimalActivity.class);
                    intent.putExtra(ANIMAL, entry.getKey());
                    startActivity(intent);
                }
            };
            button.setOnClickListener(clickListener);
            LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 0.5f);
            button.setLayoutParams(layoutParams);
            linearLayout.addView(button);
            count ++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}

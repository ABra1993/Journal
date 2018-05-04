package example.promo.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {
    /** The following class retrieves user's input and adds a new journal entry to SQL database. */

    // initializes properties...
    String drawableMood;
    String addFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry(View view) {

        // retrieves entry database
        EntryDatabase instance = EntryDatabase.getInstance(this);

        // retrieves input data from view
        ImageView favourites = findViewById(R.id.addFavourites);
        if (favourites.getVisibility() == View.VISIBLE) {
            addFavourites = "yes";
        } else {
            addFavourites = "no";
        }
        View parentView = (View) view.getParent();
        EditText titleView = parentView.findViewById(R.id.titleSubmit);
        EditText contentView = parentView.findViewById(R.id.contentSubmit);
        String title = titleView.getText().toString();
        String content = contentView.getText().toString();
        String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        // creates new journal entry
        JournalEntry journalEntry = new JournalEntry(title, content, drawableMood, timeStamp, addFavourites);

        // insert journal entry in table
        instance.insert(journalEntry);

        // returns to main activity
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // retrieves and stores emoticon the user has clicked on
    public void emoticonClicked(View view) {

        ImageView mood = findViewById(R.id.mood);

        switch(view.getId()) {
            case R.id.positiveMood:
                drawableMood = "positive";
                mood.setImageResource(R.drawable.positive);
                mood.setVisibility(View.VISIBLE);
                break;
            case R.id.neutralMood:
                drawableMood = "neutral";
                mood.setImageResource(R.drawable.neutral);
                mood.setVisibility(View.VISIBLE);
                break;
            case R.id.negativeMood:
                drawableMood = "negative";
                mood.setImageResource(R.drawable.negative);
                mood.setVisibility(View.VISIBLE);
                break;
            case R.id.sadMood:
                drawableMood = "sad";
                mood.setImageResource(R.drawable.sad);
                mood.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void addFavourites(View view) {

        ImageView favourites = findViewById(R.id.addFavourites);

        if (favourites.getVisibility() == View.VISIBLE) {
            favourites.setVisibility(View.INVISIBLE);
        } else {
            favourites.setVisibility(View.VISIBLE);
        }
    }
}

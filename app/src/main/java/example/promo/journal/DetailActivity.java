package example.promo.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {
    /** The following class shows the title, timestamp, content en mood of a journal entry. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // retrieves info journal entry
        Intent intent = getIntent();
        Bundle retrievedInfo = intent.getBundleExtra("bundle");

        // set title
        TextView title = findViewById(R.id.titleShow);
        title.setText(retrievedInfo.getString("title"));

        // set content
        TextView content = findViewById(R.id.contentShow);
        content.setText(retrievedInfo.getString("content"));

        // set timestamp
        TextView timestamp = findViewById(R.id.timestamp);
        timestamp.setText(retrievedInfo.getString("timestamp"));

        // set favourite
        ImageView favourites = findViewById(R.id.favourites);
        switch (retrievedInfo.getString("favourites")) {
            case "yes":
                favourites.setVisibility(View.VISIBLE);
                break;
            case "no":
                favourites.setVisibility(View.INVISIBLE);
                break;
        }

        // set mood
        ImageView mood = findViewById(R.id.moodShow);
        String moodRow = retrievedInfo.getString("mood");
        if (moodRow != null) {

            switch (moodRow) {
                case "positive":
                    mood.setImageResource(R.drawable.positive);
                    break;
                case "neutral":
                    mood.setImageResource(R.drawable.neutral);
                    break;
                case "negative":
                    mood.setImageResource(R.drawable.negative);
                    break;
                case "sad":
                    mood.setImageResource(R.drawable.sad);
                    break;
            }
        } else {
            mood.setImageResource(R.drawable.negative);
        }
    }
}

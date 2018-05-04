package example.promo.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter{
    /** The following class binds view of main activity to the adapter and shows the title,
     * timestamp and mood of the journal entries in the main activity. */

    // constructor
    public EntryAdapter(Context context, Cursor cursor, int layout, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // initialize properties...
        int columnIndex;

        // retrieves title from database and shows it in main activity
        columnIndex = cursor.getColumnIndex("title");
        String titleRow = cursor.getString(columnIndex);
        TextView title = view.findViewById(R.id.titleRow);
        title.setText(titleRow);

        // retrieves timestamp from database and shows it in main activity
        columnIndex = cursor.getColumnIndex("timestamp");
        String timestampRow = cursor.getString(columnIndex);
        TextView timestamp = view.findViewById(R.id.timestamp);
        timestamp.setText(timestampRow);

        // retrieves favourites from database and shows it in main activity
        columnIndex = cursor.getColumnIndex("favourites");
        String favouritesRow = cursor.getString(columnIndex);
        ImageView starView = view.findViewById(R.id.favourites);
        switch (favouritesRow) {
            case "yes":
                starView.setVisibility(View.VISIBLE);
                break;
            case "no":
                starView.setVisibility(View.INVISIBLE);
                break;
        }

        // retrieves mood from database and shows it in main activity
        columnIndex = cursor.getColumnIndex("mood");
        String moodRow =  cursor.getString(columnIndex);
        ImageView imageView = view.findViewById(R.id.moodRow);
        if (moodRow != null) {
            switch (moodRow) {
                case "positive":
                    imageView.setImageResource(R.drawable.positive);
                    break;
                case "neutral":
                    imageView.setImageResource(R.drawable.neutral);
                    break;
                case "negative":
                    imageView.setImageResource(R.drawable.negative);
                    break;
                case "sad":
                    imageView.setImageResource(R.drawable.sad);
                    break;
                    }
            } else {
            imageView.setImageResource(R.drawable.negative);
        }
    }
}

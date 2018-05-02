package example.promo.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter{
    public EntryAdapter(Context context, Cursor cursor, int layout, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        int columnIndex;

        columnIndex = cursor.getColumnIndex("title");
        String titleRow = cursor.getString(columnIndex);
        TextView title = view.findViewById(R.id.titleRow);
        title.setText(titleRow);

        columnIndex = cursor.getColumnIndex("timestamp");
        String timestampRow = cursor.getString(columnIndex);
        TextView timestamp = view.findViewById(R.id.timestamp);
        timestamp.setText(timestampRow);

        //columnIndex = cursor.getColumnIndex("mood");
        ImageView imageView = view.findViewById(R.id.moodRow);
        imageView.setImageResource(R.drawable.negativemood);
    }
}

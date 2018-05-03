/****************************************************************************
 * MainActivity.java
 *
 * Author: Amber Brands
 * Date: 04-05-2018
 *
 * This program implements the Journal app for android phones.
 ***************************************************************************/

package example.promo.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // initialize properties...
    private static EntryDatabase db;
    private static EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // retrieves database
        db = EntryDatabase.getInstance(getApplicationContext());

        // stores all data in cursor
        Cursor cursor = db.selectAll();

        // connects listener to list view
        ListView listView = findViewById(R.id.listView);
        adapter = new EntryAdapter(this, cursor, R.layout.entry_row,1);
        listView.setAdapter(adapter);

        // create click and long click listener
        AdapterView.OnItemLongClickListener clickedOn = new ListItemClickListener();
        listView.setOnItemLongClickListener(clickedOn);

        AdapterView.OnItemClickListener clickedOnLong = new ListItemClickListener();
        listView.setOnItemClickListener(clickedOnLong);
    }

    // directs to input activity when floating button is clicked
    public void FloatingActionButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // retrieves cursor of clicked entry
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            // creates bundle
            Bundle bundle = new Bundle();

            // stores info of journal entry
            String[] titles = new String[]{"title", "timestamp", "content", "mood"};
            for (String title : titles) {
                int columnIndex = cursor.getColumnIndex(title);
                bundle.putString(title, cursor.getString(columnIndex));
            }

            // pass info journal entry to detail activity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            // get cursor on clicked item
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            // retrieves id at clicked location
            int columnIndex = cursor.getColumnIndex("_id");
            long ids = cursor.getInt(columnIndex);

            // deletes data of clicked location and updates view
            db.delete(ids);
            updateData();

            return true;
        }
    }

    // updates view
    private void updateData() {
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
    }
}

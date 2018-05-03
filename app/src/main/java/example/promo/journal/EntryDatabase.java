package example.promo.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {
    /** The following class controls SQL database and contains the following methods: onCreate,
     * onUpgrade, EntryDatabase, selectAll, insert and delete. */

    // initialize properties...
    private static String DB_NAME = "entries";
    private static int DB_VERSION = 1;
    private static EntryDatabase instance = null;

    // constructor
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // returns entry database if it exists
    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            return instance = new EntryDatabase(context, DB_NAME, null, DB_VERSION);
        }
    }

    // creates table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table entries ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT, timestamp TEXT);");
        String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        db.execSQL("INSERT INTO entries (title, content, mood, timestamp) VALUES ('Good day', 'University', 'positive', '" + timeStamp + "');");
    }

    // upgrades table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }

    // selects all data in table
    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM entries;", null);
    }

    // inserts row in table
    public void insert(JournalEntry entry) {

        // retrieves database
        SQLiteDatabase db = getWritableDatabase();

        // creates content values and stores data
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", entry.getTitle());
        contentValues.put("content", entry.getContent());
        contentValues.put("mood", entry.getDrawableMood());
        contentValues.put("timestamp", entry.getTimestamp());

        // inserts content values in table
        db.insert(DB_NAME, null, contentValues);
    }

    // deletes row in table
    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("entries", "_id=" + id, null);
    }
}

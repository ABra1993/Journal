package example.promo.journal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "entries";
    private static int DB_VERSION = 1;
    private static EntryDatabase instance;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table entries ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT, timestamp TEXT);");
        db.execSQL("INSERT INTO entries (title, content, mood) VALUES ('Good day', 'University', 'happy');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            return instance = new EntryDatabase(context, DB_NAME, null, DB_VERSION);
        }
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM entries;", null);

    }
}

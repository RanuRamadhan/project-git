package DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import models.Student;

public class DbHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbMovie";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STD = "Movie";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_GENRE = "nim";
    private static final String KEY_SINOPSIS = "sinopsis";
    private static final String KEY_REELEASEDATE = "releasedate";
    private static final String KEY_DIRECTOR = "director";
    private static final String KEY_RATING = "rating";
    private static final String KEY_TRAILER = "trailer";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_STD + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT, " + KEY_GENRE + " TEXT, " + KEY_SINOPSIS + "TEXT, " + KEY_REELEASEDATE + "TEXT " +
            KEY_DIRECTOR + "TEXT, " + KEY_RATING + "TEXT, " + KEY_SINOPSIS + "TEXT )";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STD + "'");
        onCreate(db);
    }

    public long addUserDetail(String name, String nim, String sinopsis) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GENRE, nim);
        values.put(KEY_SINOPSIS, sinopsis);
        long insert = db.insert(TABLE_STD, null, values);

        return insert;
    }

    @SuppressLint("Range")
    public ArrayList<Student> getAllUsers() {
        ArrayList<Student> userModelArrayList = new ArrayList<Student>();

        String selectQuery = "SELECT * FROM " + TABLE_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Student std = new Student();
                std.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                std.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                std.setGenre(c.getString(c.getColumnIndex(KEY_GENRE)));
                std.setGenre(c.getString(c.getColumnIndex(KEY_SINOPSIS)));
                // adding to Students list
                userModelArrayList.add(std);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STD, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public int updateUser(int id, String nim, String name, String sinopsis) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GENRE, nim);
        values.put(KEY_SINOPSIS, sinopsis);

        return db.update(TABLE_STD, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});

    }
}

package com.example.guessmovie.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;//movies hint added
    private static final String DATABASE_NAME = "movies.db";

    //Movies
    public static final String TABLE_MOVIE = "movies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "des";

    public static final String COLUMN_LevelSelected = "levelselected";

    public static final String COLUMN_HINT1 = "hint1";
    public static final String COLUMN_HINT2 = "hint2";
    public static final String COLUMN_HINT3 = "hint3";

    // Users
    public static final String TABLE_USERS = "usuarios";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "usuario";
    public static final String COLUMN_PASSWORD = "contrasena";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tabla de películas
        String createMoviesTableQuery = "CREATE TABLE " + TABLE_MOVIE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_HINT1 + " TEXT, " +
                COLUMN_HINT2 + " TEXT, " +
                COLUMN_HINT3 + " TEXT, " +
                COLUMN_LevelSelected + " INTEGER)";
        db.execSQL(createMoviesTableQuery);

        //tabla de usuarios
        String createUsersTableQuery = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUsersTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropMoviesTableQuery = "DROP TABLE IF EXISTS " + TABLE_MOVIE;
        db.execSQL(dropMoviesTableQuery);

        String dropUsersTableQuery = "DROP TABLE IF EXISTS " + TABLE_USERS;
        db.execSQL(dropUsersTableQuery);
        onCreate(db);
    }

    public long insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        return db.insert(TABLE_USERS, null, values);
    }
    public long insertMovie(String movie, String description, String hint1, String hint2, String hint3, int levelSelected) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_HINT1, hint1);
        values.put(COLUMN_HINT2, hint2);
        values.put(COLUMN_HINT3, hint3);
        values.put(COLUMN_LevelSelected, levelSelected); // Agregar el nuevo campo
        return db.insert(TABLE_MOVIE, null, values);
    }
    public ArrayList<String> getAllUsers() {
        ArrayList<String> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USERNAME + " FROM " + TABLE_USERS, null);

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                Log.d("DatabaseHelper", "Usuario encontrado: " + username);
                userList.add(username);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }
    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_USERNAME + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }

    public MovieData getMovieData(int levelSelected) {
        SQLiteDatabase db = this.getReadableDatabase();
        MovieData movieData = null;

        Cursor cursor = db.query(TABLE_MOVIE,
                new String[]{COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_HINT1, COLUMN_HINT2, COLUMN_HINT3, COLUMN_LevelSelected},
                COLUMN_LevelSelected + "=?",
                new String[]{String.valueOf(levelSelected)}, // Buscar por levelSelected
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            movieData = new MovieData(
                    cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_LevelSelected)), // Obtén el nivel seleccionado como entero
                    cursor.getString(cursor.getColumnIndex(COLUMN_HINT1)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_HINT2)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_HINT3))
            );
            cursor.close();
        }

        db.close();
        return movieData;
    }


}

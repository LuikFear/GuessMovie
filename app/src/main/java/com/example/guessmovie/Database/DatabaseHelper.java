package com.example.guessmovie.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movies.db";

    public static final String TABLE_MOVIE = "movies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "des";

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
        // Crear la tabla de películas
        String createMoviesTableQuery = "CREATE TABLE " + TABLE_MOVIE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(createMoviesTableQuery);

        // Crear la tabla de usuarios
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
    public long insertMovie(String movie, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie);
        values.put(COLUMN_DESCRIPTION, description);
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

    public Cursor getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MOVIE, null, null, null, null, null, null);
    }
}

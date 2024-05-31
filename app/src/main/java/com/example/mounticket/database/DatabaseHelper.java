package com.example.mounticket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mounticket.models.Mountain;
import com.example.mounticket.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mountains.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_MOUNTAINS = "mountains";
    private static final String COLUMN_MOUNTAIN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ALAMAT = "alamat";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_HARGA = "harga";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT" +
                    ")";

    private static final String TABLE_CREATE_MOUNTAINS =
            "CREATE TABLE " + TABLE_MOUNTAINS + " (" +
                    COLUMN_MOUNTAIN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_ALAMAT + " TEXT, " +
                    COLUMN_HEIGHT + " TEXT, " +
                    COLUMN_IMAGE + " TEXT, " +
                    COLUMN_HARGA + " TEXT" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_MOUNTAINS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOUNTAINS);
        onCreate(db);
    }

    // User table methods
    public long addUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        return db.insert(TABLE_USERS, null, values);
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        return cursorCount > 0;
    }

    // Mountain table methods
    public void insertMountain(Mountain mountain) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOUNTAIN_ID, mountain.getId());
        values.put(COLUMN_NAME, mountain.getName());
        values.put(COLUMN_ALAMAT, mountain.getAlamat());
        values.put(COLUMN_HEIGHT, mountain.getHeight());
        values.put(COLUMN_IMAGE, mountain.getImage());
        values.put(COLUMN_HARGA, mountain.getHarga());
        db.insert(TABLE_MOUNTAINS, null, values);
        db.close();
    }

    public List<Mountain> getAllMountains() {
        List<Mountain> mountainList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOUNTAINS, null);
        if (cursor.moveToFirst()) {
            do {
                Mountain mountain = new Mountain();
                mountain.setId(cursor.getInt(0));
                mountain.setName(cursor.getString(1));
                mountain.setAlamat(cursor.getString(2));
                mountain.setHeight(cursor.getString(3));
                mountain.setImage(cursor.getString(4));
                mountain.setHarga(cursor.getString(5));
                mountainList.add(mountain);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mountainList;
    }
}
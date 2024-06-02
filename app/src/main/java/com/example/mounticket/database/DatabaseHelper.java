package com.example.mounticket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mounticket.models.Mountain;
import com.example.mounticket.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mountains_users_transactions.db";
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

    private static final String TABLE_TRANSACTIONS = "transactions";
    private static final String COLUMN_TRANSACTION_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_AMOUNT = "amount";

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

    private static final String TABLE_CREATE_TRANSACTIONS =
            "CREATE TABLE " + TABLE_TRANSACTIONS + " (" +
                    COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_AMOUNT + " REAL" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_MOUNTAINS);
        db.execSQL(TABLE_CREATE_TRANSACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOUNTAINS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
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
    public long addMountain(Mountain mountain) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, mountain.getName());
        values.put(COLUMN_ALAMAT, mountain.getAlamat());
        values.put(COLUMN_HEIGHT, mountain.getHeight());
        values.put(COLUMN_IMAGE, mountain.getImage());
        values.put(COLUMN_HARGA, mountain.getHarga());
        return db.insert(TABLE_MOUNTAINS, null, values);
    }

    public List<Mountain> getAllMountains() {
        List<Mountain> mountainList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOUNTAINS, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COLUMN_MOUNTAIN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int alamatIndex = cursor.getColumnIndex(COLUMN_ALAMAT);
                int heightIndex = cursor.getColumnIndex(COLUMN_HEIGHT);
                int imageIndex = cursor.getColumnIndex(COLUMN_IMAGE);
                int hargaIndex = cursor.getColumnIndex(COLUMN_HARGA);

                // Pastikan indeks kolom valid
                if (idIndex >= 0 && nameIndex >= 0 && alamatIndex >= 0 && heightIndex >= 0 && imageIndex >= 0 && hargaIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String alamat = cursor.getString(alamatIndex);
                    String height = cursor.getString(heightIndex);
                    String image = cursor.getString(imageIndex);
                    String harga = cursor.getString(hargaIndex);

                    Mountain mountain = new Mountain(id, name, alamat, height, image, harga);
                    mountainList.add(mountain);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mountainList;
    }

    // Transaction table methods
    public void deleteAllTransactions() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSACTIONS, null, null);
        db.close();
    }

    public long insertTransaction(String description, double amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_AMOUNT, amount);
        return db.insert(TABLE_TRANSACTIONS, null, values);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TRANSACTIONS, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_ID));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT));
                Transaction transaction = new Transaction(id, description, amount);
                transactionsList.add(transaction);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return transactionsList;
    }
}
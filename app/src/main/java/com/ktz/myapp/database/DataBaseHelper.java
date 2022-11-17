package com.ktz.myapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_AGE = "age";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_PASS = "pass";

    public static final int ID = 1;
    public static final int NAME = 2;
    public static final int EMAIL = 3;
    public static final int PHONE = 4;
    public static final int AGE = 5;
    public static final int GENDER = 6;
    public static final int COUNTRY = 7;
    public static final int PASS = 8;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = " CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_PHONE + " TEXT, " +
                KEY_AGE + " TEXT, " +
                KEY_GENDER + " TEXT, " +
                KEY_COUNTRY + " TEXT, " +
                KEY_PASS + " TEXT);";
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String name, String email, String pass, String phone, String age, String gender, String country) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASS, pass);
        values.put(KEY_PHONE, phone);
        values.put(KEY_AGE, age);
        values.put(KEY_GENDER, gender);
        values.put(KEY_COUNTRY, country);
        return db.insert(TABLE_NAME, null, values);
    }

    public void updateUser(long l, String name, String email, String pass) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASS, pass);
        db.update(TABLE_NAME, values, KEY_ID + "=" + l, null);
    }

    public void deleteUser(long l) {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=" + l, null);
    }

    public int userCount() {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        int count = 0;
        cursor.moveToFirst();
        count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    public String getValue(int type, long l) {
        db = this.getReadableDatabase();
        String text = "";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        text = cursor.getString(type);
        cursor.close();
        return text;

    }

    public String getValue(long l, String type) {
        db = this.getReadableDatabase();
        String text = "";
        String[] columns = new String[]{KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_AGE, KEY_GENDER, KEY_COUNTRY, KEY_PASS};
        Cursor cursor = db.query(TABLE_NAME, columns, KEY_ID + "=" + l, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            switch (type) {
                case KEY_NAME: {
                    text = cursor.getString(1);
                    break;
                }
                case KEY_EMAIL: {
                    text = cursor.getString(2);
                    break;
                }
                case KEY_PASS: {
                    text = cursor.getString(7);
                    break;
                }
                case KEY_PHONE: {
                    text = cursor.getString(3);
                    break;
                }
                case KEY_AGE: {
                    text = cursor.getString(4);
                    break;
                }
                case KEY_GENDER: {
                    text = cursor.getString(5);
                    break;
                }
                case KEY_COUNTRY: {
                    text = cursor.getString(6);
                    break;
                }
            }
            cursor.close();
            return text;
        }
        return null;
    }
}

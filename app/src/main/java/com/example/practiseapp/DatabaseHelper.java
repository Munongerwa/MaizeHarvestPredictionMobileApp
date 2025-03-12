package com.example.practiseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "harvest.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "predictions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_AREA = "area";
    private static final String COLUMN_RAINFALL = "rainfall";
    private static final String COLUMN_FERTILIZER = "fertilizer";
    private static final String COLUMN_HARVEST = "harvest";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AREA + " REAL, " +
                COLUMN_RAINFALL + " REAL, " +
                COLUMN_FERTILIZER + " REAL, " +
                COLUMN_HARVEST + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPrediction(double area, double rainfall, double fertilizer, double harvest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AREA, area);
        values.put(COLUMN_RAINFALL, rainfall);
        values.put(COLUMN_FERTILIZER, fertilizer);
        values.put(COLUMN_HARVEST, harvest);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<String> getAllPredictions() {
        List<String> predictions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String prediction = "Area: " + cursor.getDouble(1) +
                        ", Rainfall: " + cursor.getDouble(2) +
                        ", Fertilizer: " + cursor.getDouble(3) +
                        ", Harvest: " + cursor.getDouble(4);
                predictions.add(prediction);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return predictions;
    }
}

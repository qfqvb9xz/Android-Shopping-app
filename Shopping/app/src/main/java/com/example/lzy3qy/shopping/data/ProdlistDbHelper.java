package com.example.lzy3qy.shopping.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lzy3qy.shopping.data.ProductListContract.*;

/**
 * Created by LZY3QY on 3/17/17.
 */

public class ProdlistDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "waitlist.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public ProdlistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold waitlist data
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + ProdlistEntry.TABLE_NAME + " (" +
                ProdlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProdlistEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                ProdlistEntry.COLUMN_PRODUCT_NUMBER + " INTEGER NOT NULL, " +
                ProdlistEntry.COLUMN_URL + " STRING NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProdlistEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
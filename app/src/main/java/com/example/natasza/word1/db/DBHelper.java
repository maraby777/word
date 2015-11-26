package com.example.natasza.word1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Natasza on 2015-10-25.
 */
public class DBHelper extends SQLiteOpenHelper {

    //name DB
    public static final String DATABASE_NAME = "word.db";
    public static final int DATABASE_VERSION = 1;



    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //jesli DB nie istnieje, to bedzie utworzona
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exist " + Contract.Word.TABLE_NAME
                + "(" +
                Contract.Word._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Word.ID_WORDS + " INTEGER, " +
                Contract.Word.WORD + " TEXT, " +
                Contract.Word.TRANSLATION + " TEXT, " +
                Contract.Word.ID_KATEGORY + " INTEREG NOT NULL, " +
                Contract.Word.ID_STATUS + " INTEGER "
                + ");"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXIST "+ Contract.Category.TABLE_NAME
                + "(" +
                Contract.Category._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Category.ID_CATEGORY + " INTEGER, " +
                Contract.Category.CATEGORY + " TEXT "
                + ");"
        );

        db.execSQL(
                "CREATE  TABLE IF NOT EXIST "+ Contract.Status.TABLE_NAME
                + "(        " +
                Contract.Status._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,         " +
                Contract.Status.ID_STATUS + " INTEGER,         " +
                Contract.Status.STATUS + " TEXT "
                + ");"
                );
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IR EXIST " + Contract.Word.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IR EXIST " + Contract.Status.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IR EXIST " + Contract.Category.TABLE_NAME + ";");

    }
}

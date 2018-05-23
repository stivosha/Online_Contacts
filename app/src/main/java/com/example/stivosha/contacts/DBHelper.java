package com.example.stivosha.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stivosha on 17.05.2018.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDB";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_NUMBER = "number";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_CONTACTS+"("+KEY_ID+
        " integer primary key,"+KEY_NAME+" text,"+KEY_LAST_NAME+" text,"+
        KEY_NUMBER+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }
}

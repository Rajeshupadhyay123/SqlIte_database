package com.example.exeprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyOwnDatabase extends SQLiteOpenHelper {

    public MyOwnDatabase(Context ct)
    {
        super(ct,Contract.DB_NAME,null,Contract.DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Contract.DB_TABLE+" (_id integer primary key autoincrement,emp_email text,emp_name text,emp_profile text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+Contract.DB_TABLE);
    }
}

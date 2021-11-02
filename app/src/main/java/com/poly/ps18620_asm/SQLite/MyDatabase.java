package com.poly.ps18620_asm.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(@Nullable Context context) {
        super(context, "quanlysinhvien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        create table lophoc
        (
            _id integer primary key autoincrement,
            tenlop text
        )

        create table sinhvien
        (
            _id integer primary key autoincrement,
            tensinhvien text,
            id_lop integer
        )
         */

        String sql = "create table lophoc" +
                "(" +
                "_id integer primary key autoincrement," +
                "tenlop text" +
                ")";

        String sql2 = "create table sinhvien " +
                "( " +
                "_id integer primary key autoincrement," +
                "tensinhvien text," +
                "id_lop integer" +
                ")";

        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists lophoc");
        sqLiteDatabase.execSQL("drop table if exists sinhvien");
        onCreate(sqLiteDatabase);
    }
}

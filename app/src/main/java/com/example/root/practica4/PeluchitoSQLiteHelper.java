package com.example.root.practica4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PeluchitoSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate="CREATE TABLE peluchitos (" +
            "id     INTEGER PRIMARY KEY AUTOINCREMENT, " +//0
            "nombre TEXT, " +                             //1
            "precio TEXT, " +                           //2
            "cantidad TEXT)";


    public PeluchitoSQLiteHelper(Context context,
                                 String name,
                                 SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS peluchitos");
        db.execSQL(sqlCreate);

    }
}
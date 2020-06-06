package com.example.calculateurimc.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // Propriétés
    public static final String DATABASE_NAME = "dbIMC.sqlite";
    public static final String TABLE_NAME = "user";
    public static final String COL_1 = "id";
    public static final String COL_2 = "nom";
    public static final String COL_3 = "prenom";
    public static final String COL_4 = "IMC";

    /**
     * Constructeur
     * @param context
     */
    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creation = "CREATE TABLE " + TABLE_NAME + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom TEXT NOT NULL,"
                + "prenom TEXT NOT NULL,"
                + "IMC NUMERIC NOT NULL)";

        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nom, String prenom, Float imc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nom);
        contentValues.put(COL_3, prenom);
        contentValues.put(COL_4, imc);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllDatas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;
    }
}

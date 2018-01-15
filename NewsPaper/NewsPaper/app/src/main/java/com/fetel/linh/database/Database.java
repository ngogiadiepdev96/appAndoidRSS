package com.fetel.linh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fetel.linh.model.Account;

import java.util.ArrayList;
import java.util.Vector;



public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "abc.db";
    public static final int DB_VERSION = 1;
    public static final String ACCOUNT_TABLE = "Account";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE " +ACCOUNT_TABLE+ "("
            +COLUMN_USERNAME+"  TEXT PRIMARY KEY, "
            +COLUMN_PASSWORD+ " TEXT"+")";
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNT);// mo ket noi den DB

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXITS " + ACCOUNT_TABLE);
        db.execSQL(ACCOUNT_TABLE);
        onCreate(db);
    }

    public void addAccount(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        db.insert(ACCOUNT_TABLE, null, values);
        db.close();
    }


    public boolean Check(String username, String password) {
        String sql = "SELECT * FROM " + ACCOUNT_TABLE + " WHERE " +
                COLUMN_USERNAME + " = " + "'"+username+"'" + " AND "+ COLUMN_PASSWORD + " = " + "'"+password+"'";
        SQLiteDatabase   db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while(c.getCount()>0){
            return true;
        }
        return false;
    }
    public boolean CheckUsername(String username){
        String sql = "SELECT * FROM " + ACCOUNT_TABLE + " WHERE " +
                COLUMN_USERNAME + " = " + "'"+username+"'" ;
        SQLiteDatabase   db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while(c.getCount()>0){
            return true;
        }
        return false;

    }
}

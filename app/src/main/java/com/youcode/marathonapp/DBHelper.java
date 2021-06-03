package com.youcode.marathonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 2);
        SQLiteDatabase DB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id INTEGER primary key AUTOINCREMENT,name TEXT , phone TEXT, birthday TEXT, email TEXT , gender TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
        onCreate(DB);
    }

    public boolean insertUserData(String name, String phone, String birthday, String email, String gender){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("birthday", birthday);
        contentValues.put("email", email);
        contentValues.put("gender", gender);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean updateUserData(String name, String phone, String birthday, String email, String gender){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone);
        contentValues.put("birthday", birthday);
        contentValues.put("name", name);
        contentValues.put("gender", gender);

        Cursor cursor = DB.rawQuery("select * from Userdetails where email=?", new String[]{email});
        if(cursor.getCount()>0) {
            long result = DB.update("Userdetails", contentValues, "email=?", new String[]{email});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public boolean deleteUserData(String email){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from Userdetails where email=?", new String[]{email});
        if(cursor.getCount()>0) {
            long result = DB.delete("Userdetails","email=?", new String[]{email});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }


}

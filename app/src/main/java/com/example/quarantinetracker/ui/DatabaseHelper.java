package com.example.quarantinetracker.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Raport.db";
    public static final String MAIN_TABLE_NAME = "Raport_table";
    public static final String MAIN_COL_1 = "ID";
    public static final String MAIN_COL_2 = "Year";
    public static final String MAIN_COL_3 = "Month";
    public static final String MAIN_COL_4 = "Day";
    public static final String MAIN_COL_5 = "Hour";
    public static final String MAIN_COL_6 = "Minute";
    public static final String MAIN_COL_7 = "Activity_ID";
    public static final String MAIN_COL_8 = "Location_ID";
    public static final String MAIN_COL_9 = "Assessment";
    public static final String MAIN_COL_10 = "People";
    public static final String MAIN_COL_11 = "Misc";


    public static final String PEOPLE_TABLE_NAME = "People_table";
    public static final String PEOPLE_COL_1 = "ID";
    public static final String PEOPLE_COL_2 = "Name";

    public static final String TITLE_TABLE_NAME = "Title_table";
    public static final String TITLE_COL_1 = "ID";
    public static final String TITLE_COL_2 = "Title";
    public static final String TITLE_COL_3 = "Times";
    public static final String TITLE_COL_4 = "AssessmentSum";

    public static final String LOCATION_TABLE_NAME = "Location_table";
    public static final String LOCATION_COL_1 = "ID";
    public static final String LOCATION_COL_2 = "Location";
    public static final String LOCATION_COL_3 = "Times";
    public static final String LOCATION_COL_4 = "AssessmentSum";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MAIN_TABLE_NAME + " (" + MAIN_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MAIN_COL_2 + " INTEGER, "+ MAIN_COL_3 + " INTEGER,"+ MAIN_COL_4 +" INTEGER,"+ MAIN_COL_5 +" INTEGER,"+ MAIN_COL_6 +" INTEGER,"+ MAIN_COL_7 +" INTEGER,"+ MAIN_COL_8 +" INTEGER,"+ MAIN_COL_9 +" TEXT,"+ MAIN_COL_10 +" INTEGER,"+ MAIN_COL_11 +" TEXT)");
        db.execSQL("create table " + PEOPLE_TABLE_NAME + " (" + PEOPLE_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +PEOPLE_COL_2 + " TEXT)");
        db.execSQL("create table " + TITLE_TABLE_NAME + " (" + TITLE_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE_COL_2 + " TEXT, " + TITLE_COL_3 + " INTEGER," + TITLE_COL_4 + " INTEGER)");
        db.execSQL("create table " + LOCATION_TABLE_NAME + " (" + LOCATION_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LOCATION_COL_2 + " TEXT, " + LOCATION_COL_3 + " INTEGER," + LOCATION_COL_4 + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ MAIN_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertReport(int year, int month, int day, int hour, int minute, String activity, String location, String assessment, String people, String misc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MAIN_COL_2, year);
        contentValues.put(MAIN_COL_3, month);
        contentValues.put(MAIN_COL_4, day);
        contentValues.put(MAIN_COL_5, hour);
        contentValues.put(MAIN_COL_6, minute);
        contentValues.put(MAIN_COL_7, activity);
        contentValues.put(MAIN_COL_8, location);
        contentValues.put(MAIN_COL_9, assessment);
        contentValues.put(MAIN_COL_10, people);
        contentValues.put(MAIN_COL_11, misc);
        long result = db.insert(MAIN_TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public boolean insertPerson(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MAIN_COL_2, name);
        long result = db.insert(PEOPLE_TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }





    public Cursor getRaportData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ MAIN_TABLE_NAME,null);
        return res;
    }

    public Cursor getLocationData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ LOCATION_TABLE_NAME,null);
        return res;
    }

    public Cursor getTitleData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TITLE_TABLE_NAME,null);
        return res;
    }

    public Cursor getPeopleData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ PEOPLE_TABLE_NAME,null);
        return res;
    }

}

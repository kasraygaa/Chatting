package com.example.android.recycle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by USER on 03/11/2017.
 */

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "Database";
    private static final String TABLE_NAME = "datakas";
    private static final String COL1 = "ID";
    private static final String COL2 = "Name";
    private static final String COL3 = "Message";

    public Database(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 +" TEXT," + COL3 + " TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sq = "DROP IF TABLE EXIST";
        sqLiteDatabase.execSQL(sq + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean addData(String user, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, user);
        contentValues.put(COL3, message);
        Log.d(TAG, "addData: Adding " + user + " and " + message + " to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = sqLiteDatabase.rawQuery(query, null);
        return data;
    }
}

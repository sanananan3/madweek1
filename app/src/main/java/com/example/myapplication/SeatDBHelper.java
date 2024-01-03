package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;


public class SeatDBHelper extends SQLiteOpenHelper

{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SeatCount.db";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + SeatTable.TABLE_NAME + " (" + SeatTable.COLUMN_ID + " INTEGER PRIMARY KEY, "
    + SeatTable.COLUMN_TIME_ID +" INTEGER," + SeatTable.COLUMN_SEAT_ID + " INTEGER)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + SeatTable.TABLE_NAME;
    public SeatDBHelper (Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate (SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        insertInitialData(db);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_TABLE);

        onCreate(db);
    }


    private void insertInitialData(SQLiteDatabase db){

        int timeId, seatId;


        for (timeId = 1;timeId <= 83; timeId++){

            if(timeId % 11 == 0 ) {

                for(seatId=1; seatId <= 11 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);

                }
            }

            else if(timeId % 11 == 1 ) {

                for(seatId=1; seatId <= 12 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);

                }
            }

            else if(timeId % 11 == 2 ) {

                for(seatId=1; seatId <= 9 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);

                }

            }

            else if(timeId % 11 == 3 ) {

                for(seatId=1; seatId <= 4 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);

                }

            }

            else if(timeId % 11 == 4 ) {

                for(seatId=1; seatId <= 21 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);
                }
            }
            else if(timeId % 11 == 5 ) {

                for(seatId=1; seatId <= 6 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);
                }
            }
            else if(timeId % 11 == 6 ) {

                for(seatId=1; seatId <= 1 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);
                }
            }
            else if(timeId % 11 == 7 ) {

                for(seatId=1; seatId <= 17 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);
                }
            }
            else if(timeId % 11 == 8 ) {

                for(seatId=1; seatId <= 10 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);
                }
            }
            else if(timeId % 11 == 9 ) {

                for(seatId=1; seatId <= 2; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);
                }
            }
            else if(timeId % 11 == 10 ) {

                for(seatId=1; seatId <= 3 ; seatId++) {

                    ContentValues values  = new ContentValues();
                    values.put(SeatTable.COLUMN_TIME_ID, timeId);
                    values.put(SeatTable.COLUMN_SEAT_ID, seatId);
                    db.insert(SeatTable.TABLE_NAME, null, values);
                }
            }
        }



    }

    public int getSeatCountForTimeId(int timeId){

        SQLiteDatabase db= this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM "+SeatTable.TABLE_NAME + " WHERE "+ SeatTable.COLUMN_TIME_ID+
                " =?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(timeId)});
        int count = 0 ;
        if(cursor!=null){

            if(cursor.moveToNext()){
                count = cursor.getInt(0);

            }
            cursor.close();
        }


        db.close();
        return count;
    }

}

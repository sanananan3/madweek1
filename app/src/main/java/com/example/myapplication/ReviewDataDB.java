package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ReviewDataDB extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "reviewdata.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "review_info";
    public static final String COLUMN_MOVIE = "movie";
    public static final String COLUMN_REVIEW = "review";

    //DB생성
    public ReviewDataDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    //테이블생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_MOVIE + " TEXT, " +
                COLUMN_REVIEW + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //데이터 가져오기
    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //데이터 등록하기
    public void addReviewData(String movie, String review){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MOVIE, movie);
        cv.put(COLUMN_REVIEW, review);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //데이터 초기화하기
    public void initReviewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }
}

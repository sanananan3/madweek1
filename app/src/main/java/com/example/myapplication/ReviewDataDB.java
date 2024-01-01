package com.example.myapplication;

import static java.sql.DriverManager.println;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ReviewDataDB extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "reviewdata.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "review_info";
    public static final String COLUMN_MOVIE = "movie";
    public static final String COLUMN_REVIEW = "review";
    public static final String COLUMN_SCORE = "score";

    //DB 생성
    public ReviewDataDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    //TABLE 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_MOVIE + " TEXT," +
                COLUMN_REVIEW + " TEXT," +
                COLUMN_SCORE + " REAL);";
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

    //데이터 등록
    public void addReviewData(String movie, String review, float score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ReviewDataDB.COLUMN_MOVIE, movie);
        cv.put(ReviewDataDB.COLUMN_REVIEW, review);
        cv.put(ReviewDataDB.COLUMN_SCORE, score);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Log.e("ReviewDataDB", "Insert failed:");
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else if (result != -1){
            Toast.makeText(context, "Succeed", Toast.LENGTH_SHORT).show();
        }


    }
}

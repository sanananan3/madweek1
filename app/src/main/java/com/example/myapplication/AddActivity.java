package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    TextView btn_alert;
    TextView tv_movie;
    EditText addReviewEdit;
    float movie_score= 0.0F;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn_alert = findViewById(R.id.tv_movie);
        tv_movie = findViewById(R.id.tv_movie);
        addReviewEdit = findViewById(R.id.add_review_edit);
        String[] items = getResources().getStringArray(R.array.movies);
        ArrayList<String> selectItem = new ArrayList<>();

        selectItem.add(items[0]);
        btn_alert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddActivity.this)
                        .setTitle("리뷰를 작성할 영화를 선택하세요.")
                        .setSingleChoiceItems(R.array.movies, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectItem.clear();
                                selectItem.add(items[which]);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_movie.setText(selectItem.get(0));
                            }
                        })
                        .setNegativeButton("닫기", null)
                        .show();
            }
        });

        RatingBar ratingBar = findViewById(R.id.RatingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                movie_score = rating;
            }
        });

        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String movie = selectItem.get(0);
                String review = addReviewEdit.getText().toString();
                ReviewDataDB reviewDataDB = new ReviewDataDB(AddActivity.this);
                reviewDataDB.addReviewData(movie, review, movie_score);
                finish();
            }
        });


    }
}
package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Fragment3 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ImageButton selectButton;
    ImageButton insertButton;

    View view;
    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance() {
        Fragment3 fragment3 = new Fragment3();
        return fragment3;
    }

    ReviewDataDB db;
    ArrayList<ReviewData> reviewDataArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ReviewDataAdapter adapter;
    EditText addReview;
    EditText addMovie;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_3, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        selectButton = (android.widget.ImageButton) view.findViewById(R.id.select_movie_button);
        insertButton = (android.widget.ImageButton) view.findViewById(R.id.edit_button);

        addReview = view.findViewById(R.id.review_id);
        addMovie = view.findViewById(R.id.movie_id);

        recyclerView = view.findViewById(R.id.review_recycler);
        adapter = new ReviewDataAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db = new ReviewDataDB(getContext());
        storeDataInArray();

        selectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ReviewDataDB reviewDataDB = new ReviewDataDB(getContext());
                reviewDataDB.initReviewData();
            }
        });


        insertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    String review = addReview.getText().toString();
                    String movie = addMovie.getText().toString();

                    ReviewDataDB reviewDataDB = new ReviewDataDB(getContext());

                    reviewDataDB.addReviewData(movie, review);
                }
        });

        return view;
    }

    void storeDataInArray(){
        Cursor cursor = db.readAllData();

        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                ReviewData reviewData = new ReviewData(cursor.getString(0),
                        cursor.getString(1));
                reviewDataArrayList.add(reviewData);
                adapter.addItem(reviewData);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onRefresh() {
        updateLayoutView();
        swipeRefreshLayout.setRefreshing(false);
    }

    public void updateLayoutView(){
        storeDataInArray();
    }
}
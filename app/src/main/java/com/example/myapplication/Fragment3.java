package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    ImageButton selectButton;
    ImageButton insertButton;
    EditText reviewEdit;
    ArrayList<Review> reviewArrayList;
    ReviewAdapter reviewAdapter;
    String[] movie_list = {"a", "b", "c"};
    String selected_movie="";
    ReviewAdapter ra_selected_movie;

    View view;
    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance() {
        Fragment3 fragment3 = new Fragment3();
        return fragment3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_3, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.review_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));

        reviewArrayList = new ArrayList<>();
        selected_movie = new String();
        reviewAdapter = new ReviewAdapter(reviewArrayList, selected_movie);
        recyclerView.setAdapter(reviewAdapter);

        selectButton = (android.widget.ImageButton) view.findViewById(R.id.select_movie_button);
        insertButton = (android.widget.ImageButton) view.findViewById(R.id.edit_button);
        reviewEdit = (EditText) view.findViewById(R.id.review_id);

        selectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setTitle("선택").setSingleChoiceItems(movie_list,
                -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected_movie=movie_list[which];
                    }
                }).setNegativeButton("닫기", null).setPositiveButton("선택", null).show();
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Review newReview = new Review(reviewEdit.getText().toString());
                    reviewArrayList.add(newReview);
                    reviewAdapter.notifyDataSetChanged();
                    reviewEdit.setText(null);
                }
        });

        return view;
    }
}
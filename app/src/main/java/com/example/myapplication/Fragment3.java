package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment3 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

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
    TextView noDataText;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_3, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        noDataText = view.findViewById(R.id.noData_text);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new ReviewDataAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(0, 0));

        db = new ReviewDataDB(getContext());
        storeDataInArray();

        FloatingActionButton addBtn = view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void storeDataInArray(){
        Cursor cursor = db.readAllData();

        if(cursor.getCount() == 0){
            noDataText.setVisibility(noDataText.VISIBLE);
        }else{
            noDataText.setVisibility(noDataText.GONE);
            while (cursor.moveToNext()){
                ReviewData reviewData = new ReviewData(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getFloat(2));
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

    public void updateLayoutView() {
        adapter.removeItem();
        storeDataInArray();
    }
}
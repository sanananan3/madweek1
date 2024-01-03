package com.example.myapplication;


import androidx.fragment.app.DialogFragment;


import androidx.fragment.app.DialogFragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;


import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


import android.view.LayoutInflater;
import android.view.View;

import androidx.cardview.widget.CardView;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConfirmDialogFragment extends DialogFragment {

    private String selectedMovName;
    private String movieDetails;

    public ConfirmDialogFragment(String selectedMovName, String movieDetails) {
        this.selectedMovName = selectedMovName;
        this.movieDetails = movieDetails;
    }

    public ConfirmDialogFragment() {
        // Required empty public constructor
    }

    public static ConfirmDialogFragment newInstance(String selectedMovName, String movieDetails) {
        return new ConfirmDialogFragment(selectedMovName, movieDetails);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm, container, false);

        // Find and initialize your TextViews
        TextView confirmNameTextView = view.findViewById(R.id.confirm_name);
        TextView confirmMovieTextView = view.findViewById(R.id.confirm_movie);

        // Set the text with the values received from newInstance
        confirmNameTextView.setText(selectedMovName);
        confirmMovieTextView.setText(movieDetails);

        return view;
    }
}




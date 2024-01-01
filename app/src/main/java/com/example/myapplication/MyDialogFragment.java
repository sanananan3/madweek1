package com.example.myapplication;

import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;

import android.content.Context;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import androidx.cardview.widget.CardView;

import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;




public class MyDialogFragment extends DialogFragment {

    private LinearLayout imageContainer;
    private LinearLayout imageContainer2;

    public MyDialogFragment(){


    }

    public static MyDialogFragment newInstance(){

        MyDialogFragment myDialogFragment = new MyDialogFragment();
        return myDialogFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_frag1, container, false);

        // Handle the UI components and functionality of dialog_frag1.xml here
        ImageButton closeButton = view.findViewById(R.id.btn_close);


        closeButton.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){

                dismiss();
            }
        });


        imageContainer = view.findViewById(R.id.imageContainer);
        addImagesToContainer();
        imageContainer2 = view.findViewById(R.id.imageContainer2);
        addImagesToContainer2();


        return view;
    }

    private void addImagesToContainer() {
        int imageWidth = 130;
        int imageHeight = 130;
        int margin = 1; // Set the margin in dp

        for (int i = 1; i <= 10; i++) {
            ImageView imageView = new ImageView(requireContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    dpToPx(imageWidth),
                    dpToPx(imageHeight)
            );

            imageView.setLayoutParams(layoutParams);

            imageView.setImageResource(getResources().getIdentifier("img" + i, "drawable", requireContext().getPackageName()));

            imageContainer.addView(imageView);
        }
    }

    private void addImagesToContainer2(){
        int imageWidth = 130;
        int imageHeight = 130;
        int margin = 1;


        for(int i=11;i<=20;i++){

            ImageView imageView2 = new ImageView(requireContext());
            imageView2.setLayoutParams(new LinearLayout.LayoutParams(
                    dpToPx(imageWidth),
                    dpToPx(imageHeight)
            ));

            imageView2.setImageResource(getResources().getIdentifier("img" +i,"drawable", requireContext().getPackageName()));
            imageView2.setPadding(0,0,0,0);

            imageContainer2.addView(imageView2);
        }
    }

    private int dpToPx(int dp){

        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp*density);
    }




}


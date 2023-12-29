package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {

    View view;
    public Fragment1() {
        // Required empty public constructor
    }

    public static Fragment1 newInstance() {
        Fragment1 fragment1 = new Fragment1();
        return fragment1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }
}
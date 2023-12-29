package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    TabPagerAdapter adapter;

    String[] str = new String[] {"tab1", "tab2", "tab3"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml 연결
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        //adapter 연결
        adapter = new TabPagerAdapter(this);
        viewPager.setAdapter(adapter);
        //TabLayout, ViewPager 연결
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy(){

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(str[position]);
                tab.setCustomView(textView);
            }
        }).attach();
    }
}
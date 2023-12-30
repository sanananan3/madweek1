package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    TabPagerAdapter adapter;

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
                switch(position){
                    case 0:
                        tab.setIcon(R.drawable.icon1);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.icon2);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.icon3);
                        break;
                }
            }
        }).attach();
    }
}
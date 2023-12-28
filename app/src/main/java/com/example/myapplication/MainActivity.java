package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    TabLayout tabs;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    int position;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("탭1");
        tabs = findViewById((R.id.tabs));
        tabs.addTab(tabs.newTab().setText("탭1"));
        tabs.addTab(tabs.newTab().setText("탭2"));
        tabs.addTab(tabs.newTab().setText("탭3"));

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragment1).commit();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            public void onTabReselected(TabLayout.Tab tab){}
            public void onTabUnselected(TabLayout.Tab tab){}
            public void onTabSelected(TabLayout.Tab tab){

                position = tab.getPosition(); // 탭 위치

                Fragment selected = fragment1;

                if(position ==0){

                    selected = fragment1;
                    getSupportActionBar().setTitle("탭1");

                }

                else if (position==1){

                    selected = fragment2;
                    getSupportActionBar().setTitle("탭2");
                }

                else if (position==2){

                    selected = fragment3;
                    getSupportActionBar().setTitle("탭3");
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.containers,selected).commit();
            }


        });
    }
}
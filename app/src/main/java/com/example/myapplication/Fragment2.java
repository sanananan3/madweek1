package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;

public class Fragment2 extends Fragment {

    View view;
    GridView gridView;
    GridView gridView2;
    String[] title = new String[]{"The Dark Knight", "IRONMAN3", "DUNE", "Us and Them", "The Unjust",
            "Demon Slayer", "Noryang", "Weathering with You", "Oppenheimer", "X-man",
            "The New World", "The Outlaws", "Harry Potter", "12.12: The Day", "Elemental",
            "Suzume", "Smugglers", "The First Slam Dunk", "Gaurdians of the Galaxy Vol.3", "Concrete Utopia"
    };
    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance() {
        Fragment2 fragment2 = new Fragment2();
        return fragment2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_2, container, false);

        gridView = (GridView)view.findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter1(getActivity()));
        gridView2 = (GridView)view.findViewById(R.id.gridView2);
        gridView2.setAdapter(new GridAdapter2(getActivity()));
        return view;
    }

    public class GridAdapter1 extends BaseAdapter {
        Context context;

        public GridAdapter1(Context c) {
            context = c;
        }

        Integer[] imgID1 = {
                R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
                R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10
        };

        @Override
        public int getCount() {
            return imgID1.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(500, 800));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(2, 0, 0, 0);
            imageView.setImageResource(imgID1[i]);

            final int pos = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    View dialogView = View.inflate(context, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                    ImageView poster = dialogView.findViewById(R.id.poster);
                    poster.setImageResource(imgID1[pos]);
                    dlg.setTitle(title[pos]);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });

            return imageView;
        }
    }
    public class GridAdapter2 extends BaseAdapter {

        Context context;
        public GridAdapter2(Context c) {
            context = c;
        }

        Integer[] imgID2 = {
                R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img15,
                R.drawable.img16, R.drawable.img17, R.drawable.img18, R.drawable.img19, R.drawable.img20
        };

        @Override
        public int getCount() {
            return imgID2.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(500, 800));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(0, 0, 2, 0);
            imageView.setImageResource(imgID2[i]);

            final int pos = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    View dialogView = View.inflate(context, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                    ImageView poster = dialogView.findViewById(R.id.poster);
                    poster.setImageResource(imgID2[pos]);
                    dlg.setTitle(title[pos+10]);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });

            return imageView;
        }
    }

}
package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import androidx.cardview.widget.CardView;
import android.widget.LinearLayout;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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

public class Fragment1 extends Fragment {

    private SearchView searchView;
    RecyclerView rv_recycle;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter_recycle;
    DataDTO dto;
    ArrayList<DataDTO> data = new ArrayList<>();

    int currentPosition = 0;
    Button rv_button;

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

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        rv_recycle = view.findViewById(R.id.rv_recycle);
        parser();
        layoutManager = new LinearLayoutManager(getContext());
        rv_recycle.setLayoutManager(layoutManager);
        adapter_recycle = new RecyclerAdapter(getContext(), data,getChildFragmentManager());
        rv_recycle.setAdapter(adapter_recycle);

        searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            public boolean onQueryTextSubmit(String query){

                ArrayList<Integer> matchingPositions = new ArrayList<>();



               for (int i=0;i<data.size();i++){

                   DataDTO item = data.get(i);

                   if(item.getName().toLowerCase().contains(query.toLowerCase())||
                           item.getMbti().toLowerCase().contains(query.toLowerCase())||
                           item.getBirth().toLowerCase().contains(query.toLowerCase()) ||
                           item.getCall().toLowerCase().contains(query.toLowerCase()))
                   {
                       matchingPositions.add(i);
                   }
               }
               if(!matchingPositions.isEmpty()){
                   if(currentPosition >= matchingPositions.size()){
                       currentPosition = 0;
                   }

                   rv_recycle.scrollToPosition(matchingPositions.get(currentPosition));
                   currentPosition++;
               }
                return true;
                }
                public boolean onQueryTextChange(String newText){

                return true;

            }

        });


        return view;

    }



    private void parser(){

        InputStream inputStream = getResources().openRawResource(R.raw.product);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;


        try{

            while ((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line);
            }

            Log.v("TAG", "StringBuffer : " + stringBuffer.toString());

            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            JSONArray jsonArray = new JSONArray(jsonObject.getString("2분반"));

            for (int i=0; i<jsonArray.length() ; i++){


                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                String name = jsonObject1.getString("name");
                String mbti = jsonObject1.getString("mbti");
                String birth = jsonObject1.getString("birth");
                String call = jsonObject1.getString("call");
                int imageResource = getResources().getIdentifier(jsonObject1.getString("image"),"drawable",getContext().getPackageName());

                dto = new DataDTO(name,mbti, birth, call,imageResource);
                data.add(dto);
            }
        } catch (Exception e){

            e.printStackTrace();
        } finally {
            try{

                if (inputStream != null ) inputStream.close();
                if(inputStreamReader != null ) inputStreamReader.close();
                if(bufferedReader != null) bufferedReader.close();

            }
            catch (Exception e){

                e.printStackTrace();
            }


        }



    }




}
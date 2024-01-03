package com.example.myapplication;

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

// replace with the actual package and class name



public class MyDialogFragment extends DialogFragment {
    String movie_name;

    private LinearLayout imageContainer;
    private LinearLayout imageContainer2;
    private Map<Integer, Integer> seatCountMap = new HashMap<>();

    private int selectedTimeCardId = -1;

    private View fragmentView;
    private ScrollView scrollView;
    private FloatingActionButton btnScrollToTop;
    private int selectedCardId = -1;
    private static final String SELECTED_CARD_KEY = "selected_card";

    private String selectedMovName;
    Fragment1 fragment1;

    String movieDetails;






    public MyDialogFragment(){

    }

    public static MyDialogFragment newInstance(String selectedMovName){

        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.selectedMovName = selectedMovName;
        return myDialogFragment;


    }

    public void onStart(){

        super.onStart();

        if(getDialog()!=null){

            int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
            int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
            getDialog().getWindow().setLayout(width, height);
        }
    }


    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_CARD_KEY, selectedCardId);
    }

    public void onViewStateRestored( Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            selectedCardId = savedInstanceState.getInt(SELECTED_CARD_KEY, -1);
            updateCardViewColors();
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_frag1, container, false);

        // Handle the UI components and functionality of dialog_frag1.xml here
        ImageButton closeButton = view.findViewById(R.id.btn_close);
        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        scrollView = view.findViewById(R.id.scrollView);

        fragmentView = view;
        fragment1 = (Fragment1) requireActivity().getSupportFragmentManager().findFragmentByTag("fragment1");


        closeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                dismiss();
            }
        });

        for (int i = 1; i <= 83; i++) {
            int cardId = getResources().getIdentifier("timecard" + i, "id", requireContext().getPackageName());
            CardView timeCard = view.findViewById(cardId);

            int finalI = i;
            timeCard.setOnClickListener(v -> {

                // Store the selected time card ID
                selectedTimeCardId = finalI;

                // Optionally, provide visual feedback that the time card is selected
                timeCard.setSelected(true);

                selectedCardId = cardId;
                updateCardViewColors();

                if (cardId == selectedCardId) {
                    timeCard.setSelected(true);
                    updateCardViewColors();
                }

            });
        }


        btnConfirm.setOnClickListener(v -> {

            movieDetails = getMovieDetailsForTimeCardId(selectedTimeCardId);


            // Handle the logic to delete the selected time card from the database
            deleteSelectedTimeCardFromDatabase();

            fragment1 = new Fragment1();

            // fragment1이 null이거나 추가된 상태인 경우에 업데이트 수행
            if (fragment1 != null ) {
                Log.d("선택한 애 이름", "하하하꺼~~ 선택한 애 이름" + selectedMovName + "선택한 영화 이름" + movieDetails);
                fragment1.updateContent(selectedMovName, movieDetails);
            } else {
                Log.e("MyDialogFragment", "Fragment1 is null or not added to the activity 실화냐 또? ");
            }
            // Update the seat count after deletion
            loadDataFromDatabase();



            // Dismiss the dialog after confirming the reservation
            dismiss();

            // frag1 에다가 예매한 사람 이름, 영화 이름 추가
            showConfirmationDialog(selectedMovName, movieDetails);

        });

        imageContainer = view.findViewById(R.id.imageContainer);
        addImagesToContainer();
        imageContainer2 = view.findViewById(R.id.imageContainer2);
        addImagesToContainer2();

        loadDataFromDatabase();




        return view;
    }


    // 추가: 선택한 카드의 색상을 업데이트하는 메서드
    private void updateCardViewColors() {
        for (int i = 1; i <= 83; i++) {
            int cardId = getResources().getIdentifier("timecard" + i, "id", requireContext().getPackageName());
            CardView timeCard = fragmentView.findViewById(cardId);

            if (cardId == selectedCardId) {
                // 선택한 카드의 경우 배경색을 변경합니다.
                timeCard.setCardBackgroundColor(getResources().getColor(R.color.gray));
            } else {
                // 선택하지 않은 카드의 경우 기본 배경색으로 설정합니다.
                timeCard.setCardBackgroundColor(getResources().getColor(R.color.nonselected));
            }
        }
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



    private void updateSeatTotalForTimeId(int timeId){

        String textViewId = "seatremain_" + timeId;
        int resId = getResources().getIdentifier(textViewId,"id",requireContext().getPackageName());
        TextView seatTotalTextView = fragmentView.findViewById(resId);
        if(seatTotalTextView != null){
            if(seatCountMap.containsKey(timeId)){

                int seatCount = seatCountMap.get(timeId);
                seatTotalTextView.setText(String.valueOf(seatCount));
            }

        }



    }


    private void loadDataFromDatabase(){

        SeatDBHelper dbHelper = new SeatDBHelper(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.query(

                SeatTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null

        );

        if(cursor!=null){
            while(cursor.moveToNext()){

                int timeId = cursor.getInt(cursor.getColumnIndexOrThrow(SeatTable.COLUMN_TIME_ID));
                int seatCount = dbHelper.getSeatCountForTimeId(timeId);

                seatCountMap.put(timeId,seatCount);

                updateSeatTotalForTimeId(timeId);

            }

            cursor.close();


        }

        db.close();


    }
    private void deleteSelectedTimeCardFromDatabase() {
        if (selectedTimeCardId != -1) {
            SeatDBHelper dbHelper = new SeatDBHelper(requireContext());
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Implement the SQL DELETE statement to delete the selected time card
            String selection = SeatTable.COLUMN_TIME_ID + " = ?";
            String[] selectionArgs = {String.valueOf(selectedTimeCardId)};

            String[] projection = {"id"};
            Cursor cursor = db.query(SeatTable.TABLE_NAME, projection, selection, selectionArgs, null,null,null);

            if(cursor.moveToFirst()){
                long idToDelete = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                db.delete(SeatTable.TABLE_NAME,"id = ?",new String[]{String.valueOf(idToDelete)});

                Toast.makeText(requireContext(),"예매에 성공하였습니다!",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(requireContext(), "좌석이 없어요!", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
            db.close();

            // Optionally, reset the selected time card ID after deletiond
            selectedTimeCardId = -1;
        } else {

        }
    }


    private String getMovieDetailsForTimeCardId(int timeCardId){


        if (timeCardId>=1 && timeCardId<=5){
            movie_name = "다크 나이트" ;

        }

        else if (timeCardId>=6 && timeCardId<=10){
            movie_name = "아이언맨 3";

        }
        else if (timeCardId>=11 && timeCardId<=15){
            movie_name = "듄";

        }
        else if (timeCardId>=16 && timeCardId<=20){
            movie_name = "먼 훗날 우리";

        }
        else if (timeCardId>=21 && timeCardId<=24){
            movie_name = "부당거래";

        }
        else if (timeCardId>=25 && timeCardId<=27){
            movie_name = "극장판 귀멸의 칼날: 무한열차편";

        }
        else if (timeCardId>=28 && timeCardId<=31){
            movie_name = "노량: 죽음의 바다";

        }
        else if (timeCardId>=32 && timeCardId<=35){
            movie_name = "날씨의 아이";

        }
        else if (timeCardId>=36 && timeCardId<=39){
            movie_name = "오펜하이머";

        }
        else if (timeCardId>=40 && timeCardId<=43){
            movie_name = "엑스맨: 데이즈 오브 퓨쳐 패스트";

        }
        else if (timeCardId>=44 && timeCardId<=47){
            movie_name = "신세계";

        }
        else if (timeCardId>=48 && timeCardId<=51){
            movie_name = "범죄도시";

        }
        else if (timeCardId>=52 && timeCardId<=55){
            movie_name = "해리 포터와 마법사의 돌";

        }
        else if (timeCardId>=56 && timeCardId<=59){
            movie_name = "서울의 봄";

        }
        else if (timeCardId>=60 && timeCardId<=63){
            movie_name = "엘리멘탈";

        }
        else if (timeCardId>=64 && timeCardId<=67){
            movie_name = "스즈메의 문단속";

        }
        else if (timeCardId>=68 && timeCardId<=71){
            movie_name = "밀수";

        }
        else if (timeCardId>=72 && timeCardId<=75){
            movie_name = "더 퍼스트 슬램덩크";

        }
        else if (timeCardId>=76 && timeCardId<=79){
            movie_name = "가디언즈 오브 갤럭시";

        }
        else if (timeCardId>=80 && timeCardId<=83){
            movie_name = "콘크리트 유토피아";

        }

        return movie_name;


    }

    private void showConfirmationDialog(String selectedMovName, String movieDetails) {
        // Create a new instance of ConfirmDialogFragment
        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance(selectedMovName, movieDetails);

        // Show the confirmation dialog
        confirmDialogFragment.show(requireActivity().getSupportFragmentManager(), "dialog_confirm");
    }




}


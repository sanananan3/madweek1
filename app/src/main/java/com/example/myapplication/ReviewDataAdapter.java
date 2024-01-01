package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewDataAdapter extends RecyclerView.Adapter<ReviewDataAdapter.MyViewHolder>{
    Context context;
    ArrayList<ReviewData> reviewDataArrayList = new ArrayList<>();
    ReviewDataAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReviewData reviewData = reviewDataArrayList.get(position);

        holder.movie_text.setText(String.valueOf(reviewData.getMovie())); //영화제목
        holder.review_text.setText(String.valueOf(reviewData.getReview())); //리뷰
        holder.ratingBar.setRating(reviewData.getScore()); //점수
    }

    @Override
    public int getItemCount() {
        return reviewDataArrayList.size();
    }
    public void removeItem(){ reviewDataArrayList.clear(); }
    public void addItem(ReviewData item){
        reviewDataArrayList.add(item);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView movie_text, review_text;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_text = itemView.findViewById(R.id.id_movie);
            review_text = itemView.findViewById(R.id.id_review);
            ratingBar = itemView.findViewById(R.id.ratingBar_result);
        }
    }
}

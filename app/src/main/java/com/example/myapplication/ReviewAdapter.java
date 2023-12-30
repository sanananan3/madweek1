package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private ArrayList<Review> mData = null;
    private String movie = null;

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView textview_review_item;
        protected TextView selected_movie;

        public ViewHolder(View itemView){
            super(itemView);

            this.textview_review_item = itemView.findViewById(R.id.textview_review_item);
            this.selected_movie = itemView.findViewById(R.id.selected_movie);
        }
    }

    ReviewAdapter(ArrayList<Review> list, String selected_movie){
        mData = list;
        movie = selected_movie;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.review_item, parent, false);
        ReviewAdapter.ViewHolder vh = new ReviewAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        holder.textview_review_item.setText(mData.get(position).getReview());
        holder.selected_movie.setText(movie);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

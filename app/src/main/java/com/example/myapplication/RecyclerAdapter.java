package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context mContext;
    ArrayList<DataDTO> data;
    private FragmentManager fragmentManager;


    public RecyclerAdapter(Context mContext, ArrayList<DataDTO> data, FragmentManager fragmentManager){

        this.mContext=mContext;
        this.data= data;
        this.fragmentManager = fragmentManager;

    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from (parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position){

        holder.tv_name.setText(data.get(position).getName());
        holder.tv_mbti.setText(data.get(position).getMbti());
        holder.tv_birth.setText(data.get(position).getBirth());
        holder.tv_call.setText(data.get(position).getCall());
        holder.iv_image.setImageResource(data.get(position).getImageResource());

        holder.rv_button.setOnClickListener(v -> {
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            myDialogFragment.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "MyDialogFragment");
        });
    }

    public int getItemCount(){

        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_mbti, tv_birth, tv_call;
        ImageView iv_image;
        Button rv_button;
        public ViewHolder( View itemView){

            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_mbti=itemView.findViewById(R.id.tv_mbti);
            tv_birth = itemView.findViewById(R.id.tv_birth);
            tv_call = itemView.findViewById(R.id.tv_call);
            iv_image = itemView.findViewById(R.id.iv_image);
            rv_button = itemView.findViewById(R.id.rv_button);

        }
    }
}
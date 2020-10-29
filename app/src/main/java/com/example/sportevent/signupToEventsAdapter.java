package com.example.sportevent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class signupToEventsAdapter extends  RecyclerView.Adapter<signupToEventsAdapter.ViewHolder> {

    private ArrayList<SignupToEvenItem> signupToEvenItems;

    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private ArrayList<Integer> mImage = new ArrayList<>();


    public signupToEventsAdapter(ArrayList<String> Title, ArrayList<String> desc, ArrayList<Integer> image ){
            mTitle = Title;
            mDesc = desc;
            mImage = image;
    }

    public signupToEventsAdapter(ArrayList<SignupToEvenItem> signupToEvenItemArrayList) {
        signupToEvenItems = signupToEvenItemArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.signupevent_list_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(mTitle.get(position));
        holder.desc.setText(mDesc.get(position));
        holder.image.setImageResource(mImage.get(position));


    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.signupEventImage);
            title = itemView.findViewById(R.id.signupEventName);
            desc = itemView.findViewById(R.id.signupEventDesc);
        }
    }
}

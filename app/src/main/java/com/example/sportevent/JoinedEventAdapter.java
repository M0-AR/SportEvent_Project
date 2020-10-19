package com.example.sportevent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JoinedEventAdapter extends RecyclerView.Adapter<JoinedEventAdapter.JoinedEventViewHolder> {
    private ArrayList<JoinedEventItem> mJoinedEventList;

    public static class JoinedEventViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public JoinedEventViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
            this.mTextView1 = itemView.findViewById(R.id.textView1);
            this.mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public JoinedEventAdapter(ArrayList<JoinedEventItem> joinedEventList) {
        this.mJoinedEventList = joinedEventList;
    }


    @NonNull
    @Override
    public JoinedEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joined_event_item, parent, false);
        JoinedEventViewHolder joinedEventViewHolder = new JoinedEventViewHolder(view);
        return joinedEventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JoinedEventViewHolder holder, int position) {
        JoinedEventItem currentItem =  mJoinedEventList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getTextView1());
        holder.mTextView2.setText(currentItem.getTextView2());
    }

    @Override
    public int getItemCount() {
        return mJoinedEventList.size();
    }
}

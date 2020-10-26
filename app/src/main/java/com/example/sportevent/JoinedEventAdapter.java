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
    private static ClickListener mClickListener;

    public interface ClickListener {
        // Todo why this suggest safe delete
        void mClick(View view, int position);
    }

    public static class JoinedEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public JoinedEventViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
            this.mTextView1 = itemView.findViewById(R.id.textView1);
            this.mTextView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.mClick(v, getAdapterPosition());
           // Toast.makeText(v.getContext(), "Event Name: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }




    }

    public JoinedEventAdapter(ArrayList<JoinedEventItem> joinedEventList, ClickListener clickListener) {
        this.mJoinedEventList = joinedEventList;
        mClickListener = clickListener;
    }


    @NonNull
    @Override
    public JoinedEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joined_event_item, parent, false);
        JoinedEventViewHolder joinedEventViewHolder = new JoinedEventViewHolder(view);
        return joinedEventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JoinedEventViewHolder holder, final int position) {
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

package com.example.sportevent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private static List<Event> mEventList;
    private static OnEventClickListener mOnEventClickListener;

    public EventAdapter(ArrayList<Event> eventList) {
        mEventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        if (mEventList != null) {
            Event currentItem = mEventList.get(position);

            holder.mImageView.setImageResource(currentItem.getImageResource());
            holder.mTextView1.setText(currentItem.getTextView1());
            holder.mTextView2.setText(currentItem.getTextView2());
        } // Todo how to cover this problem 9:37 https://www.youtube.com/watch?v=reSPN7mgshI&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118&index=6
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
            this.mTextView1 = itemView.findViewById(R.id.textView1);
            this.mTextView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mOnEventClickListener != null && position != RecyclerView.NO_POSITION)
                        mOnEventClickListener.onEventClick(mEventList.get(position));
                }
            });
        }
    }

    public interface OnEventClickListener {
        // Todo why this suggest safe delete
        void onEventClick(Event event);
    }



    // Instead of passing reference from constructor
    public void setOnEventClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }

}

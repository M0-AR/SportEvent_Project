package com.example.sportevent.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private  List<Event> mEventList;
    private static OnEventClickListener mOnEventClickListener;
    private Context mContext;
    private LAYOUT mLAYOUT;

    public EventAdapter(Context context, LAYOUT layout) {
        this.mContext = context;
        this.mLAYOUT = layout;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (mLAYOUT == LAYOUT.HOME_LIST)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.created_item, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);

        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        if (mEventList != null) {
            Event currentItem = mEventList.get(position);

            Glide.with(mContext)
                    .asBitmap()
                    .load(currentItem.getImageURL())
                    .into(holder.mImageView);
            holder.mTextView1.setText(currentItem.getEventName());
            holder.mTextView2.setText(currentItem.getEventDescription());



        holder.mParent.setOnClickListener(v -> {
            if (mOnEventClickListener != null && position != RecyclerView.NO_POSITION)
                mOnEventClickListener.onEventClick(mEventList.get(position));
        });

        }
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    // Instead of passing reference from constructor
    public void setOnEventClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }


    public void setMEventList(List<Event> mEventList) {
        this.mEventList = mEventList;
        notifyDataSetChanged();
    }



    public interface OnEventClickListener {
        void onEventClick(Event event);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView1;
        private TextView mTextView2;
        private CardView mParent;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
            this.mTextView1 = itemView.findViewById(R.id.textView_title);
            this.mTextView2 = itemView.findViewById(R.id.textView_description);
            this.mParent = itemView.findViewById(R.id.event_item_parent);
        }
    }
}

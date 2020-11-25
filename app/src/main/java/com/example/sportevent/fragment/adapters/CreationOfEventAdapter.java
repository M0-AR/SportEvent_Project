package com.example.sportevent.fragment.adapters;

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
import com.example.sportevent.data.Event;

import java.util.ArrayList;
import java.util.List;

public class CreationOfEventAdapter extends RecyclerView.Adapter<CreationOfEventAdapter.ViewHolder> {
    private static List<Event> mEventList;
    private static OnEventClickListener mOnEventClickListener;
    private Context mContext;

    public CreationOfEventAdapter(ArrayList<Event> eventList, Context context) {
        mEventList = eventList;
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.created_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (mEventList != null) {
            Event currentItem = mEventList.get(position);

            Glide.with(mContext)
                    .asBitmap()
                    .load(currentItem.getImageURL())
                    .into(holder.mImageView);
            holder.mTextView1.setText(currentItem.getEventName());
            holder.mTextView2.setText(currentItem.getEventDescription());



            holder.mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnEventClickListener != null && position != RecyclerView.NO_POSITION)
                        mOnEventClickListener.onEventClick(mEventList.get(position));
                }
            });

        } // Todo how to cover this problem 9:37 https://www.youtube.com/watch?v=reSPN7mgshI&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118&index=6
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }


    public interface OnEventClickListener {
        // Todo why this suggest safe delete
        void onEventClick(Event event);
    }



    // Instead of passing reference from constructor
    public void setOnEventClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        private CardView mParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
            this.mTextView1 = itemView.findViewById(R.id.textView_title);
            this.mTextView2 = itemView.findViewById(R.id.textView_description);
            this.mParent = itemView.findViewById(R.id.event_item_parent);
        }
    }


}

package com.example.sportevent.view.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {
    private List<Participant> mParticipantList;

    public ParticipantAdapter(ArrayList<Participant> participantList) {
        mParticipantList = participantList;
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_item, parent, false);
        ParticipantViewHolder participantViewHolder = new ParticipantViewHolder(view);
        return participantViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, final int position) {
        if (mParticipantList != null) {
            Participant participant = mParticipantList.get(position);

            holder.mTextView1.setText(participant.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mParticipantList.size();
    }

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;

        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTextView1 = itemView.findViewById(R.id.textView1);
            this.mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

}

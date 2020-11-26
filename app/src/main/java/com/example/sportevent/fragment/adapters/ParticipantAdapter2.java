package com.example.sportevent.fragment.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportevent.R;
import com.example.sportevent.data.PersonalEventData;
import com.example.sportevent.data.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantAdapter2 extends RecyclerView.Adapter<ParticipantAdapter2.ParticipantViewHolder> {
    private  List<Participant> mParticipantList;
    private List<PersonalEventData> mPersonalEventData;

    public ParticipantAdapter2(ArrayList<Participant> participantList, ArrayList<PersonalEventData> personalEventData) {
        mParticipantList = participantList;
        mPersonalEventData = personalEventData;
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_item2, parent, false);
        return new ParticipantViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, final int position) {
        if (mParticipantList != null && mPersonalEventData != null) {
            Participant participant = mParticipantList.get(position);
            PersonalEventData personalEventData = mPersonalEventData.get(position);

            //race result data
            holder.mTextView3.setText((personalEventData.getResult()+1) + ((position < 9) ? "  " : ""));

            holder.mTextView1.setText(participant.getName());
            holder.mTextView2.setText((participant.getId()+1) + "");
        } // Todo how to cover this problem 9:37 https://www.youtube.com/watch?v=reSPN7mgshI&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118&index=6
    }

    @Override
    public int getItemCount() {
        return mParticipantList.size();
    }

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTextView1 = itemView.findViewById(R.id.textView1);
            this.mTextView2 = itemView.findViewById(R.id.textView2);
            this.mTextView3 = itemView.findViewById(R.id.textView3);
        }
    }

}

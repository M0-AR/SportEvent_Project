package com.example.sportevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompletedParticipantsRecyclerAdapter extends RecyclerView.Adapter<CompletedParticipantsRecyclerAdapter.MyViewHolder> {

    String[] data1;
    String[] data2;
    String[] data3;

    public CompletedParticipantsRecyclerAdapter(String[] s1, String[] s2, String[] s3){
        data1 = s1;
        data2 = s2;
        data3 = s3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.completedparticipant_recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText3 = itemView.findViewById(R.id.completedPlads);
            myText1 = itemView.findViewById(R.id.completedDeltager);
            myText2 = itemView.findViewById(R.id.completedDeltagerNr);

        }
    }
}

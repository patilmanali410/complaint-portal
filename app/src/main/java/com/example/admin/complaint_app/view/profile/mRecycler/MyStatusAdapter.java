package com.example.admin.complaint_app.view.profile.mRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.complaint_app.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.List;


public class MyStatusAdapter extends RecyclerView.Adapter<MyStatusHolder> {

    Context c;
    List<String> complaintTitle;
    List<String> complaintDescription;
    List<String> complaintDate;
    List<String> complaintTotalVotes;

    public MyStatusAdapter(Context c,List<String> complaintTitle, List<String> complaintDescription, List<String> complaintTotalVotes, List<String> complaintDate){
        this.c=c;
        this.complaintTitle=complaintTitle;
        this.complaintDescription=complaintDescription;
        this.complaintDate=complaintDate;
        this.complaintTotalVotes=complaintTotalVotes;
    }


    @NonNull
    @Override
    public MyStatusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.status,viewGroup,false);

        return new MyStatusHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatusHolder myStatusHolder, int i) {
        myStatusHolder.complaintTitle.setText(complaintTitle.get(i));
        myStatusHolder.complaintDescription.setText(complaintDescription.get(i));
        myStatusHolder.complaintDate.setText(complaintDate.get(i));
        myStatusHolder.complaintVotes.setText(complaintTotalVotes.get(i)+" Votes");



    }

    @Override
    public int getItemCount() {
        return complaintTitle.size();
    }
}

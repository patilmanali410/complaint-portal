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
    List<String> complaintLevel;
    List<String> complaintStatusDescription;
    final String level1="Complaint Filed Successfully";
    final String level2="Viewed By HOD";
    final String level3="Inprocess";
    final String level4="Complaint Solved";

    public MyStatusAdapter(Context c,List<String> complaintTitle, List<String> complaintDescription, List<String> complaintTotalVotes, List<String> complaintDate,List<String> complaintlevel,List<String> complaintStatusDescription){
        this.c=c;
        this.complaintTitle=complaintTitle;
        this.complaintDescription=complaintDescription;
        this.complaintDate=complaintDate;
        this.complaintTotalVotes=complaintTotalVotes;
        this.complaintLevel=complaintlevel;
        this.complaintStatusDescription=complaintStatusDescription;
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
        myStatusHolder.complaintDescription.setText("Status Description: "+complaintStatusDescription.get(i));
        myStatusHolder.complaintDate.setText(complaintDate.get(i));
        myStatusHolder.complaintVotes.setText(complaintTotalVotes.get(i)+" Votes");
        if(complaintLevel.get(i).equals("1")){
            myStatusHolder.level.setText("Level "+complaintLevel.get(i)+": "+level1);
        }
        if(complaintLevel.get(i).equals("2")){
            myStatusHolder.level.setText("Level "+complaintLevel.get(i)+": "+level2);
        }
        if(complaintLevel.get(i).equals("3")){
            myStatusHolder.level.setText("Level "+complaintLevel.get(i)+": "+level3);
        }
        if(complaintLevel.get(i).equals("4")){
            myStatusHolder.level.setText("Level "+complaintLevel.get(i)+": "+level4);
        }



    }

    @Override
    public int getItemCount() {
        return complaintTitle.size();
    }
}

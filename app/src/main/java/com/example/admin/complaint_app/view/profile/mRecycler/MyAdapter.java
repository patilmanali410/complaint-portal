package com.example.admin.complaint_app.view.profile.mRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.admin.complaint_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    List<String> complaintTitle;
    List<String> complaintDescription;
    List<String> complaintDate;
    List<String> complaintTotalVotes;
    List<String> imageUrl;

    public MyAdapter(Context c, List<String> complaintTitle, List<String> complaintDescription, List<String> complaintTotalVotes, List<String> complaintDate,List<String> imageUrl){
        this.c=c;
        this.complaintTitle=complaintTitle;
        this.complaintDescription=complaintDescription;
        this.complaintDate=complaintDate;
        this.complaintTotalVotes=complaintTotalVotes;
        this.imageUrl=imageUrl;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complaint,viewGroup,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.complaintTitle.setText(complaintTitle.get(i));
        myHolder.complaintDescription.setText(complaintDescription.get(i));
        myHolder.complaintDate.setText(complaintDate.get(i));
        myHolder.complaintVotes.setText(complaintTotalVotes.get(i)+" Votes");
        Glide.with(c).load(imageUrl.get(i)).into(myHolder.imageView);
        }

    @Override
    public int getItemCount() {
        return complaintTitle.size();
    }
}

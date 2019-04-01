package com.example.admin.complaint_app.view.profile.mRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.complaint_app.R;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    String[] complaints;

    public MyAdapter(Context c,String[] complaints){
        this.c=c;
        this.complaints=complaints;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complaint,viewGroup,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.nametxt.setText(complaints[i]);

    }

    @Override
    public int getItemCount() {
        return complaints.length;
    }
}

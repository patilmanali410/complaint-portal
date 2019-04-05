package com.example.admin.complaint_app.view.profile.mRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.complaint_app.R;

public class MyStatusAdapter extends RecyclerView.Adapter<MyStatusHolder> {

    Context c;
    String[] complaints;

    public MyStatusAdapter(Context c,String[] complaints){
        this.c=c;
        this.complaints=complaints;
    }


    @NonNull
    @Override
    public MyStatusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.status,viewGroup,false);

        return new MyStatusHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatusHolder myStatusHolder, int i) {
        myStatusHolder.nametxt.setText(complaints[i]);

    }

    @Override
    public int getItemCount() {
        return complaints.length;
    }
}

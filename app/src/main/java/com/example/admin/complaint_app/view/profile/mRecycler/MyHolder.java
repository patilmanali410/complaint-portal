package com.example.admin.complaint_app.view.profile.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.complaint_app.R;

public class MyHolder extends RecyclerView.ViewHolder {

    TextView nametxt;

    public MyHolder(View itemView){
        super(itemView);
        nametxt=(TextView) itemView.findViewById(R.id.nameTxt);

    }
}

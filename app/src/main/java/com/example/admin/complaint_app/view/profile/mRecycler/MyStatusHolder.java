package com.example.admin.complaint_app.view.profile.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.complaint_app.R;

public class MyStatusHolder extends RecyclerView.ViewHolder {

    TextView nametxt;

    public MyStatusHolder(View itemView){
        super(itemView);
        nametxt=(TextView) itemView.findViewById(R.id.blog_user_name);

    }
}

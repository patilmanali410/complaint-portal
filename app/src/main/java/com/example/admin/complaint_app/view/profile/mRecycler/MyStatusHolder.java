package com.example.admin.complaint_app.view.profile.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.complaint_app.R;

public class MyStatusHolder extends RecyclerView.ViewHolder {

    TextView complaintTitle,complaintDescription,complaintDate,complaintVotes,level;


    public MyStatusHolder(View itemView){
        super(itemView);
        complaintTitle=(TextView) itemView.findViewById(R.id.blog_user_name);
        complaintDescription=(TextView) itemView.findViewById(R.id.blog_desc);
        complaintDate=(TextView) itemView.findViewById(R.id.blog_date);
        complaintVotes=(TextView) itemView.findViewById(R.id.blog_like_count);
        level=(TextView) itemView.findViewById(R.id.textView);

    }
}

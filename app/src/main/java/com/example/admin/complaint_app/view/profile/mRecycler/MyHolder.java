package com.example.admin.complaint_app.view.profile.mRecycler;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.complaint_app.R;

public class MyHolder extends RecyclerView.ViewHolder {

    TextView complaintTitle,complaintDescription,complaintDate,complaintVotes;
    ImageView imageView;
    Button voteButton;

    public MyHolder(View itemView){
        super(itemView);
        complaintTitle=(TextView) itemView.findViewById(R.id.blog_user_name);
        complaintDescription=(TextView) itemView.findViewById(R.id.blog_desc);
        complaintDate=(TextView) itemView.findViewById(R.id.blog_date);
        complaintVotes=(TextView) itemView.findViewById(R.id.blog_like_count);
        imageView=(ImageView) itemView.findViewById(R.id.blog_image);
        voteButton=(Button) itemView.findViewById(R.id.votebutton);

    }
}

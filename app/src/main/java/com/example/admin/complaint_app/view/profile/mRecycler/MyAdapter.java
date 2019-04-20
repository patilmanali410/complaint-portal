package com.example.admin.complaint_app.view.profile.mRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.complaint_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    List<String> complaintTitle;
    List<String> complaintDescription;
    List<String> complaintDate;
    List<String> complaintTotalVotes;
    List<String> imageUrl;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth  firebaseAuth;
    List<String> complaintID=new ArrayList<>();
    Map<String,String> likesMap;
    String dateString;
    int votesCount;

    public MyAdapter(Context c, List<String> complaintTitle, List<String> complaintDescription, List<String> complaintTotalVotes, List<String> complaintDate,List<String> imageUrl,List<String> complaintID){
        this.c=c;
        this.complaintTitle=complaintTitle;
        this.complaintDescription=complaintDescription;
        this.complaintDate=complaintDate;
        this.complaintTotalVotes=complaintTotalVotes;
        this.imageUrl=imageUrl;
        this.complaintID=complaintID;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complaint,viewGroup,false);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        likesMap=new HashMap<>();
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        final String currentUserId=firebaseAuth.getCurrentUser().getUid();
        myHolder.complaintTitle.setText(complaintTitle.get(i));
        myHolder.complaintDescription.setText(complaintDescription.get(i));
        myHolder.complaintDate.setText(complaintDate.get(i));
        //myHolder.complaintVotes.setText(complaintTotalVotes.get(i)+" Votes");
        Glide.with(c).load(imageUrl.get(i)).into(myHolder.imageView);

        //likes count
        firebaseFirestore.collection("Complaints/"+complaintID.get(i)+"/Likes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(!queryDocumentSnapshots.isEmpty()){
                    votesCount=queryDocumentSnapshots.size();
                    myHolder.complaintVotes.setText(votesCount+ " votes");
                }
                else{
                    myHolder.complaintVotes.setText("0");
                }
            }
        });

        //votes
        myHolder.voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("Complaints/"+complaintID.get(i)+"/Likes").document(currentUserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(!task.getResult().exists()){
                            Map<String,Object> Votes=new HashMap<>();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date date = new Date();
                            dateString = dateFormat.format(date);

                            likesMap.put("timestamp",dateString);
                            firebaseFirestore.collection("Complaints/"+complaintID.get(i)+"/Likes").document(currentUserId).set(likesMap);


                        }
                        else{
                            Toast.makeText(c,"Already Voted",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        }

    @Override
    public int getItemCount() {
        return complaintTitle.size();
    }
}

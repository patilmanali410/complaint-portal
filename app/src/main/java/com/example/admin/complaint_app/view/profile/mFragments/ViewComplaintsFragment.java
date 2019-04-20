package com.example.admin.complaint_app.view.profile.mFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.models.Complaint;
import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.view.profile.mRecycler.MyAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ViewComplaintsFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    CollectionReference colRefComplaint;
    List<String> complaintTitle=new ArrayList<>();
    List<String> complaintDescription=new ArrayList<>();
    List<String> complaintDate=new ArrayList<>();
    List<String> complaintTotalVotes=new ArrayList<>();
    List<String> imageUrl=new ArrayList<>();
    Complaint complaint;
    List<String> complaintID=new ArrayList<>();


    String[]  complaints={"complaint1","complaint2","complaint3","complaint4","complaint5","complaint6"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_viewcomplaints, container, false);
        RecyclerView rv =rootView.findViewById(R.id.viewcomplaints);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        getComplaintData(rv,this.getActivity());
        return rootView;
    }



    private void getComplaintData(final RecyclerView rv, final FragmentActivity activity) {
        colRefComplaint=db.collection("Complaints");
        colRefComplaint.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){

                    complaintID.add(documentSnapshot.getId());
                    complaint=documentSnapshot.toObject(Complaint.class);
                    Log.d("complaint","complaint title"+complaint.getTitle());
                    complaintTitle.add(complaint.getTitle());
                    complaintDescription.add(complaint.getDescription());
                    complaintDate.add(complaint.getDate());
                    complaintTotalVotes.add(Integer.toString(complaint.getTotalVotes()));
                    imageUrl.add(complaint.getImageUri());
                    Log.d("imageurl","iamge url is "+imageUrl.get(0));

                }
                rv.setLayoutManager(new LinearLayoutManager(activity));
                MyAdapter myAdapter=new MyAdapter(getContext(),complaintTitle,complaintDescription,complaintTotalVotes,complaintDate,imageUrl,complaintID);
                rv.setAdapter(myAdapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("complaint","error "+e.getMessage());
            }
        });
    }


    private void incrementVote() {
        Log.d("vote","inside incrementvote");
    }

}
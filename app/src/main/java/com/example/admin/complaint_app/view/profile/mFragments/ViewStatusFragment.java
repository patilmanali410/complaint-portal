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

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.models.Complaint;
import com.example.admin.complaint_app.view.profile.mRecycler.MyAdapter;
import com.example.admin.complaint_app.view.profile.mRecycler.MyStatusAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.StateProgressBar.StateNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class ViewStatusFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    CollectionReference colRefComplaint;
    List<String> complaintTitle=new ArrayList<>();
    List<String> complaintDescription=new ArrayList<>();
    List<String> complaintDate=new ArrayList<>();
    List<String> complaintTotalVotes=new ArrayList<>();
    Complaint complaint;
    StateProgressBar stateProgressBar;
    String[] descriptionData = {"Details", "Status", "Photo", "Confirm"};
    StateNumber st;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard

        View rootView = inflater.inflate(R.layout.fragment_viewstatus, container, false);
        RecyclerView rv=rootView.findViewById(R.id.viewstatus);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();



        getComplaintStatusData(rv,this.getActivity());
        return rootView;

    }

    private void getComplaintStatusData(final RecyclerView rv, final FragmentActivity activity) {
        colRefComplaint=db.collection("Complaints");
        colRefComplaint.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    complaint=documentSnapshot.toObject(Complaint.class);
                    Log.d("complaint","complaint title"+complaint.getTitle());
                    complaintTitle.add(complaint.getTitle());
                    complaintDescription.add(complaint.getDescription());
                    complaintDate.add(complaint.getDate());
                    complaintTotalVotes.add(Integer.toString(complaint.getTotalVotes()));

                }
                rv.setLayoutManager(new LinearLayoutManager(activity));
                MyStatusAdapter myStatusAdapter= new MyStatusAdapter(getContext(),complaintTitle,complaintDescription,complaintTotalVotes,complaintDate);
                rv.setAdapter(myStatusAdapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("complaint","error "+e.getMessage());
            }
        });
    }
}
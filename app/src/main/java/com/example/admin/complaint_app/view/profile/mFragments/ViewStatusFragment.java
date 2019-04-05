package com.example.admin.complaint_app.view.profile.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.profile.mRecycler.MyStatusAdapter;
import com.kofigyan.stateprogressbar.StateProgressBar;

/**
 * Created by Belal on 1/23/2018.
 */

public class ViewStatusFragment extends Fragment {


    String[]  complaints={"complaint1","complaint2","complaint3","complaint4","complaint5","complaint6"};

    String[] descriptionData = {"Complaint filed", "Complaint reached", "On progress", "Solved"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View rootView = inflater.inflate(R.layout.fragment_viewstatus, container, false);
        RecyclerView rv=rootView.findViewById(R.id.viewstatus);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        MyStatusAdapter myStatusAdapter=new MyStatusAdapter(getContext(),complaints);
        rv.setAdapter(myStatusAdapter);

        





        return rootView;

    }
}
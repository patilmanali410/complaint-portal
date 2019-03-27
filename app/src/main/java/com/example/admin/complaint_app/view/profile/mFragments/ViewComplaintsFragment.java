package com.example.admin.complaint_app.view.profile.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.profile.mRecycler.MyAdapter;

/**
 * Created by Belal on 1/23/2018.
 */

public class ViewComplaintsFragment extends Fragment {
    String[] complaints={"ritik","sumil","bittuu","hangusa","dafg","fja","ahjfkgas","asdfghkj"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_viewcomplaints, container, false);
        RecyclerView rv=rootView.findViewById(R.id.viewcomplaints);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        MyAdapter myAdapter=new MyAdapter(this.getActivity(),complaints);
        rv.setAdapter(myAdapter);
        return rootView;
    }
}
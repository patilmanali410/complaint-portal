package com.example.admin.complaint_app.view.profile.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.view.profile.mRecycler.MyAdapter;


public class ViewComplaintsFragment extends Fragment {

    String[]  complaints={"complaint1","complaint2","complaint3","complaint4","complaint5","complaint6"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_viewcomplaints, container, false);
        RecyclerView rv=rootView.findViewById(R.id.viewcomplaints);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        MyAdapter myAdapter=new MyAdapter(getContext(),complaints);
        rv.setAdapter(myAdapter);
        return rootView;
    }

}
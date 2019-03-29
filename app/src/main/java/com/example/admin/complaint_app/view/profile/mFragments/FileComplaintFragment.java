package com.example.admin.complaint_app.view.profile.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.Gravity;

import com.example.admin.complaint_app.R;

/**
 * Created by Belal on 1/23/2018.
 */

public class FileComplaintFragment extends Fragment implements OnClickListener {

    private static final int CAMERA_PIC_REQUEST = 2500;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard

        View v = inflater.inflate(R.layout.fragment_filecomplaints, container, false);

        TextView read = (TextView) v.findViewById(R.id.readconditions);
        Button file=(Button) v.findViewById(R.id.filecomplaintbutton);
        read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new read_terms_and_conditions());
                fr.commit();
            }
        });

        file.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(
                        //getActivity(),"Custom Toast From Fragment",Toast.LENGTH_LONG
                        getActivity().getApplicationContext(), "Complaint filed succesfully",
                        Toast.LENGTH_SHORT
                );
                // Set the Toast display position layout center
                toast.setGravity(Gravity.BOTTOM,25,250);
                // Finally, show the toast
                toast.show();
            }
            });



        return v;

        

    }


    @Override
    public void onClick(View v) {

    }
}
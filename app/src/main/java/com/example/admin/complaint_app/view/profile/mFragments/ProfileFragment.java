package com.example.admin.complaint_app.view.profile.mFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.login.login;
import com.example.admin.complaint_app.view.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Belal on 1/23/2018.
 */

public class ProfileFragment extends Fragment implements OnClickListener {
    FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        Button logout=(Button) v.findViewById(R.id.logoutbutton);
        mAuth=FirebaseAuth.getInstance();
        Log.d("fragment","inside fragment");

        logout.setOnClickListener(this);


        return v;
    }

    private void sendToLogin() {
        Intent intent=new Intent(getActivity(),login.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        mAuth.signOut();
        sendToLogin();
    }
}
package com.example.admin.complaint_app.view.profile.mFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.login.login;
import com.example.admin.complaint_app.view.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by Belal on 1/23/2018.
 */

public class ProfileFragment extends Fragment implements OnClickListener {
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    DocumentReference docRef;
    String UID;
    TextView email1,name1,contact1,details1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        email1=v.findViewById(R.id.student_email);
        name1=v.findViewById(R.id.student_name);
        contact1=v.findViewById(R.id.student_contact);
        details1=v.findViewById(R.id.student_coursedetails);


        db=FirebaseFirestore.getInstance();
        Button logout=(Button) v.findViewById(R.id.logoutbutton);
        mAuth=FirebaseAuth.getInstance();
        UID=mAuth.getCurrentUser().getUid();
        docRef=db.collection("Students").document(UID);
        Log.d("fragment","inside fragment");

        logout.setOnClickListener(this);
        getDetails();


        return v;
    }

    private void getDetails() {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        updateUI(document);


                    }
                    else{
                        Log.d("document","No such Documnet");
                    }
                }
                else{
                    String error=task.getException().getMessage();
                    Log.d("document","error"+error);
                }
            }
        });
    }

    private void updateUI(DocumentSnapshot document) {
        String email=document.getData().get("email").toString();
        String name=document.getData().get("name").toString();
        String contact=document.getData().get("contact").toString();
        String department=document.getData().get("department").toString();
        String year=document.getData().get("year").toString();

        email1.setText(email);
        name1.setText(name);
        contact1.setText(contact);
        details1.setText(year+" YEAR -"+department);
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
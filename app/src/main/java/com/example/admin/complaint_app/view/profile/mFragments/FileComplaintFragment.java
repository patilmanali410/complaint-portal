package com.example.admin.complaint_app.view.profile.mFragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.MimeTypeFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.Gravity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.models.Complaint;
import com.example.admin.complaint_app.models.Department;
import com.example.admin.complaint_app.models.Source;
import com.example.admin.complaint_app.models.Status;
import com.example.admin.complaint_app.models.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class FileComplaintFragment extends Fragment implements OnClickListener {

    EditText title, description;
    String title1, description1;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    DocumentReference docRefStudent;
    DocumentReference docRefDepartment;
    DocumentReference docRefComplaint;
    Student s;
    Department d;
    Status st;
    Source so;
    Complaint complaint;
    String dateString;
    // ImageView imageView;
    private Uri imageUri;
    Button addImageButton;
    ProgressBar progressBar;
    String downloadUrl;


    private static final int CAMERA_PIC_REQUEST = 2500;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard

        View v = inflater.inflate(R.layout.fragment_filecomplaints, container, false);

        title = v.findViewById(R.id.entertitle);
        description = v.findViewById(R.id.enterdescription);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference=FirebaseStorage.getInstance().getReference("uploads");
        progressBar=v.findViewById(R.id.imageprogressbar);
        //imageView=v.findViewById(R.id.blog_image);
        addImageButton = v.findViewById(R.id.imagebutton);

        addImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openfileChooser();
            }
        });



        TextView read = (TextView) v.findViewById(R.id.readconditions);
        Button file = (Button) v.findViewById(R.id.filecomplaintbutton);

        file.setOnClickListener(this);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new read_terms_and_conditions());
                fr.commit();
            }
        });


        return v;
    }


    @Override
    public void onClick(View v) {
        Log.d("file", "inside file complaint onclick");
        title1 = title.getText().toString();
        description1 = description.getText().toString();

        if (!TextUtils.isEmpty(title1) && !TextUtils.isEmpty(description1)) {
            getData();
        } else {
            if (TextUtils.isEmpty(title1)) {
                Toast.makeText(getContext(), "Please Enter Title", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(description1)) {
                Toast.makeText(getContext(), "Please Enter Description", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void getData() {
        String UID = mAuth.getCurrentUser().getUid();
        Log.d("id", "id is" + UID);
        docRefStudent = db.collection("Students").document(UID);
        docRefStudent.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("id", "inside exists");

                        //Getting student data;
                        s = new Student(document.getData().get("id").toString(),
                                document.getData().get("name").toString(),
                                document.getData().get("email").toString(),
                                document.getData().get("department").toString(),
                                document.getData().get("year").toString(),
                                document.getData().get("contact").toString());
                        Log.d("ritik", "name is " + s.getName());

                        //get department data
                        getDepartment();

                        //get source data
                        getSource();
                        //get status
                        getStatus();

                        //file complaint
                        fileComplaint();
                    } else {
                        Toast.makeText(getContext(), "No Such records", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void getSource() {
        so = new Source(s, d);
    }

    private void getStatus() {
        //get the status from department;s reply
        st = new Status(1, "Soon action will be taken");
    }

    private void getDepartment() {
        //get department same as logged in user department
        d = new Department("COMPS", "Dr. Megha Trivedi");

    }


    private void fileComplaint() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateString = dateFormat.format(date);
        uploadFile();
    }

    private void openfileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            Log.d("image", "imageUri is " + imageUri);

        } else {
            Log.d("imageuri", "isnise else " + imageUri);
            Toast.makeText(getContext(), "Image not selected properly", Toast.LENGTH_LONG).show();
        }
    }

    private String getFileExtension(Uri uri) {

        ContentResolver cR= getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(imageUri !=null){
            final StorageReference fileReference=storageReference.child(String.valueOf(System.currentTimeMillis())+
            "."+getFileExtension(imageUri));

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }

                            },2000);

                            Toast.makeText(getContext(),"Upload Successfull",Toast.LENGTH_LONG).show();
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl=uri.toString();
                                    complaint = new Complaint(1, so, title1, description1, st, dateString, downloadUrl);
                                    db.collection("Complaints").add(complaint).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getContext(), "Complaint Filed Successfully", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(getContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            });

                            Log.d("imageuri","download url"+downloadUrl);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0 * taskSnapshot.getBytesTransferred() /taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int)progress);
                        }
                    });
            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    downloadUrl=uri.toString();
                }
            });
        }
        else{
            Log.d("imageuri","null imside else");
            return;
        }
    }



}
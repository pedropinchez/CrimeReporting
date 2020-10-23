package com.example.crimereporting.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.crimereporting.Login.SetupActivity;
import com.example.crimereporting.R;
import com.example.crimereporting.Utils.SharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends Fragment {
        private CircleImageView profile_image;
        private TextView profile_name;
        
        private FirebaseAuth mAuth;
        private FirebaseFirestore db;

        private String userId;
        private String postId;
        TextView email,id,phone,age,gender,county,subcounty,location,nearest,disability;
        private Uri mainImageURI = null;
    private RecyclerView post_list;
    FloatingActionButton add;
    Button edit;


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_profile, container, false);
            if(container!=null){
                container.removeAllViews();
            }


            profile_image = view.findViewById(R.id.profile_image);
            profile_name = view.findViewById(R.id.profile_name);
            edit = view.findViewById(R.id.edit);
            add = view.findViewById(R.id.add);
            email=view.findViewById(R.id.profile_email);
            id = view.findViewById(R.id.profile_id);
           age = view.findViewById(R.id.profile_age);
            phone = view.findViewById(R.id.profile_phone);
            gender = view.findViewById(R.id.profile_gender);
            county = view.findViewById(R.id.profile_county);
            subcounty=view.findViewById(R.id.profile_sub);
            location = view.findViewById(R.id.profile_location);
            nearest = view.findViewById(R.id.profile_nearest);
           disability=view.findViewById(R.id.profile_disability);
            mAuth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();
            userId = mAuth.getCurrentUser().getUid();
            setHasOptionsMenu(false);

            db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            String name = task.getResult().getString("name");
                            String image = task.getResult().getString("image");
                            String idno = task.getResult().getString("id");
                            String phoneno = task.getResult().getString("phone");
                            String ageno = task.getResult().getString("age");
                            String genders = task.getResult().getString("gender");
                            String countys = task.getResult().getString("county");
                            String subcountys = task.getResult().getString("subcounty");
                            String locations = task.getResult().getString("location");
                            String nearests = task.getResult().getString("nearest");
                            String disabilitys = task.getResult().getString("disability");
                            profile_name.setText(name);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String profemail = user.getEmail();
                            email.setText(profemail);
                            id.setText("IDNO:"+idno);
                            phone.setText( "PhoneNo:"+phoneno);
                            age.setText("AGE:"+ageno);
                            gender.setText("Gender:"+genders);
                            county.setText("County:"+countys);
                            subcounty.setText("Sub-County:"+subcountys);
                            location.setText("Location:"+locations);
                            nearest.setText("Police Station:"+nearests);
                            disability.setText("Disability:"+disabilitys);




                            mainImageURI = Uri.parse(image);

                            RequestOptions placeholderRequest = new RequestOptions();
                            placeholderRequest.placeholder(R.drawable.default_profile);

                            Glide.with(getActivity()).setDefaultRequestOptions(placeholderRequest).load(image).into(profile_image);

                        }
                    } else {
                        String errorMessage = task.getException().getMessage();
                        Toast.makeText(getActivity(), "Firestore Load Error: " + errorMessage, Toast.LENGTH_LONG).show();
                    }

                }
            });

            db = FirebaseFirestore.getInstance();
            final String user_id = mAuth.getCurrentUser().getUid();
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent settings = new Intent(getActivity(), SetupActivity.class);
                    settings.putExtra("user", user_id);
                    startActivity(settings);

                }
            });
            return view;
        }
    }




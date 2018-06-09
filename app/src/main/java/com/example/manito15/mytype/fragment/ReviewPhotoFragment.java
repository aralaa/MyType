package com.example.manito15.mytype.fragment;


import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.manito15.mytype.MainActivity;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.lib.GoLib;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewPhotoFragment extends Fragment implements View.OnClickListener{
    private static final int GALLERY_CODE = 10;
    private final String TAG = this.getClass().getSimpleName();
    Activity context;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    View v;

    public ReviewPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = this.getActivity();
        v =  inflater.inflate(R.layout.fragment_review_photo, container, false);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_prev = (Button) v.findViewById(R.id.btn_prev);
        Button btn_complete = (Button) v.findViewById(R.id.btn_complete);
        Button btn_camera=(Button) v.findViewById(R.id.btn_camera);

        btn_prev.setOnClickListener(this);
        btn_complete.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_prev:
                GoLib.getInstance().goBackFragment(((AppCompatActivity)getActivity()).getSupportFragmentManager());
                break;
            case R.id.btn_complete:
                intent = new Intent(((AppCompatActivity)getActivity()).getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_camera:
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                startActivityForResult(intent,GALLERY_CODE);
                break;

        }

    }
    public String getPath(Uri uri){

        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context,uri,proj,null,null,null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_CODE) {

            StorageReference storageRef = storage.getReferenceFromUrl("gs://mytype-3bcda.appspot.com");

            Uri file = Uri.fromFile(new File(getPath(data.getData())));
            StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
            UploadTask uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                }
            });




        }
    }
}
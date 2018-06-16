package com.example.manito15.mytype.Review;


import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.manito15.mytype.App.MainActivity;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.item.ImageDTO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * 선물 후기를 등록하는 액티비티
 */
public class ReviewReceiverInfoFragment extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    Activity context;
    View v;

    private static final int GALLERY_CODE = 10;

    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private String imagePath;
    private ImageView gift_image;
    private EditText edt_review_write;
    private EditText edt_price;

    // 성별, 관계 정보
    private String male = null; private String female = null; private String family = null;
    private String parents = null; private String grandparents = null; private String friends = null;
    private String lover = null; private String coworker = null; private String teacher = null;

    // 나이대 정보
    private String teenager = null; private String twenty = null; private String thirty = null; private String forty = null;
    private String fifty = null; private String sixty = null; private String seventy = null; private String eighty = null;
    private String early = null; private String mid = null; private String late = null;

    //시크바
    private String satisfaction = null;
    private String onlineURL = null;

    CheckBox btn_male; CheckBox btn_female;

    CheckBox btn_family; CheckBox btn_parents; CheckBox btn_grandparents;
    CheckBox btn_friends; CheckBox btn_lover; CheckBox btn_coworker; CheckBox btn_teacher;

    CheckBox btn_teenager;CheckBox btn_twenty;CheckBox btn_thirty;CheckBox btn_forty;
    CheckBox btn_fifty;CheckBox btn_sixty;CheckBox btn_seventy;CheckBox btn_eighty;

    CheckBox btn_early;CheckBox btn_mid;CheckBox btn_late;

    EditText edt_url;
    Button btn_camera;
    TextView seekBarText;

    public ReviewReceiverInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = this.getActivity();
        v = inflater.inflate(R.layout.fragment_review_receiver_info, container, false);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        setupToolbar();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button select_offline = (Button) v.findViewById(R.id.select_offline);
        select_offline.setOnClickListener(this);

        //시크바
        seekBarText = (TextView) v.findViewById(R.id.seekBarText);
        SeekBar seekBar = (SeekBar) v.findViewById(R.id.seekBar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarText.setText(Integer.toString(progress) + "%");
                satisfaction = Integer.toString(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        Button btn_prev = (Button) v.findViewById(R.id.btn_prev);
        Button btn_complete = (Button) v.findViewById(R.id.btn_complete);
        btn_camera = (Button) v.findViewById(R.id.btn_camera);

        btn_male = (CheckBox) v.findViewById(R.id.btn_male);
        btn_female = (CheckBox) v.findViewById(R.id.btn_female);

        btn_family = (CheckBox) v.findViewById(R.id.btn_family);
        btn_parents = (CheckBox) v.findViewById(R.id.btn_parents);
        btn_grandparents = (CheckBox) v.findViewById(R.id.btn_grandparents);
        btn_friends = (CheckBox) v.findViewById(R.id.btn_friends);
        btn_lover = (CheckBox) v.findViewById(R.id.btn_lover);
        btn_coworker = (CheckBox) v.findViewById(R.id.btn_coworker);
        btn_teacher = (CheckBox) v.findViewById(R.id.btn_teacher);

        btn_teenager = (CheckBox) v.findViewById(R.id.btn_teenager);
        btn_twenty = (CheckBox) v.findViewById(R.id.btn_twenty);
        btn_thirty = (CheckBox) v.findViewById(R.id.btn_thirty);
        btn_forty = (CheckBox) v.findViewById(R.id.btn_forty);
        btn_fifty = (CheckBox) v.findViewById(R.id.btn_fifty);
        btn_sixty = (CheckBox) v.findViewById(R.id.btn_sixty);
        btn_seventy = (CheckBox) v.findViewById(R.id.btn_seventy);
        btn_eighty = (CheckBox) v.findViewById(R.id.btn_eighty);

        btn_early = (CheckBox) v.findViewById(R.id.btn_early);
        btn_mid = (CheckBox) v.findViewById(R.id.btn_mid);
        btn_late = (CheckBox) v.findViewById(R.id.btn_late);

        edt_url = (EditText) v.findViewById(R.id.edt_url);

        gift_image = (ImageView) v.findViewById(R.id.gift_image);
        edt_review_write = (EditText) v.findViewById(R.id.edt_review_write);
        edt_price = (EditText) v.findViewById(R.id.edt_price);

        btn_prev.setOnClickListener(this);
        btn_complete.setOnClickListener(this);
        btn_camera.setOnClickListener(this);

        btn_male.setOnClickListener(this);
        btn_female.setOnClickListener(this);
        btn_family.setOnClickListener(this);
        btn_parents.setOnClickListener(this);
        btn_grandparents.setOnClickListener(this);
        btn_friends.setOnClickListener(this);
        btn_lover.setOnClickListener(this);
        btn_coworker.setOnClickListener(this);
        btn_teacher.setOnClickListener(this);

        btn_teenager.setOnClickListener(this);
        btn_twenty.setOnClickListener(this);
        btn_thirty.setOnClickListener(this);
        btn_forty.setOnClickListener(this);
        btn_fifty.setOnClickListener(this);
        btn_sixty.setOnClickListener(this);
        btn_seventy.setOnClickListener(this);
        btn_eighty.setOnClickListener(this);

        btn_early.setOnClickListener(this);
        btn_mid.setOnClickListener(this);
        btn_late.setOnClickListener(this);
        edt_url.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.btn_online:
                edt_url.setVisibility(View.VISIBLE);
                onlineURL = edt_url.getText().toString();
                break;
            case R.id.select_offline:
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.content_main, new MapsFragment());
                fr.commit();
                //GoLib.getInstance().goFragmentBack(((AppCompatActivity) getActivity()).getSupportFragmentManager(), R.id.content_main, new MapsFragment());
                break;
            case R.id.btn_prev:
                //GoLib.getInstance().goBackFragment(((AppCompatActivity)getActivity()).getSupportFragmentManager());
                intent = new Intent(((AppCompatActivity) getActivity()).getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_complete:
                upload(imagePath);
                intent = new Intent(((AppCompatActivity) getActivity()).getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_camera:
                btn_camera.setVisibility(View.GONE);
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, GALLERY_CODE);

                break;
            case R.id.btn_male:
                if (btn_male.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_male.setButtonDrawable(R.drawable.gift_check);
                    male = "남자";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_male.setButtonDrawable(R.drawable.uncheck);
                    male = null;
                }
                break;
            case R.id.btn_female:
                if (btn_female.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_female.setButtonDrawable(R.drawable.gift_check);
                    female = "여자";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_female.setButtonDrawable(R.drawable.uncheck);
                    female = null;
                }
                break;
            case R.id.btn_family:
                if (btn_family.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_family.setButtonDrawable(R.drawable.gift_check);
                    family = "가족";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_family.setButtonDrawable(R.drawable.uncheck);
                    family = null;
                }
                break;
            case R.id.btn_parents:
                if (btn_parents.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_parents.setButtonDrawable(R.drawable.gift_check);
                    parents = "부모님";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_parents.setButtonDrawable(R.drawable.uncheck);
                    parents = null;
                }
                break;
            case R.id.btn_grandparents:
                if (btn_grandparents.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_grandparents.setButtonDrawable(R.drawable.gift_check);
                    grandparents = "조부모님";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_grandparents.setButtonDrawable(R.drawable.uncheck);
                    grandparents = null;
                }
                break;
            case R.id.btn_friends:
                if (btn_friends.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_friends.setButtonDrawable(R.drawable.gift_check);
                    friends = "친구";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_friends.setButtonDrawable(R.drawable.uncheck);
                    friends = null;
                }
                break;
            case R.id.btn_lover:
                if (btn_lover.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_lover.setButtonDrawable(R.drawable.gift_check);
                    lover = "연인";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_lover.setButtonDrawable(R.drawable.uncheck);
                    lover = null;
                }
                break;
            case R.id.btn_coworker:
                if (btn_coworker.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_coworker.setButtonDrawable(R.drawable.gift_check);
                    coworker = "직장동료";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_coworker.setButtonDrawable(R.drawable.uncheck);
                    coworker = null;
                }
                break;
            case R.id.btn_teacher:
                if (btn_teacher.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_teacher.setButtonDrawable(R.drawable.gift_check);
                    teacher = "선생님";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_teacher.setButtonDrawable(R.drawable.uncheck);
                    teacher = null;
                }
                break;
            case R.id.btn_teenager:
                if (btn_teenager.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_teenager.setButtonDrawable(R.drawable.gift_check);
                    teenager = "10대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_teenager.setButtonDrawable(R.drawable.uncheck);
                    teenager = null;
                }
                break;
            case R.id.btn_twenty:
                if (btn_twenty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_twenty.setButtonDrawable(R.drawable.gift_check);
                    twenty = "20대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_twenty.setButtonDrawable(R.drawable.uncheck);
                    twenty = null;
                }
                break;
            case R.id.btn_thirty:
                if (btn_thirty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_thirty.setButtonDrawable(R.drawable.gift_check);
                    thirty = "30대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_thirty.setButtonDrawable(R.drawable.uncheck);
                    thirty = null;
                }
                break;
            case R.id.btn_forty:
                if (btn_forty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_forty.setButtonDrawable(R.drawable.gift_check);
                    forty = "40대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_forty.setButtonDrawable(R.drawable.uncheck);
                    forty = null;
                }
                break;
            case R.id.btn_fifty:
                if (btn_fifty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_fifty.setButtonDrawable(R.drawable.gift_check);
                    fifty = "50대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_fifty.setButtonDrawable(R.drawable.uncheck);
                    fifty = null;
                }
                break;
            case R.id.btn_sixty:
                if (btn_sixty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_sixty.setButtonDrawable(R.drawable.gift_check);
                    sixty = "60대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_sixty.setButtonDrawable(R.drawable.uncheck);
                    sixty = null;

                }
                break;
            case R.id.btn_seventy:
                if (btn_seventy.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_seventy.setButtonDrawable(R.drawable.gift_check);
                    seventy = "70대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_seventy.setButtonDrawable(R.drawable.uncheck);
                    seventy = null;
                }
                break;
            case R.id.btn_eighty:
                if (btn_eighty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_eighty.setButtonDrawable(R.drawable.gift_check);
                    eighty = "80대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_eighty.setButtonDrawable(R.drawable.uncheck);
                    eighty = null;
                }
                break;
            case R.id.btn_early:
                if (btn_early.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_early.setButtonDrawable(R.drawable.gift_check);
                    early = "초반";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_early.setButtonDrawable(R.drawable.uncheck);
                    early = null;
                }
                break;
            case R.id.btn_mid:
                if (btn_mid.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_mid.setButtonDrawable(R.drawable.gift_check);
                    mid = "중반";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_mid.setButtonDrawable(R.drawable.uncheck);
                    mid = null;
                }
                break;
            case R.id.btn_late:
                if (btn_late.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_late.setButtonDrawable(R.drawable.gift_check);
                    late = "후반";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_late.setButtonDrawable(R.drawable.uncheck);
                    late = null;
                }
                break;

        }
    }


    private void setupToolbar(){
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");

        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        TextView title = (TextView) v.findViewById(R.id.toolbar_title);
        title.setText("후기등록");
        TextView exit = (TextView) v.findViewById(R.id.tv_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(((AppCompatActivity)getActivity()).getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //device에 접근하여 사진 불러오기
    public String getPath(Uri uri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context, uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);
    }

    //사진 파일 업로드
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_CODE) {

            imagePath = getPath(data.getData());
            File f = new File(imagePath);
            gift_image.setImageURI(Uri.fromFile(f));
        }
    }

    private void upload(String uri) {
        StorageReference storageRef = storage.getReferenceFromUrl("gs://mytype-3bcda.appspot.com");

        Uri file = Uri.fromFile(new File(uri));
        StorageReference riversRef = storageRef.child("images/" + file.getLastPathSegment());
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
                @SuppressWarnings("VisibleForTests")
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                ImageDTO imageDTO = new ImageDTO();
                imageDTO.imageUrl = downloadUrl.toString();
                imageDTO.edt_review_write = edt_review_write.getText().toString();
                imageDTO.edt_price = edt_price.getText().toString();
                imageDTO.uid = auth.getCurrentUser().getUid();
                imageDTO.userId = auth.getCurrentUser().getEmail();

                imageDTO.btn_male = male;
                imageDTO.btn_female = female;
                imageDTO.btn_family = family;
                imageDTO.btn_parents = parents;
                imageDTO.btn_grandparents = grandparents;
                imageDTO.btn_friends = friends;
                imageDTO.btn_lover = lover;
                imageDTO.btn_coworker = coworker;
                imageDTO.btn_teacher = teacher;

                imageDTO.btn_teenager = teenager;
                imageDTO.btn_twenty = twenty;
                imageDTO.btn_thirty = thirty;
                imageDTO.btn_thirty = forty;
                imageDTO.btn_fifty = fifty;
                imageDTO.btn_sixty = sixty;
                imageDTO.btn_seventy = seventy;
                imageDTO.btn_eighty = eighty;

                imageDTO.btn_early = early;
                imageDTO.btn_mid = mid;
                imageDTO.btn_late = late;
                imageDTO.onlineURL = onlineURL;

                //시크바
                imageDTO.seekBar = satisfaction;

                database.getReference().child("images").push().setValue(imageDTO);

            }
        });
    }
}

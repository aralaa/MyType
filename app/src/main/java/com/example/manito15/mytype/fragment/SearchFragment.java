package com.example.manito15.mytype.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.manito15.mytype.MainActivity;
import com.example.manito15.mytype.R;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{

    private final String TAG = "HomeFragment";
    Context context;
    View v;


    RecyclerView recyclerView;



    TextView seekBarText_satis;
    TextView seekBarText_price;

    String satisfaction;
    String price;

    CheckBox btn_male;
    CheckBox btn_female;

    CheckBox btn_family;
    CheckBox btn_parents;
    CheckBox btn_grandparents;
    CheckBox btn_friends;
    CheckBox btn_lover;
    CheckBox btn_coworker;
    CheckBox btn_teacher;
    CheckBox btn_teenager;
    CheckBox btn_twenty;
    CheckBox btn_thirty;
    CheckBox btn_forty;
    CheckBox btn_fifty;
    CheckBox btn_sixty;
    CheckBox btn_seventy;
    CheckBox btn_eighty;

    CheckBox btn_early;
    CheckBox btn_mid;
    CheckBox btn_late;

    private String male=null;
    private String female=null;
    private String family=null;
    private String parents=null;
    private String grandparents=null;
    private String friends=null;
    private String lover=null;
    private String coworker=null;
    private String teacher=null;

    private String teenager=null;
    private String twenty=null;
    private String thirty=null;
    private String forty=null;
    private String fifty=null;
    private String sixty=null;
    private String seventy=null;
    private String eighty=null;

    private String early=null;
    private String mid=null;
    private String late=null;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        context = this.getActivity();
        v =  inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview); //리싸이클러뷰 가져옴

        setupToolbar(); //툴바 셋팅

        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button_filter = (Button) v.findViewById(R.id.button_filter); //필터로 검색 버튼

        button_filter.setOnClickListener(this);

        btn_male=(CheckBox) v.findViewById(R.id.btn_male);
        btn_female=(CheckBox) v.findViewById(R.id.btn_female);

        btn_family=(CheckBox) v.findViewById(R.id.btn_family);
        btn_parents=(CheckBox) v.findViewById(R.id.btn_parents);
        btn_grandparents=(CheckBox) v.findViewById(R.id.btn_grandparents);
        btn_friends=(CheckBox) v.findViewById(R.id.btn_friends);
        btn_lover=(CheckBox) v.findViewById(R.id.btn_lover);
        btn_coworker=(CheckBox) v.findViewById(R.id.btn_coworker);
        btn_teacher=(CheckBox) v.findViewById(R.id.btn_teacher);

        btn_teenager=(CheckBox) v.findViewById(R.id.btn_teenager);
        btn_twenty=(CheckBox) v.findViewById(R.id.btn_twenty);
        btn_thirty=(CheckBox) v.findViewById(R.id.btn_thirty);
        btn_forty=(CheckBox) v.findViewById(R.id.btn_forty);
        btn_fifty=(CheckBox) v.findViewById(R.id.btn_fifty);
        btn_sixty=(CheckBox) v.findViewById(R.id.btn_sixty);
        btn_seventy=(CheckBox) v.findViewById(R.id.btn_seventy);
        btn_eighty=(CheckBox) v.findViewById(R.id.btn_eighty);

        btn_early=(CheckBox) v.findViewById(R.id.btn_early);
        btn_mid=(CheckBox) v.findViewById(R.id.btn_mid);
        btn_late=(CheckBox) v.findViewById(R.id.btn_late);


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

        seekBarText_satis = (TextView) v.findViewById(R.id.seekBarText_satis);
        seekBarText_price = (TextView) v.findViewById(R.id.seekBarText_price);

        SeekBar seekBar_satis = (SeekBar) v.findViewById(R.id.seekBar_satis);
        SeekBar seekBar_price = (SeekBar) v.findViewById(R.id.seekBar_price);

        seekBar_satis.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarText_satis.setText(Integer.toString(progress)+"%");
                satisfaction = Integer.toString(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarText_price.setText(Integer.toString(progress)+"원");
                price = Integer.toString(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_filter: //필터로 검색 버튼
                break;
            case R.id.btn_male:
                if (btn_male.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_male.setButtonDrawable(R.drawable.male);
                    male="남자";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_male.setButtonDrawable(R.drawable.uncheck);
                    male=null;
                }
                break;
            case R.id.btn_female:
                if (btn_female.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_female.setButtonDrawable(R.drawable.female);
                    female="여자";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_female.setButtonDrawable(R.drawable.uncheck);
                    female=null;
                }
                break;
            case R.id.btn_family:
                if (btn_family.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_family.setButtonDrawable(R.drawable.gift_check);
                    family="가족";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_family.setButtonDrawable(R.drawable.uncheck);
                    family=null;
                }
                break;
            case R.id.btn_parents:
                if (btn_parents.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_parents.setButtonDrawable(R.drawable.gift_check);
                    parents="부모님";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_parents.setButtonDrawable(R.drawable.uncheck);
                    parents=null;
                }
                break;
            case R.id.btn_grandparents:
                if (btn_grandparents.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_grandparents.setButtonDrawable(R.drawable.gift_check);
                    grandparents="조부모님";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_grandparents.setButtonDrawable(R.drawable.uncheck);
                    grandparents=null;
                }
                break;
            case R.id.btn_friends:
                if (btn_friends.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_friends.setButtonDrawable(R.drawable.gift_check);
                    friends="친구";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_friends.setButtonDrawable(R.drawable.uncheck);
                    friends=null;
                }
                break;
            case R.id.btn_lover:
                if (btn_lover.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_lover.setButtonDrawable(R.drawable.gift_check);
                    lover="연인";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_lover.setButtonDrawable(R.drawable.uncheck);
                    lover=null;
                }
                break;
            case R.id.btn_coworker:
                if (btn_coworker.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_coworker.setButtonDrawable(R.drawable.gift_check);
                    coworker="직장동료";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_coworker.setButtonDrawable(R.drawable.uncheck);
                    coworker=null;
                }
                break;
            case R.id.btn_teacher:
                if (btn_teacher.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_teacher.setButtonDrawable(R.drawable.gift_check);
                    teacher="선생님";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_teacher.setButtonDrawable(R.drawable.uncheck);
                    teacher=null;
                }
                break;
            case R.id.btn_teenager:
                if (btn_teenager.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_teenager.setButtonDrawable(R.drawable.gift_check);
                    teenager="10대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_teenager.setButtonDrawable(R.drawable.uncheck);
                    teenager=null;
                }
                break;
            case R.id.btn_twenty:
                if (btn_twenty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_twenty.setButtonDrawable(R.drawable.gift_check);
                    twenty="20대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_twenty.setButtonDrawable(R.drawable.uncheck);
                    twenty=null;
                }
                break;
            case R.id.btn_thirty:
                if (btn_thirty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_thirty.setButtonDrawable(R.drawable.gift_check);
                    thirty="30대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_thirty.setButtonDrawable(R.drawable.uncheck);
                    thirty=null;
                }
                break;
            case R.id.btn_forty:
                if (btn_forty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_forty.setButtonDrawable(R.drawable.gift_check);
                    forty="40대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_forty.setButtonDrawable(R.drawable.uncheck);
                    forty=null;
                }
                break;
            case R.id.btn_fifty:
                if (btn_fifty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_fifty.setButtonDrawable(R.drawable.gift_check);
                    fifty="50대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_fifty.setButtonDrawable(R.drawable.uncheck);
                    fifty=null;
                }
                break;
            case R.id.btn_sixty:
                if (btn_sixty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_sixty.setButtonDrawable(R.drawable.gift_check);
                    sixty="60대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_sixty.setButtonDrawable(R.drawable.uncheck);
                    sixty=null;

                }
                break;
            case R.id.btn_seventy:
                if (btn_seventy.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_seventy.setButtonDrawable(R.drawable.gift_check);
                    seventy="70대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_seventy.setButtonDrawable(R.drawable.uncheck);
                    seventy=null;
                }
                break;
            case R.id.btn_eighty:
                if (btn_eighty.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_eighty.setButtonDrawable(R.drawable.gift_check);
                    eighty="80대";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_eighty.setButtonDrawable(R.drawable.uncheck);
                    eighty=null;
                }
                break;
            case R.id.btn_early:
                if (btn_early.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_early.setButtonDrawable(R.drawable.gift_check);
                    early="초반";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_early.setButtonDrawable(R.drawable.uncheck);
                    early=null;
                }
                break;
            case R.id.btn_mid:
                if (btn_mid.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_mid.setButtonDrawable(R.drawable.gift_check);
                    mid="중반";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_mid.setButtonDrawable(R.drawable.uncheck);
                    mid=null;
                }
                break;
            case R.id.btn_late:
                if (btn_late.isChecked()) {
                    // TODO : CheckBox is checked.
                    btn_late.setButtonDrawable(R.drawable.gift_check);
                    late="후반";
                } else {
                    // TODO : CheckBox is unchecked.
                    btn_late.setButtonDrawable(R.drawable.uncheck);
                    late=null;
                }
                break;

        }

    }

    /**
     * Toolbar Setup
     */
    private void setupToolbar(){
        Log.d(TAG, "setupToolbar: Toolbar 셋팅");

        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        TextView title = (TextView) v.findViewById(R.id.toolbar_title);
        title.setText("후기검색");
    }


}

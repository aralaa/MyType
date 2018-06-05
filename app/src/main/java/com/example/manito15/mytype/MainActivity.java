package com.example.manito15.mytype;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.manito15.mytype.fragment.HomeFragment;
import com.example.manito15.mytype.fragment.MyTypeFragment;
import com.example.manito15.mytype.fragment.ReviewRegisterFragment;
import com.example.manito15.mytype.fragment.SearchFragment;
import com.example.manito15.mytype.helper.BottomNavigationViewHelper;

/**
 * Bottom Navigation View를 가지며 다양한 프래그먼트를 보여주는 컨테이너 역할을 한다.
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager = getFragmentManager();

    /**
     * 네비게이션 메뉴를 클릭했을 때 호출되는 메소드
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                manager.beginTransaction().replace(R.id.contentLayout, homeFragment, homeFragment.getTag()).commit();
                setToolbar(R.string.app_name);
                return true;
            case R.id.nav_search:
                SearchFragment searchFragment = new SearchFragment();
                manager.beginTransaction().replace(R.id.contentLayout, searchFragment, searchFragment.getTag()).commit();

                return true;
            case R.id.nav_review:
                ReviewRegisterFragment reviewRegisterFragment = new ReviewRegisterFragment();
                manager.beginTransaction().replace(R.id.contentLayout, reviewRegisterFragment, reviewRegisterFragment.getTag()).commit();
                setToolbar(R.string.title_review);
                return true;
            case R.id.nav_mytype:
                MyTypeFragment myTypeFragment = new MyTypeFragment();
                manager.beginTransaction().replace(R.id.contentLayout, myTypeFragment, myTypeFragment.getTag()).commit();
                setToolbar(R.string.title_mytype);
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        // 아라 : BottomNavigation 애니메이션 없애는 코드
        BottomNavigationViewHelper.disableShiftMode(navigation);

        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction().replace(R.id.contentLayout, homeFragment, homeFragment.getTag()).commit();

    }

    private void setToolbar(int title){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }



}

package com.example.manito15.mytype.App;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.manito15.mytype.Home.HomeFragment;
import com.example.manito15.mytype.MyPage.MyTypeFragment;
import com.example.manito15.mytype.R;
import com.example.manito15.mytype.Review.ReviewRegisterActivity;
import com.example.manito15.mytype.Search.SearchFragment;
import com.example.manito15.mytype.helper.BottomNavigationViewHelper;

/**
 * Bottom Navigation View를 가지며 다양한 프래그먼트를 보여주는 컨테이너 역할을 한다.
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager = getSupportFragmentManager();

    /**
     * 네비게이션 메뉴를 클릭했을 때 호출되는 메소드
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
                return true;
            case R.id.nav_search:
                SearchFragment searchFragment = new SearchFragment();
                manager.beginTransaction().replace(R.id.content_main, searchFragment, searchFragment.getTag()).commit();
                return true;
            case R.id.nav_review:
                Intent intent = new Intent(getApplicationContext(), ReviewRegisterActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.nav_mytype:
                MyTypeFragment myTypeFragment = new MyTypeFragment();
                manager.beginTransaction().replace(R.id.content_main, myTypeFragment, myTypeFragment.getTag()).commit();                //로그아웃하려면 위에거 지우고 주석 풀어주면됨
                //LogoutFragment logoutFragment=new LogoutFragment();
                //manager.beginTransaction().replace(R.id.content_main, logoutFragment, logoutFragment.getTag()).commit();//로그아웃하려면 저거 두개logoutFragment로 변경
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
        manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
    }
}


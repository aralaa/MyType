package com.example.manito15.mytype.lib;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.manito15.mytype.Review.ReviewRegisterActivity;

/**
 * 액티비티나 프래그먼트 실행 라이브러리
 */
public class GoLib {
    public final String TAG = GoLib.class.getSimpleName();
    private volatile static GoLib instance;

    public static GoLib getInstance(){
        if(instance == null){
            synchronized (GoLib.class){
                if (instance == null){
                    instance = new GoLib();
                }
            }
        }

        return instance;
    }

    /**
     * 프래그먼트를 보여준다.
     * @param fragmentManager 프래그먼트 매니저
     * @param containerViewID 프래그먼트를 보여줄 컨테이너 뷰 아이디
     * @param fragment 프래그먼트
     */
    public void goFragment(FragmentManager fragmentManager, int containerViewID, Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(containerViewID, fragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * 뒤로가기를 할 수 있는 프래그먼트를 보여준다.
     * @param fragmentManager 프래그먼트 매니저
     * @param containerViewId 프래그먼트를 보여줄 컨테이너 뷰 아이디
     * @param fragment 프래그먼트
     */
    public void goFragmentBack(FragmentManager fragmentManager, int containerViewId, Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * 이전 프래그먼트를 보여준다.
     * @param fragmentManager
     */
    public void goBackFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }

    /**
     * 후기 등록 액티비티 실행
     * @param context
     */
    public void goReviewRegisterActivity(Context context){
        Intent intent = new Intent(context, ReviewRegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}

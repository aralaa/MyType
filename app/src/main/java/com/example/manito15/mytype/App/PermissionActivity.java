package com.example.manito15.mytype.App;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.manito15.mytype.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  앱을 실행할 때 필요한 권한을 처리하기 위한 액티비티
 */

public class PermissionActivity extends AppCompatActivity {

    private static final int PERMISSION_MULTI_CODE = 100;

    /**
     * 화면을 구성하고 SDK 버전과 권한에 따른 처리를 한다.
     * @param savedInstanceState 액티비티가 새로 생성되었을 경우, 이전 상태 값을 가지는 객체
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        //파이어베이스 READ_EXTERNAL_STORAGE
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        //}
        // 사용자의 안드로이드 버전 파악하여 권한 승인 요청 할지를 결정한다.
        if(Build.VERSION.SDK_INT < 23){
            goIndexActivity();
        }else{
            if(checkAndRequestPermissions()){
                goIndexActivity();
            }
        }
    }
    /**
     *  권한을 확인하고 권한이 부여되어 있지 않다면 권한을 요청한다.
     *  @return 필요한 권한이 모두 부여되어있다면 true, 그렇지 않다면 false
     */
    private boolean checkAndRequestPermissions(){
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        List<String> listPermissionNeeded = new ArrayList<>();



        for(String permission:permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                listPermissionNeeded.add(permission);
            }
        }

        // checkSelfPermission() 메소드로 필요한 권한을 검사하고 권한 승인이 되어 있지 않다면
        // ArrayList 인 listPermissionNeeded 에 추가한다.
        // 추가된 권한이 한개라도 있다면 requestPermissions() 메소드로 해당 권한을 사용자가 승인할 수 있도록 요청.
        if(!listPermissionNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),
                                                            PERMISSION_MULTI_CODE);
            return false;
        }

        return true;
    }

    /**
     * 권한 요청 결과를 받는 메소드
     * @param requestCode 요청 코드
     * @param permissions 권한 종류
     * @param grantResults 권한 결과
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length == 0) return;

        switch (requestCode){
            case PERMISSION_MULTI_CODE:
                checkPermissionResult(permissions, grantResults);
                break;
        }
    }

    /**
     * 권한 처리 결과를 보고 인덱스 액티비티를 실행할지, 권한 설정 요청 다이얼로그를 보여줄지 결정한다.
     * 모든 권한이 승인되었을 경우에는 goIndexActivity() 메소드를 호출한다.
     * @param permissions 권한 종류
     * @param grantResults 권한 부여 결과
     */
    private void checkPermissionResult(String[] permissions, int[] grantResults){
        boolean isAllGranted = true;

        for(int i = 0; i<permissions.length; i++){
            if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                isAllGranted = false;
            }
        }
        //권한이 부여되었다면
        if(isAllGranted){
            goIndexActivity();
        //권한이 부여되어 있지 않다면
        }else{
            showPermissionDialog();
        }
    }

    /**
     * 인덱스 액티비티를 실행하고 현재 액티비티를 종료한다.
     */
    private void goIndexActivity(){
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);

        finish();
    }

    /**
     * 권한 설정 화면으로 이동할지를 선택하는 다이얼로그를 보여준다.
     */
    private void showPermissionDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.permission_setting_title);
        dialog.setMessage(R.string.permission_setting_message);
        dialog.setPositiveButton(R.string.setting, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(PermissionActivity.this, R.string.permission_setting_restart, Toast.LENGTH_LONG).show();

                PermissionActivity.this.finish();
                goAppSettingActivity();
            }
        });
    }

    /**
     * 권한을 설정할 수 있는 설정 액티비티를 실행한다.
     */
    private void goAppSettingActivity(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}

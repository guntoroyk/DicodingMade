package com.guntoroyk.smslistenerapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCheckPermission;
    Button btnDownload;
    public static final String ACTION_DOWNLOAD_STATUS = "download_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckPermission = findViewById(R.id.btn_permission);
        btnCheckPermission.setOnClickListener(this);

        btnDownload = findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_permission) {
            PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE);
        } else if (v.getId() == R.id.btn_download) {

        }
    }

    final int SMS_REQUEST_CODE = 101;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        if (requestCode == SMS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Sms receiver permission diterima", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(this, "Sms receiver permission diterima", Toast.LENGTH_SHORT);
            }
        }
    }
}

package com.geektech.maps;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;

public class MainActivity extends BaseMapActivity {


    @Override
    public int getViewId() {
        return R.layout.activity_main;
    }

    private Button btnPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();
        setUpListeners();
    }

    private void setUpListeners() {
        btnPush.setOnClickListener(v -> {
            if (PermissionUtils.isPermissionLocationGranted(this))
                startServiceLocation();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startServiceLocation();
            }
        }
    }

    private void startServiceLocation() {
        startService(new Intent(this, ServiceBuilder.class));

    }

    private void setUpViews() {
        btnPush = findViewById(R.id.btnPush);
    }

}

package com.example.appmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appthibanglaixe.R;

public class NenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nen);

        // Tạo một Handler để đợi 10 giây trước khi chuyển sang màn hình đăng nhập
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Chuyển sang màn hình đăng nhập
                Intent intent = new Intent(NenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 10000); // 10000ms = 10s
    }
}
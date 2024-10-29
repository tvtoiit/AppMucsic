package com.example.appmusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.checkerframework.checker.nullness.qual.NonNull;

public class HomeActivity extends AppCompatActivity {
    private TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_home:
//                        // Xử lý khi chọn mục "Home"
//                        return true;
//                    case R.id.action_favorite:
//                        // Xử lý khi chọn mục "Search"
//                        return true;
//                    case R.id.action_playlist:
//                        // Xử lý khi chọn mục "Playlist"
//                        return true;
//                    case R.id.action_download:
//                        // Xử lý khi chọn mục "Download"
//                        return true;
//                    case R.id.action_account:
//                        // Xử lý khi chọn mục "Account"
//                        return true;
//                    default:
//                        return false;
//                }
//                return false;
//            }
//        });
        //Ánh xạ id
        tvHello = findViewById(R.id.tvHello);

        //Nhận tên từ Intent
        Intent i = getIntent();
        String name = i.getStringExtra("user_Name");
        tvHello.setText("Xin chào " + name);
    }
}
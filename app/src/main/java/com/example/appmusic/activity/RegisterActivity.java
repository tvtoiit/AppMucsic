package com.example.appmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmusic.entity.modify;
import com.example.appthibanglaixe.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ các thành phần giao diện
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Tạo một instance của class modify
        modify modifyInstance = new modify(this);

        // Xử lý sự kiện click cho nút Sign up
        btnSignUp.setOnClickListener(v -> {
            // Lấy dữ liệu từ các trường EditText
            String fullname = edtName.getText().toString().trim();
            String username = edtEmail.getText().toString().trim(); // Giả sử email là username
            String password = edtPassword.getText().toString().trim();
            String imageuser = "default_image_path"; // Hoặc path thực tế đến ảnh người dùng

            // Gọi hàm registerUser để lưu thông tin người dùng vào cơ sở dữ liệu
            modifyInstance.registerUser(fullname, username, password, imageuser);

            // Thông báo đăng ký thành công (có thể thay thế bằng Intent để chuyển sang màn hình khác)
            Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển sang màn hình đăng nhập
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
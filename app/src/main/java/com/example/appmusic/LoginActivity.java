package com.example.appmusic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private Button btnSignIn, btnSignUp;
    private FirebaseAuth mAuth;
    private CheckBox checkBox;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        // ánh xạ id
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        checkBox = findViewById(R.id.checkBox);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // kiểm tra và điền email, mật khẩu đã lưu
        SharedPreferences prefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        boolean rememberMe = prefs.getBoolean("REMEMBER_ME", false);
        if (rememberMe) {
            edtEmail.setText(prefs.getString("EMAIL", ""));
            edtPassword.setText(prefs.getString("PASSWORD", ""));
            checkBox.setChecked(true);
        }

        // Xử lý sự kiện click
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                logup();
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Vui long nhap email cua ban",Toast.LENGTH_LONG).show();
                }else {
                    resetPassword(email);
                }
            }
        });
    }

    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Vui long kiem tra email cua ban",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Khong the gui email",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void logup() {
        Intent i =new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }

    private void login() {
        String Email, Password;
        Email = edtEmail.getText().toString();
        Password = edtPassword.getText().toString();
        if (Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this,"Vui long nhap email hoac password!",Toast.LENGTH_LONG).show();
            return;
        }

    mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
               // Nếu checkbox đc chọn thì lưu thông tin
                if (checkBox.isChecked()) {
                    //Lưu email và password
                    getSharedPreferences("Điền thông tin đăng nhập", MODE_PRIVATE)
                            .edit()
                            .putString("EMAIL", edtEmail.getText().toString())
                            .putString("PASSWORD", edtPassword.getText().toString())
                            .putBoolean("REMEMBER_ME", true)
                            .apply();
            }else {
                    // Nếu checkbox không được chọn thì xóa thông tin
                    getSharedPreferences("Điền thông tin đăng nhập", MODE_PRIVATE)
                            .edit()
                            .clear()
                            .apply();
                }
            Toast.makeText(getApplicationContext(),"Đăng nhập thành công!",Toast.LENGTH_LONG).show();
                Intent i =new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Đăng nhập thất bại!",Toast.LENGTH_LONG).show();
            }
        }
    });
    }
}
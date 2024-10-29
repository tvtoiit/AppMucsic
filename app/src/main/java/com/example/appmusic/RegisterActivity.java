package com.example.appmusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPassword;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ImageButton btnSignUp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ id
        mAuth = FirebaseAuth.getInstance();
        edtName = findViewById(R.id.edtName);
        mDatabase = FirebaseDatabase.getInstance().getReference("Users"); // Tham chiếu Firebase Realtime Database
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp1 = findViewById(R.id.btnSignUp1);

        // Xử lý sự kiện click
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logup();
            }
        });

    }

    private void logup() {
        String Email, Password, Name;
        Name = edtName.getText().toString();
        Email = edtEmail.getText().toString();
        Password = edtPassword.getText().toString();
        if ( Name.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        if (Password.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_LONG).show();
            return;
        }

        // đăng ký tài khoản với email và mật khẩu
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // đăng ký thành công và lưu thêm tên vào firebase database
                    String userID = mAuth.getCurrentUser().getUid();
                    saveUserToDatabase(userID, Name, Email);
                    Toast.makeText(getApplicationContext(),"Đăng ký thành công!",Toast.LENGTH_LONG).show();
                    Intent i =new Intent(RegisterActivity.this,HomeActivity.class);
                    i.putExtra("user_Name",Name);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Đăng ký thất bại!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void saveUserToDatabase(String userID, String Name, String Email) {
        //Tạo dữ liệu người dùng dưới dạng HashMap
        Map<String , Object> user = new HashMap<>();
        user.put("name",Name);
        user.put("email",Email);

        //Lưu dữ liệu người dùng vào Firebase Database
        mDatabase.child(userID).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Dữ liệu đã được lưu!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"Lỗi khi lưu dữ liệu!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
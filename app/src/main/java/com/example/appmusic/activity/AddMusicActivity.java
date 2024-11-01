package com.example.appmusic.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmusic.MainActivity;
import com.example.appmusic.entity.modify;
import com.example.appthibanglaixe.R;

public class AddMusicActivity extends AppCompatActivity {

    private EditText edtSongTitle, edtArtistAlbum, edtDuration, edtCurrentTime;
    private TextView tvFilePath, tvImagePath;
    private CheckBox chkFavorite;
    private Button btnAddMusic;
    private modify modifyDb;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_FILE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmusic);

        // Ánh xạ các thành phần UI
        edtSongTitle = findViewById(R.id.edtSongTitle);
        edtArtistAlbum = findViewById(R.id.edtArtistAlbum);
        tvImagePath = findViewById(R.id.tvImagePath);
        edtDuration = findViewById(R.id.edtDuration);
        edtCurrentTime = findViewById(R.id.edtCurrentTime);
        tvFilePath = findViewById(R.id.tvFilePath);
        chkFavorite = findViewById(R.id.chkFavorite);
        btnAddMusic = findViewById(R.id.btnAddMusic);

        Button btnChooseImage = findViewById(R.id.btnChooseImage);
        Button btnSelectFile = findViewById(R.id.btnSelectFile);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        // Khởi tạo modifyDb
        modifyDb = new modify(this);

        btnAddMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMusic();
            }
        });
    }

    private void addMusic() {
        String songTitle = edtSongTitle.getText().toString().trim();
        String artistAlbum = edtArtistAlbum.getText().toString().trim();
        String imagePath = tvImagePath.getText().toString().trim();
        String durationStr = edtDuration.getText().toString().trim();
        String currentTimeStr = edtCurrentTime.getText().toString().trim();
        String filePath = tvFilePath.getText().toString().trim();
        int favorite = chkFavorite.isChecked() ? 1 : 0;

        // Kiểm tra dữ liệu nhập vào
        if (songTitle.isEmpty() || durationStr.isEmpty() || filePath.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        int duration = Integer.parseInt(durationStr);
        int currentTime = currentTimeStr.isEmpty() ? 0 : Integer.parseInt(currentTimeStr);

        // Gọi phương thức addMusic để thêm bài hát
        modifyDb.addMusic(this, songTitle, artistAlbum, imagePath, duration, currentTime, favorite, filePath);
        Toast.makeText(this, "Thêm bài hát thành công!", Toast.LENGTH_SHORT).show();

        // Chuyển sang trang chủ
        Intent intent = new Intent(AddMusicActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                if (requestCode == PICK_IMAGE_REQUEST) {
                    // Lấy tên của hình ảnh và gán vào TextView tvImagePath
                    String imageName = getFileNameFromUri(uri, MediaStore.Images.Media.DISPLAY_NAME);
                    String imageNameNew = imageName.substring(0, imageName.lastIndexOf("."));
                    tvImagePath.setText(imageNameNew);
                } else if (requestCode == PICK_FILE_REQUEST) {
                    // Lấy tên của file nhạc và gán vào TextView tvFilePath
                    String fileName = getFileNameFromUri(uri, MediaStore.Audio.Media.DISPLAY_NAME);
                    String fileNameNew = fileName.substring(0, fileName.lastIndexOf("."));
                    tvFilePath.setText(fileNameNew);
                }
            }
        }
    }

    private String getFileNameFromUri(Uri uri, String mediaColumn) {
        String fileName = "Unknown"; // Giá trị mặc định nếu không tìm thấy
        String[] projection = {mediaColumn};
        try (Cursor cursor = getContentResolver().query(uri, projection, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(mediaColumn);
                if (nameIndex != -1) {
                    fileName = cursor.getString(nameIndex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}

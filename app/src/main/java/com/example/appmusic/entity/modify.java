package com.example.appmusic.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appmusic.data.DbContract;
import com.example.appmusic.data.sqDuLieu;
import com.example.appmusic.model.Music;

import java.util.ArrayList;
import java.util.List;

public class modify {
    private Context context;

    // Constructor
    public modify(Context context) {
        this.context = context;
    }

    // Phương thức thêm người dùng mới vào cơ sở dữ liệu
    public void registerUser(String fullname, String username, String password, String imageuser) {
        SQLiteDatabase db = sqDuLieu.getInstance(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.UserEntry.COLUMN_FULLNAME, fullname);
        values.put(DbContract.UserEntry.COLUMN_USERNAME, username);
        values.put(DbContract.UserEntry.COLUMN_PASSWORD, password);
        values.put(DbContract.UserEntry.COLUMN_IMAGEUSER, imageuser);
        db.insert(DbContract.UserEntry.TABLE_NAME, null, values);
    }

    public long addMusic(Context context, String songTitle, String artistAlbum, String coverImage, int duration, int currentTime, int favorite, String filePath) {
        // Lấy database ở chế độ ghi
        SQLiteDatabase db = sqDuLieu.getInstance(context).getWritableDatabase();

        // Tạo ContentValues để chứa dữ liệu cần insert
        ContentValues values = new ContentValues();
        values.put(DbContract.MusicEntry.COLUMN_SONG_TITLE, songTitle);
        values.put(DbContract.MusicEntry.COLUMN_ARTIST_ALBUM, artistAlbum);
        values.put(DbContract.MusicEntry.COLUMN_COVER_IMAGE, coverImage);
        values.put(DbContract.MusicEntry.COLUMN_DURATION, duration);
        values.put(DbContract.MusicEntry.COLUMN_CURRENT_TIMEB, currentTime);
        values.put(DbContract.MusicEntry.COLUMN_FAVORITE, favorite);
        //values.put(DbContract.MusicEntry.COLUMN_FILE_PATH, filePath);

        // Chèn dữ liệu vào bảng và lấy ID của hàng vừa chèn
        long newRowId = db.insert(DbContract.MusicEntry.TABLE_NAME, null, values);

        // Đóng database
        db.close();

        return newRowId; // Trả về ID của hàng mới chèn, -1 nếu thất bại
    }


    // Phương thức thêm bài hát mới vào cơ sở dữ liệu
    public void addMusic1(String songTitle, String artistAlbum, String coverImage, int duration, int currentTime, int favorite, String filePath) {
        SQLiteDatabase db = sqDuLieu.getInstance(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.MusicEntry.COLUMN_SONG_TITLE, songTitle);
        values.put(DbContract.MusicEntry.COLUMN_ARTIST_ALBUM, artistAlbum);
        values.put(DbContract.MusicEntry.COLUMN_COVER_IMAGE, coverImage);
        values.put(DbContract.MusicEntry.COLUMN_DURATION, duration);
        values.put(DbContract.MusicEntry.COLUMN_CURRENT_TIMEB, currentTime);
        values.put(DbContract.MusicEntry.COLUMN_FAVORITE, favorite);
        values.put(DbContract.MusicEntry.COLUMN_FILE_PATH, filePath);

        // Thực hiện thêm dữ liệu vào bảng nhạc
        db.insert(DbContract.MusicEntry.TABLE_NAME, null, values);
    }



    public List<Music> getFavoriteSongs(Context context) {
        List<Music> favoriteSongs = new ArrayList<>();
        SQLiteDatabase db = sqDuLieu.getInstance(context).getReadableDatabase();

        // Truy vấn để lấy các bài hát yêu thích
        String query = "SELECT " + DbContract.MusicEntry.COLUMN_SONG_TITLE + ", " +
                DbContract.MusicEntry.COLUMN_ARTIST_ALBUM + " FROM " +
                DbContract.MusicEntry.TABLE_NAME + " WHERE " +
                DbContract.MusicEntry.COLUMN_FAVORITE + " = 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String songTitle = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.MusicEntry.COLUMN_SONG_TITLE));
                String artistAlbum = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.MusicEntry.COLUMN_ARTIST_ALBUM));

                // Tạo đối tượng Music và thêm vào danh sách
                favoriteSongs.add(new Music(songTitle, artistAlbum));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return favoriteSongs;
    }


    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = sqDuLieu.getInstance(context).getReadableDatabase();
        String query = "SELECT * FROM " + DbContract.UserEntry.TABLE_NAME +
                " WHERE " + DbContract.UserEntry.COLUMN_USERNAME + " = ? AND " +
                DbContract.UserEntry.COLUMN_PASSWORD + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }
}

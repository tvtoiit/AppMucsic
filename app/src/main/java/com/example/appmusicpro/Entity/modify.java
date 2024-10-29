package com.example.appthibanglaixe.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appthibanglaixe.data.DbContract;
import com.example.appthibanglaixe.data.sqDuLieu;

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

    // Phương thức xóa người dùng theo username
    public void deleteUser(String username) {
        SQLiteDatabase db = sqDuLieu.getInstance(context).getWritableDatabase();
        db.delete(DbContract.UserEntry.TABLE_NAME, DbContract.UserEntry.COLUMN_USERNAME + " = ?", new String[]{username});
    }

    // Phương thức lấy tất cả người dùng
    public Cursor findAllUsers() {
        SQLiteDatabase db = sqDuLieu.getInstance(context).getReadableDatabase();
        return db.query(DbContract.UserEntry.TABLE_NAME, null, null, null, null, null, null);
    }

    // Phương thức tìm kiếm người dùng theo username
    public Cursor findUserByUsername(String username) {
        SQLiteDatabase db = sqDuLieu.getInstance(context).getReadableDatabase();
        return db.query(DbContract.UserEntry.TABLE_NAME, null, DbContract.UserEntry.COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);
    }

    // Phương thức cập nhật thông tin người dùng
    public void updateUser(String username, String newFullname, String newPassword, String newImageUser) {
        SQLiteDatabase db = sqDuLieu.getInstance(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.UserEntry.COLUMN_FULLNAME, newFullname);
        values.put(DbContract.UserEntry.COLUMN_PASSWORD, newPassword);
        values.put(DbContract.UserEntry.COLUMN_IMAGEUSER, newImageUser);

        db.update(DbContract.UserEntry.TABLE_NAME, values, DbContract.UserEntry.COLUMN_USERNAME + " = ?", new String[]{username});
    }
}

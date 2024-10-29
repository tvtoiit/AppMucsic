package com.example.appmusic.data;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class sqDuLieu extends SQLiteOpenHelper {
    private static final String TAG = sqDuLieu.class.getSimpleName();

    private Resources mResources;
    private static final String DB_Name = "appmusic.db"; // tên database
    private static final int DB_VERSION = 1;
    SQLiteDatabase db;
    private static sqDuLieu instance = null;
    //chạy trên nhiều luồng
    // Singleton pattern để đảm bảo chỉ có một instance duy nhất
    public synchronized static sqDuLieu getInstance(Context context) {
        if (instance == null) {
            instance = new sqDuLieu(context);
        }
        return instance;
    }

    public sqDuLieu(Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng User
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + DbContract.UserEntry.TABLE_NAME + " (" +
                DbContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContract.UserEntry.COLUMN_FULLNAME + " TEXT NOT NULL, " +
                DbContract.UserEntry.COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
                DbContract.UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                DbContract.UserEntry.COLUMN_IMAGEUSER + " TEXT);";

        // Thực thi lệnh SQL để tạo bảng User
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại và tạo lại bảng mới (nếu cần nâng cấp)
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.UserEntry.TABLE_NAME);
        onCreate(db);
    }
}

package com.example.appmusic.data;

import android.provider.BaseColumns;

public class DbContract {
    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_FULLNAME = "fullname";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_IMAGEUSER = "imageuser";
    }
}

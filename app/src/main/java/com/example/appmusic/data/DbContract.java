package com.example.appmusic.data;

import android.provider.BaseColumns;

public class DbContract {
    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_FULLNAME = "fullname";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_IMAGEUSER = "imageuser";
        public static final String _ID = BaseColumns._ID;
    }

    public static final class MusicEntry implements BaseColumns {
        public static final String TABLE_NAME = "music";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SONG_TITLE = "song_title";
        public static final String COLUMN_ARTIST_ALBUM = "artist_album";
        public static final String COLUMN_COVER_IMAGE = "cover_image";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_CURRENT_TIMEB = "current_time";
        public static final String COLUMN_FAVORITE = "favorite"; // Boolean (0 for false, 1 for true)
        public static final String COLUMN_FILE_PATH = "file_path";
    }
}

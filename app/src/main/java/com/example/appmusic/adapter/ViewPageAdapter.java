package com.example.appmusic.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appmusic.activity.Tab_Dowload_Fragment;
import com.example.appmusic.activity.Tab_Favorite_Fragment;
import com.example.appmusic.activity.Tab_Home_Fragment;
import com.example.appmusic.activity.Tab_Play_Fragment;
import com.example.appmusic.activity.Tab_User_Fragment;


public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Tab_Home_Fragment();
            case 1:
                return new Tab_Favorite_Fragment();
            case 2:
                return new Tab_Play_Fragment();
            case 3:
                return new Tab_Dowload_Fragment();
            default:
                return new Tab_User_Fragment();
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}

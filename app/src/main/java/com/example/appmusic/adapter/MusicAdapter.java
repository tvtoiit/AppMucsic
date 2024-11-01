package com.example.appmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appmusic.model.Music;
import com.example.appthibanglaixe.R;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {
    private List<Music> musicList;
    public MusicAdapter(Context context, List<Music> musicList) {
        super(context, 0, musicList);
        this.musicList = musicList;
    }

    public void updateData(List<Music> newMusicList) {
        musicList.clear();
        musicList.addAll(newMusicList);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Music music = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.favorite_song_item, parent, false);
        }

        // Ánh xạ các thành phần UI trong item_music.xml
        TextView tvSongTitle = convertView.findViewById(R.id.songTitleTextView);
        TextView tvArtistAlbum = convertView.findViewById(R.id.artistNameTextView);

        // Đặt dữ liệu cho các TextView
        tvSongTitle.setText(music.getSongTitle());
        tvArtistAlbum.setText(music.getArtistAlbum());

        // Trả về View cho mỗi item trong ListView
        return convertView;
    }
}

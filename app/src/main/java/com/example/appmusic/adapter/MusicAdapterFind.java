package com.example.appmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appthibanglaixe.R;
import com.example.appmusic.model.MusicFind;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapterFind extends RecyclerView.Adapter<MusicAdapterFind.MusicViewHolder> {

    private List<MusicFind> musicList;
    private List<MusicFind> fullMusicList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public MusicAdapterFind(Context context, List<MusicFind> musicList, OnItemClickListener listener) {
        this.context = context;
        this.musicList = musicList;
        this.fullMusicList = new ArrayList<>(musicList);
        this.listener = listener;

    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemfind, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        MusicFind music = musicList.get(position);

        int musicId = music.getId();
        holder.songTitle.setText(music.getSongTitle());
        holder.artistAlbum.setText(music.getArtistAlbum());

        // Thiết lập ảnh bìa cho ImageView
        int resourceId = context.getResources().getIdentifier(music.getCoverImage(), "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.coverImage.setImageResource(resourceId);
        } else {
            // Có thể thiết lập ảnh mặc định hoặc ẩn ImageView nếu không tìm thấy ảnh
            holder.coverImage.setImageResource(R.drawable.heart);
        }
        holder.itemView.setOnClickListener(v -> listener.onItemClick(musicId));
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView songTitle, artistAlbum;
        ImageView coverImage;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.textView4);
            artistAlbum = itemView.findViewById(R.id.textView3);
            coverImage = itemView.findViewById(R.id.imageView3);
        }
    }

    public void updateMusicList(List<MusicFind> newMusicList) {
        this.musicList.clear();
        this.musicList.addAll(newMusicList);
        notifyDataSetChanged();
    }

}

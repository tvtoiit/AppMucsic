package com.example.appmusic.model;

public class MusicFind {
    private String songTitle;
    private String artistAlbum;
    private String coverImage; // Đường dẫn đến ảnh bìa

    public MusicFind(String songTitle, String artistAlbum, String coverImage) {
        this.songTitle = songTitle;
        this.artistAlbum = artistAlbum;
        this.coverImage = coverImage;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getArtistAlbum() {
        return artistAlbum;
    }

    public String getCoverImage() {
        return coverImage;
    }
}

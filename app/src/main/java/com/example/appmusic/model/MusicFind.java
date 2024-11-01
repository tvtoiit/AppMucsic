package com.example.appmusic.model;

public class MusicFind {
    private int id;
    private String songTitle;
    private String artistAlbum;
    private String coverImage;
    private String filePath;

    public MusicFind(int id ,String songTitle, String artistAlbum, String coverImage, String filePath) {
        this.id = id;
        this.songTitle = songTitle;
        this.artistAlbum = artistAlbum;
        this.coverImage = coverImage;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
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

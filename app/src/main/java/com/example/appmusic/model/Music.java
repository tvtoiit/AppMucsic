package com.example.appmusic.model;

public class Music {
    private int id; // Thêm thuộc tính id
    private String songTitle;
    private String artistAlbum;
    private String coverImage;

    public Music(int id, String songTitle, String artistAlbum, String coverImage) {
        this.id = id; // Gán id
        this.songTitle = songTitle;
        this.artistAlbum = artistAlbum;
        this.coverImage = coverImage;
    }

    // Getter và setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và setter cho coverImage
    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistAlbum() {
        return artistAlbum;
    }

    public void setArtistAlbum(String artistAlbum) {
        this.artistAlbum = artistAlbum;
    }
}

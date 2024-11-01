package com.example.appmusic.model;

public class Music {
    private String songTitle;
    private String artistAlbum;

    public Music(String songTitle, String artistAlbum) {
        this.songTitle = songTitle;
        this.artistAlbum = artistAlbum;
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

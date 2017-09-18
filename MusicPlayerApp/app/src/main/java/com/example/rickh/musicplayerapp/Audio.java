package com.example.rickh.musicplayerapp;

import java.io.Serializable;

/**
 * Created by rickh on 9/14/2017.
 */

public class Audio implements Serializable {

    private int id;
    private String downloadUrl;
    private String randomKey;
    private String songName;
    private String songArtist;
    private String uploaderUid;
    private String timeStamp;

    public Audio(int id, String downloadUrl, String randomKey, String songName, String songArtist, String uploaderUid, String timeStamp) {

        this.id = id;
        this.downloadUrl = downloadUrl;
        this.randomKey = randomKey;
        this.songName = songName;
        this.songArtist = songArtist;
        this.uploaderUid = uploaderUid;
        this.timeStamp = timeStamp;
    }

    // Setters & Getters


    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getUploaderUid() {
        return uploaderUid;
    }

    public void setUploaderUid(String uploaderUid) {
        this.uploaderUid = uploaderUid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

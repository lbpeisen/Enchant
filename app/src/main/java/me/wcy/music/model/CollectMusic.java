package me.wcy.music.model;

/**
 * Created by han78 on 2017/6/6.
 */

public class CollectMusic {
    private String localID;
    private String localMusicID;
    private String like;

    public CollectMusic(String localID, String localMusicID, String like) {
        this.localID = localID;
        this.localMusicID = localMusicID;
        this.like = like;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public String getLocalMusicID() {
        return localMusicID;
    }

    public void setLocalMusicID(String localMusicID) {
        this.localMusicID = localMusicID;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}

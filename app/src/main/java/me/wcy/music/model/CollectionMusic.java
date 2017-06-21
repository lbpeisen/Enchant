package me.wcy.music.model;

/**
 * Created by oreo on 2017-6-18.
 */

public class CollectionMusic {
    String id; //用户ID
    String music_id; //musicID
    String favor; //是否喜欢
    private String path;//路径或者URL String 字段就行
    private String artist; //string
    private String title;

    public CollectionMusic(String id, String music_id, String favor, String title, String artist, String path) {
        this.id = id;
        this.music_id = music_id;
        this.favor = favor;
        this.path = path;
        this.artist = artist;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CollectionMusic(String id, String music_id, String favor, String path, String artist) {
        this.id = id;
        this.music_id = music_id;
        this.favor = favor;
        this.path = path;
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getFavor() {
        return favor;
    }

    public void setFavor(String favor) {
        this.favor = favor;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

package me.wcy.music.model;

/**
 * Created by oreo on 2017-6-19.
 */

public class GetFavoriteMusic {
    String id;

    public GetFavoriteMusic(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

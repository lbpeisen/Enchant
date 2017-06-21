package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by oreo on 2017-6-19.
 * 收藏歌曲接收类
 */

public class FavoriteMusicGroup {
    @SerializedName("favoriteMusic")
    private ArrayList<FavoriteMusic> favoriteMusics;

    public FavoriteMusicGroup(ArrayList<FavoriteMusic> favoriteMusics) {
        this.favoriteMusics = favoriteMusics;
    }

    public ArrayList<FavoriteMusic> getFavoriteMusics() {
        return favoriteMusics;
    }

    public void setFavoriteMusics(ArrayList<FavoriteMusic> favoriteMusics) {
        this.favoriteMusics = favoriteMusics;
    }

    public static class FavoriteMusic {
        private String artist_name;
        private String music_id;
        private String path;
        private String title;

        public FavoriteMusic(String artist_name, String music_id, String path, String title) {
            this.artist_name = artist_name;
            this.music_id = music_id;
            this.path = path;
            this.title = title;
        }

        public String getArtist_name() {
            return artist_name;
        }

        public void setArtist_name(String artist_name) {
            this.artist_name = artist_name;
        }

        public String getMusic_id() {
            return music_id;
        }

        public void setMusic_id(String music_id) {
            this.music_id = music_id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

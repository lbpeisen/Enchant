package me.wcy.music.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by han78 on 2017/6/16.
 */

public class CommentGroup {

    @SerializedName("musicCommnets")
    private ArrayList<Comment> musicCommnets;

    public CommentGroup(ArrayList<Comment> musicCommnets) {
        this.musicCommnets = musicCommnets;
    }

    public ArrayList<Comment> getMusicCommnets() {
        return musicCommnets;
    }

    public void setMusicCommnets(ArrayList<Comment> musicCommnets) {
        this.musicCommnets = musicCommnets;
    }

    public static class Comment implements Comparable<Comment>{
        @SerializedName("comment")
        private String comment;
        @SerializedName("create_time")
        private long create_time;
        @SerializedName("user_name")
        private String user_name;

        public Comment(String comment, long create_time, String user_name) {
            this.comment = comment;
            this.create_time = create_time;
            this.user_name = user_name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        @Override
        public int compareTo(@NonNull Comment o) {
            return  -1*Long.compare(this.create_time,o.getCreate_time());
        }
    }

}

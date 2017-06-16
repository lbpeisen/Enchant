package me.wcy.music.model;

import java.util.ArrayList;

/**
 * Created by han78 on 2017/6/16.
 */

public class CommentGroup {
    private ArrayList<Comment> commentArrayList;

    public CommentGroup(ArrayList<Comment> commentArrayList) {
        this.commentArrayList = commentArrayList;
    }

    public ArrayList<Comment> getCommentArrayList() {
        return commentArrayList;
    }

    public void setCommentArrayList(ArrayList<Comment> commentArrayList) {
        this.commentArrayList = commentArrayList;
    }

    public static class Comment{
        private String title;
        private String content;
        private long time;

        public Comment() {
            this.title = title;
            this.content = content;
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }

}

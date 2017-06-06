package me.wcy.music.model;

/**
 * Created by oreo on 2017/5/30.
 */

public class User {
    String email;
    String password;
    String name;
    String code;
    String token;
    int avator;


    public User(String token) {
        this.token = token;
    }

    public User(String user_email, String user_password) {
        this.email = user_email;
        this.password = user_password;
    }

    public User(String user_email, String user_password, String user_name, String code) {
        this.email = user_email;
        this.password = user_password;
        this.name = user_name;
        this.code = code;
    }

    public User(String user_email, String user_password, String user_name, String code, String token) {
        this.email = user_email;
        this.password = user_password;
        this.name = user_name;
        this.code = code;
        this.token = token;
    }

    public String getUser_email() {
        return email;
    }

    public void setUser_email(String user_email) {
        this.email = user_email;
    }

    public String getUser_password() {
        return password;
    }

    public void setUser_password(String user_password) {
        this.password = user_password;
    }

    public String getUser_name() {
        return name;
    }

    public void setUser_name(String user_name) {
        this.name = user_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

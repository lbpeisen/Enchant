package me.wcy.music.model;

/**
 * Created by oreo on 2016/10/14.
 */
public class Userinfo {
    int user_id;
    String user_name;
    String user_phone;
    String user_mail;
    String user_password;
    int user_academy_number;
    int user_address_province;
    String user_address_city;
    String user_sex;
    int user_avatar;
    int user_type;

    public Userinfo(String user_phone, int user_id, String user_name, String user_mail, String user_password, int user_academy_number, String user_address_city, int user_address_province, String user_sex, int user_avatar, int user_type) {
        this.user_phone = user_phone;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_academy_number = user_academy_number;
        this.user_address_city = user_address_city;
        this.user_address_province = user_address_province;
        this.user_sex = user_sex;
        this.user_avatar = user_avatar;
        this.user_type = user_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_academy_number() {
        return user_academy_number;
    }

    public void setUser_academy_number(int user_academy_number) {
        this.user_academy_number = user_academy_number;
    }

    public int getUser_address_province() {
        return user_address_province;
    }

    public void setUser_address_province(int user_address_province) {
        this.user_address_province = user_address_province;
    }

    public String getUser_address_city() {
        return user_address_city;
    }

    public void setUser_address_city(String user_address_city) {
        this.user_address_city = user_address_city;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(int user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}

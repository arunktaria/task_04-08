package com.example.task04_08.RoomDatabase;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_tbl")
public class EntityClss {


    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name = "username")
    String username;

    @ColumnInfo(name = "Date")
    String date;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "email")
    String email;

    @ColumnInfo(name = "loginstatus")
    boolean status = false;

        @ColumnInfo(name = "captured_img")
        Bitmap bitmap;

    public EntityClss(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int get_id() {
        return _id;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

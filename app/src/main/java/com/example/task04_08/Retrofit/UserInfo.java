package com.example.task04_08.Retrofit;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "base_tbl",indices = @Index(value = {"id_ads"} ,unique = true))
public class UserInfo {

    @ColumnInfo(name = "Title")
    String Title;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_ads")
    public int id_ads;

    @ColumnInfo(name = "Description")
    String Description;

    @ColumnInfo(name = "email")
    String email;

   /* @ColumnInfo(name = "imgname")
    Bitmap bitmapretro;
*/
    @ColumnInfo(name = "imgnamestr")
    String imgname;

    public UserInfo() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getId_ads() {
        return id_ads;
    }

    public void setId_ads(int id_ads) {
        this.id_ads = id_ads;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }
}

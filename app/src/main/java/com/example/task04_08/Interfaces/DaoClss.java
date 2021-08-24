package com.example.task04_08.Interfaces;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.task04_08.Retrofit.UserInfo;
import com.example.task04_08.RoomDatabase.EntityClss;

import java.util.List;

@Dao
public interface DaoClss {

    @Insert
    void insert(EntityClss entityClss);

    @Query("SELECT * FROM user_tbl order by _id desc")
     List<EntityClss> getdata();

    @Update
    void updateUser(EntityClss entityClss);

    @Query("SELECT * FROM user_tbl WHERE _id LIKE :id")
    EntityClss getId(int id);

    @Query("select * from user_tbl order by _id desc")
    LiveData<List<EntityClss>> getalldata();

    @Query("select * from user_tbl where _id like :id ")
     LiveData<List<EntityClss>> getidforcurrentuser(Integer id);

    @Query("UPDATE user_tbl SET loginstatus= :valuee where _id= :idupdate")
      void setLoginvalue(boolean valuee,int idupdate);

    @Query("delete from user_tbl where _id like :id_delete")
    public void deleteUser(int id_delete);

    @Query("select * from user_tbl where email like :email and password like :psswd")
     EntityClss checkLogin(String email,String psswd);

    @Query("select * from user_tbl where loginstatus = :bl  ")
    LiveData<EntityClss> getOnlineStatus(boolean bl);

    @Query("select captured_img from user_tbl where _id= :id")
    Bitmap getBitmap(int id);

    @Query("update user_tbl set loginstatus = :falsee where loginstatus = :truee")
    void setTrutoFalse(boolean falsee,boolean truee);


    @Query("select * from user_tbl where email  like :emaill")
    public EntityClss checkEmail(String emaill);





    @Insert
    public void insertUserData(List<UserInfo> listdata);

    @Query("select * from base_tbl")
    public LiveData<List<UserInfo>> getallUserdata();

}




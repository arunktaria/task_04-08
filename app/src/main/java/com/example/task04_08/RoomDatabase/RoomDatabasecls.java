package com.example.task04_08.RoomDatabase;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.task04_08.Interfaces.DaoClss;
import com.example.task04_08.Retrofit.UserInfo;

@TypeConverters(BitmapConvertor.class)
@Database(entities = {EntityClss.class,UserInfo.class},version = 1)
public abstract class RoomDatabasecls extends RoomDatabase {

    public  abstract DaoClss getDoa();

    private static volatile  RoomDatabasecls instance;
    public static synchronized RoomDatabasecls getInstance(Context context)
    {
        if (instance==null)
        {
            instance= Room.databaseBuilder(context,RoomDatabasecls.class,"DB_user")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

  }
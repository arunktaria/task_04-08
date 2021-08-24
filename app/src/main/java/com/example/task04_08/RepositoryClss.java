package com.example.task04_08;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.task04_08.Interfaces.DaoClss;
import com.example.task04_08.Retrofit.UserInfo;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.List;

public class RepositoryClss {
    DaoClss database;
    Context context;

    public RepositoryClss(Context context) {
        this.context=context;
    database=RoomDatabasecls.getInstance( context).getDoa();
    }

    public void insertToRoom(List<UserInfo> list)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.insertUserData(list);
            }
        }).start();
    }

    public LiveData<List<UserInfo>> getAlluserData()
    {
    return database.getallUserdata();
    }

}

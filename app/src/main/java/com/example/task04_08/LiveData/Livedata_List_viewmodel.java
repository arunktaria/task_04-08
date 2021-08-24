package com.example.task04_08.LiveData;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.annotation.XmlRes;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.task04_08.Adapters.RetroRecyclerAdatpter;
import com.example.task04_08.Interfaces.RetrofitApi;
import com.example.task04_08.RepositoryClss;
import com.example.task04_08.Retrofit.Retrofitinstance;
import com.example.task04_08.Retrofit.UserInfo;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Livedata_List_viewmodel extends AndroidViewModel {
    Application application;
    RoomDatabasecls roomDatabasecls;
     Integer id;
    Context context;
    RetrofitApi retrofitApi;
  MutableLiveData<List<UserInfo>> userinfodata;
    List<UserInfo> retroList;
    RepositoryClss repositoryClss;
    ConnectivityManager cm;
    MutableLiveData<Integer> isconected;
    public Livedata_List_viewmodel(@NonNull Application application) {
        super(application);
        this.application = application;
        roomDatabasecls = RoomDatabasecls.getInstance(application.getApplicationContext());
        userinfodata=new MutableLiveData<>();
        retroList=new ArrayList<>();
        isconected=new MutableLiveData<>();
        repositoryClss=new RepositoryClss(application.getApplicationContext());
        cm=(ConnectivityManager) application.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public LiveData<List<EntityClss>> getAlldata() {
        return roomDatabasecls.getDoa().getalldata();
    }

    public void setCurecntUser(Integer id) {
        this.id = id;
        this.context = context;

    }




public LiveData<List<EntityClss>> getCurentUser_action()
{
    return roomDatabasecls.getDoa().getidforcurrentuser(id);
}

public LiveData<EntityClss> getOnlineStatus() {
    return roomDatabasecls.getDoa().getOnlineStatus(true);

}





/*
public MutableLiveData<List<UserInfo>> getUserinfoData()
{
    retrofitApi= Retrofitinstance.Retrofitinstance().create(RetrofitApi.class);
    Call<List<UserInfo>> call = retrofitApi.getUserlist();
    call.enqueue(new Callback<List<UserInfo>>() {
        @Override
        public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
            userinfodata.postValue(response.body());
        }

        @Override
        public void onFailure(Call<List<UserInfo>> call, Throwable t) {
        userinfodata.postValue(null);
        }
    });
return userinfodata;
}
*/
public LiveData<List<UserInfo>> getAlluserData()
{
    return repositoryClss.getAlluserData();
}

}

package com.example.task04_08.Interfaces;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.task04_08.Retrofit.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    @GET("showdataofads")
    Call<List<UserInfo>> getUserlist();


}

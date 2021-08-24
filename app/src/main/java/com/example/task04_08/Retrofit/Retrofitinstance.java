package com.example.task04_08.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitinstance {
    public static Retrofit retrofit;
   public static String url="https://myandroiddevsite.000webhostapp.com/";
    public static Retrofit Retrofitinstance() {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}

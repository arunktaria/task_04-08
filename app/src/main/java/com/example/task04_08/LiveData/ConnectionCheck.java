package com.example.task04_08.LiveData;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.lifecycle.LiveData;

public class ConnectionCheck extends LiveData<Boolean> {
   /* Context context;
    ConnectivityManager cm;
    ConnectivityManager.NetworkCallback callback;

    public ConnectionCheck(Context context) {
        this.context=context;
        cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        callback=new ConnectivityManager.NetworkCallback();
    }

    @Override
    protected void onActive() {
        NetworkRequest networkRequest=new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        cm.registerNetworkCallback(networkRequest,callback);
        super.onActive();
    }

    @Override
    protected void onInactive() {
        cm.unregisterNetworkCallback(callback);
        super.onInactive();
    }


*/
}

package com.example.task04_08.Fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task04_08.Adapters.RetroRecyclerAdatpter;
import com.example.task04_08.Interfaces.DaoClss;
import com.example.task04_08.Interfaces.RetrofitApi;
import com.example.task04_08.LiveData.Livedata_List_viewmodel;
import com.example.task04_08.R;
import com.example.task04_08.RepositoryClss;
import com.example.task04_08.Retrofit.Retrofitinstance;
import com.example.task04_08.Retrofit.UserInfo;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitListFragment extends Fragment {

    RecyclerView recyclerView;
    RetroRecyclerAdatpter adapter;
    Livedata_List_viewmodel viewmode;
    List<UserInfo> list;
    DaoClss database;
    RepositoryClss repositoryClss;
    RetrofitApi retrofitApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retrofit_list, container, false);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview_retrofit);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        database = RoomDatabasecls.getInstance(getContext()).getDoa();
        viewmode = ViewModelProviders.of(getActivity()).get(Livedata_List_viewmodel.class);
        repositoryClss = new RepositoryClss(getContext());

        retrofitApi = Retrofitinstance.Retrofitinstance().create(RetrofitApi.class);
        Call<List<UserInfo>> call = retrofitApi.getUserlist();
        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
               repositoryClss.insertToRoom(response.body());
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                Toast.makeText(getContext(), "connection failure!", Toast.LENGTH_SHORT).show();
            }
        });

        viewmode.getAlluserData().observe(getActivity(), new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfos) {
                if (userInfos != null) {
                    adapter = new RetroRecyclerAdatpter(userInfos, getContext());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}
package com.example.task04_08.Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task04_08.Adapters.recyclerAdapter;
import com.example.task04_08.LiveData.Livedata_List_viewmodel;
import com.example.task04_08.R;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.ArrayList;
import java.util.List;


public class userList extends Fragment {
List<EntityClss> list;
RecyclerView recyclerView;
Context context;
recyclerAdapter adapter;
RoomDatabasecls roomDatabasecls;
Livedata_List_viewmodel dataviewmodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.userlist_frag, container, false);
    context=getContext();
    list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        roomDatabasecls= RoomDatabasecls.getInstance(context);
        dataviewmodel= ViewModelProviders.of(getActivity()).get(Livedata_List_viewmodel.class);
        refreshlistmethod();



        return view;
    }

    public void refreshlistmethod(){
    dataviewmodel.getAlldata().observe(getActivity(), new Observer<List<EntityClss>>() {
    @Override
    public void onChanged(List<EntityClss> entityClsses) {
        adapter=new recyclerAdapter(context,entityClsses);
        recyclerView.setAdapter(adapter);
    }
});






      /*  Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                list= roomDatabasecls.getDoa().getdata();
               new Handler(Looper.getMainLooper()).post(new Runnable() {
                   @Override
                   public void run() {
                       adapter=new recyclerAdapter(context,list);
                       recyclerView.setAdapter(adapter);

                   }
               });
            }
        });
        thread.start();
*/

    }

}
package com.example.task04_08.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task04_08.R;
import com.example.task04_08.Retrofit.UserInfo;

import java.util.List;

public class RetroRecyclerAdatpter extends RecyclerView.Adapter<RetroRecyclerAdatpter.Viewholderclss> {
  List<UserInfo> list;
  Context context;
    public RetroRecyclerAdatpter(List<UserInfo> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public Viewholderclss onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.retrofititemlist,parent,false);
       return new Viewholderclss(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholderclss holder, int position) {
     UserInfo ob=list.get(position);

     holder.title.setText(ob.getTitle());
     holder.body.setText(ob.getDescription());
     holder.id.setText(ob.getImgname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholderclss extends RecyclerView.ViewHolder {
        TextView title,body,id;
        public Viewholderclss(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.retrofit_title);
            body=itemView.findViewById(R.id.retrofit_body);
            id=itemView.findViewById(R.id.retrofit_id);
        }
    }
}

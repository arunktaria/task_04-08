package com.example.task04_08.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task04_08.Dialogs.Dialog_showuser;
import com.example.task04_08.R;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.Viewholderclss> {
    Context context;
    List<EntityClss> userdatarlist;
    EntityClss entityClss;
    SharedPreferences preferences;
    int idpre;
    RoomDatabasecls roomDatabasecls;
    boolean status=false;
    int loggedUser;
    public recyclerAdapter(Context context, List<EntityClss> entitylist) {
        this.context = context;
        this.userdatarlist = entitylist;
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        idpre=preferences.getInt("id",0);
        roomDatabasecls= RoomDatabasecls.getInstance(context);
    }

    @NonNull

    @Override
    public Viewholderclss onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleradapter, parent, false);
        return new Viewholderclss(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.Viewholderclss holder, int position) {
        entityClss = userdatarlist.get(position);

        holder.username.setText(entityClss.getUsername()+" id :" +entityClss.get_id());
        holder.email.setText(entityClss.getPassword());
        holder.date_listitem.setText(entityClss.getDate());

        holder.imageView.setImageBitmap(entityClss.getBitmap());

        if (entityClss.getStatus()==true)

         holder.loginstatus.setText("online");
        else
            holder.loginstatus.setText("");
    }

    @Override
    public int getItemCount() {
        return userdatarlist.size();
    }

    public class Viewholderclss extends RecyclerView.ViewHolder  {

        TextView username, email;
        ImageView imageView;
        TextView loginstatus,date_listitem;

        public Viewholderclss(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.usernametext_clayout);
            email = itemView.findViewById(R.id.useremail_clayout);
            imageView = itemView.findViewById(R.id.userimgrecycler);
            date_listitem=itemView.findViewById(R.id.date_listitem);
            loginstatus=itemView.findViewById(R.id.loginstatus);
            ImageButton btn_delete=itemView.findViewById(R.id.deleteitembtn);


            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=getAdapterPosition();
                   EntityClss ob= userdatarlist.get(index);
                    int id_delete=ob.get_id();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                        roomDatabasecls.getDoa().deleteUser(id_delete);

                        }
                    }).start();


                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    EntityClss entityClss=userdatarlist.get(getAdapterPosition());
                    int id= entityClss.get_id();
                    Dialog_showuser dialogLayout=new Dialog_showuser(context,userdatarlist,position,id);
                    dialogLayout.show();
                }
            });
        }
    }
}
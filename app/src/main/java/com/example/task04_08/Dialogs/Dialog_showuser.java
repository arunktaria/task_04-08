package com.example.task04_08.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.task04_08.R;
import com.example.task04_08.Interfaces.DaoClss;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.List;

public class Dialog_showuser extends Dialog {
   Context context;
List<EntityClss> list;
SharedPreferences preferences;
int position,id;
DaoClss database;
Bitmap bitmap;
    public Dialog_showuser(@NonNull Context context, List<EntityClss> list, int position,int id) {

        super(context);
        this.list=list;
        this.position=position;
        this.id=id;
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
        database= RoomDatabasecls.getInstance(getContext()).getDoa();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialoglayout);
        TextView username=findViewById(R.id.usernamedialog);
        TextView password=findViewById(R.id.passworddialog);
        TextView email=findViewById(R.id.emaildialog);
        ImageView imageView=findViewById(R.id.edituser_dialog);
        ImageView imageviw=findViewById(R.id.imgdialog);
        new Thread(new Runnable() {
            @Override
            public void run() {
               bitmap= database.getBitmap(id);
               new Handler(Looper.getMainLooper()).post(new Runnable() {
                   @Override
                   public void run() {
                  imageviw.setImageBitmap(bitmap);
                   }
               });
            }
        }).start();




//imageviw.setImageBitmap(bitmap);

        EntityClss entityClss=list.get(position);

            int userId=  entityClss.get_id();

            int  idp =preferences.getInt("id",0);
            if (id!=idp)
            {

                imageView.setVisibility(View.GONE);

            }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_dialog dialog=new Edit_dialog(getContext(),userId);
                dialog.show();

            }
        });





        username.setText("username : "+ entityClss.getUsername());
        password.setText("Password : "+entityClss.getPassword());
        email.setText("Email : "+entityClss.getEmail());
       // super.onCreate(savedInstanceState);
    }
}

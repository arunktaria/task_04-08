package com.example.task04_08.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.task04_08.R;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.ArrayList;
import java.util.List;

public class Edit_dialog extends Dialog {
    int userId;
    Context context;
    List<EntityClss> list;
    RoomDatabasecls roomDatabasecls;
    Button btnupdate;
    EditText username,password,email;
    String usernametxt,emailtxt,passwordtxt;
    SharedPreferences preferences;
    public Edit_dialog(@NonNull Context context,int userId) {
        super(context);
        this.context=context;
        this.userId=userId;
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edituser_layout);
        list=new ArrayList<>();
        username=findViewById(R.id.usernameedit_dialog);
        password=findViewById(R.id.passwordedit_dialog);
        email=findViewById(R.id.emailedit_dialog);
        btnupdate=findViewById(R.id.btnupdate);

        roomDatabasecls= RoomDatabasecls.getInstance(getContext());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernametxt=username.getText().toString();
                passwordtxt=password.getText().toString();
                emailtxt=email.getText().toString();
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {

                        list=roomDatabasecls.getDoa().getdata();

                        for (EntityClss entityClss :  list)
                        {
                            String str=entityClss.getUsername();
                            int id=entityClss.get_id();
                                if (userId==id) {

                                    EntityClss entityClss1=roomDatabasecls.getDoa().getId(id);
                                   entityClss1.setUsername(usernametxt);
                                   entityClss1.setEmail(emailtxt);
                                   entityClss1.setPassword(passwordtxt);
                                    roomDatabasecls.getDoa().updateUser(entityClss1);

                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(), "data updated!", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            }

                        }
                });
            thread.start();


            }

        });






    }
}

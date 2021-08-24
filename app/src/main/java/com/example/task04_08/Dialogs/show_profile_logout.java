package com.example.task04_08.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task04_08.Login_signup.userLogin;
import com.example.task04_08.R;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;


public class show_profile_logout extends Dialog {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RoomDatabasecls roomDatabasecls;
    Bitmap bitmap;
    int id;
    Context context;

    public show_profile_logout(@NonNull Context context, Bitmap bitmap) {
        super(context);
        this.context = context;
        this.bitmap = bitmap;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_profile_logout);
        TextView username = findViewById(R.id.logout_username);
        TextView password = findViewById(R.id.logout_password);
        TextView email = findViewById(R.id.logout_email);
        ImageButton logoutbtn = findViewById(R.id.logoutbtn);
        ImageView imageView = findViewById(R.id.imgdialog);

        roomDatabasecls = RoomDatabasecls.getInstance(getContext());


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
        id = sharedPreferences.getInt("id", -1);


        new Thread(new Runnable() {
            @Override
            public void run() {

                bitmap = roomDatabasecls.getDoa().getBitmap(id);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);

                    }
                });


            }
        }).start();


        String user = sharedPreferences.getString("username", "null");
        if (user.equals("null")) {
            logoutbtn.setVisibility(View.GONE);
        } else {
            logoutbtn.setVisibility(View.VISIBLE);
        }

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        roomDatabasecls.getDoa().setLoginvalue(false, id);
                    }
                }).start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        editor.clear();
                        editor.apply();

                    }
                }, 500);

            }
        });
        username.setText("Useranme : " + sharedPreferences.getString("username", "null"));
        password.setText("Password : " + sharedPreferences.getString("psswd", "null"));
        email.setText("Email : " + sharedPreferences.getString("email", "null") + "\n id : " + String.valueOf(id));


    }

}
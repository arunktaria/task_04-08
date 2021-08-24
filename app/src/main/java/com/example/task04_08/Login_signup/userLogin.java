package com.example.task04_08.Login_signup;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.task04_08.LiveData.Livedata_List_viewmodel;
import com.example.task04_08.R;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.util.ArrayList;
import java.util.List;

public class userLogin extends Fragment {





    RoomDatabasecls roomDatabasecls;
    List<EntityClss> list;
    Livedata_List_viewmodel modelclss;
    EditText emailedit;
    EditText passwordedit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int id, idtemp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.userlogin_frag, container, false);
        list = new ArrayList<>();
        emailedit = view.findViewById(R.id.emailedit);
        passwordedit = view.findViewById(R.id.password);
        Button btnsubmit = view.findViewById(R.id.loginbtn);
        btnsubmit.setBackgroundResource(R.drawable.cbtn);
        modelclss = ViewModelProviders.of(getActivity()).get(Livedata_List_viewmodel.class);
        LinearLayout layout=view.findViewById(R.id.linear_login);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager ob=(InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                ob.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });
        roomDatabasecls = RoomDatabasecls.getInstance(getContext());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
        id = sharedPreferences.getInt("id", -1);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        roomDatabasecls.getDoa().setTrutoFalse(false, true);
                    }
                }).start();
                operationperform();
                //Toast.makeText(getContext(), String.valueOf(idtemp), Toast.LENGTH_SHORT).show();


            }
        });


        idtemp = id;


        return view;
    }


    public void operationperform() {

        if (emailedit.getText().toString().isEmpty() && passwordedit.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "all fields are Required!", Toast.LENGTH_SHORT).show();
        } else {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    EntityClss clss = roomDatabasecls.getDoa().checkLogin(emailedit.getText().toString(), passwordedit.getText().toString());

                    if (clss == null) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "no user found!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {

                        String username1 = clss.getUsername();
                        String psswd = clss.getPassword();
                        String email = clss.getEmail();
                        int id1 = clss.get_id();
                        roomDatabasecls.getDoa().setLoginvalue(true, clss.get_id());

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {


                                editor.putString("username", username1);
                                editor.putString("psswd", psswd);
                                editor.putString("email", email);
                                editor.putInt("id", id1);
                                editor.putBoolean("status", true);
                                editor.apply();

                                // modelclss.setCurecntUser(id);
                                Toast.makeText(getContext(), "logged in with : " + username1, Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                }
            });

            thread.start();
        }
    }
}




















                /*for (EntityClss clsob : list) {
                    String email = clsob.getEmail().toString();
                    String psswd = clsob.getPassword().toString();
                    String username = clsob.getUsername().toString();
                  id=  clsob.get_id();
                    if (emailedit.getText().toString().equals(email) && passwordedit.getText().toString().equals(psswd)) {

                        roomDatabasecls.getDoa().setLoginvalue(true,clsob._id);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                editor.putString("username", username);
                                editor.putString("psswd", psswd);
                                editor.putString("email", email);
                                editor.putInt("id",id);
                                editor.putBoolean("status",true);
                                editor.apply();
                                usernamestr=username;
                                 modelclss.setCurecntUser(id);



                               Toast.makeText(getContext(), "logged in with : " + username, Toast.LENGTH_SHORT).show();



                            }
                        });

                    } else {


                        if (emailedit.getText().toString().isEmpty() && passwordedit.getText().toString().isEmpty()) {

                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    emailedit.setError("Required!");
                                    passwordedit.setError("Required!");
                                }
                            });


                        } else if (emailedit.getText().toString().isEmpty()) {
                            if (!passwordedit.getText().toString().isEmpty())
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        emailedit.setError("Required!");

                                    }
                                });


                        } else if (passwordedit.getText().toString().isEmpty()) {

                            if (!emailedit.getText().toString().isEmpty()) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        passwordedit.setError("Required!");

                                    }
                                });

                            }
                        }

                        String emailt = emailedit.getText().toString(), psswdt = passwordedit.getText().toString();

                        if (emailt.equals(email)) {

                            if (!psswdt.equals(psswd)) {
                                //wrong psswd
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "wrong psswd", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                        }
                        if (psswdt.equals(psswd)) {
                            if (!emailt.equals(email)) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "wrong email", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        }
                    }
                }*/







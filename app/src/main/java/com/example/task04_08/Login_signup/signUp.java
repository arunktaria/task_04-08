package com.example.task04_08.Login_signup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.task04_08.R;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class signUp extends Fragment {

    RoomDatabasecls roomDatabasecls;
    List<EntityClss> list;
    ImageButton captureimg;
    EditText username, email, password, cpassword;
    Bitmap bitmap;
    TextView date_textview;
    int month,year,day;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signupfrag, container, false);


        Button submitbtn = view.findViewById(R.id.signupbtn);
        username = view.findViewById(R.id.usernamesign);
        email = view.findViewById(R.id.emailsign);
        password = view.findViewById(R.id.passwordsign);
        cpassword = view.findViewById(R.id.cpasswordsign);
        captureimg = view.findViewById(R.id.captureimg);
        date_textview=view.findViewById(R.id.date_textview);
        roomDatabasecls = RoomDatabasecls.getInstance(getContext());

        LinearLayout layout=view.findViewById(R.id.linear_signup);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager ob=(InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                ob.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.userimg1);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signupMethod();

            }
        });

        Calendar calendar=Calendar.getInstance();

        date_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month =calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                year=calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                        calendar.set(Calendar.MONTH,month1);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        calendar.set(Calendar.YEAR,year1);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy-EE");
                        date_textview.setText(simpleDateFormat.format(calendar.getTime()));

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });



        captureimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {
            Toast.makeText(getContext(), "no image selected", Toast.LENGTH_SHORT).show();
        } else {
            bitmap = (Bitmap) data.getExtras().get("data");
            captureimg.setImageBitmap(bitmap);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    public void signupMethod() {

        String usertxt = username.getText().toString(), emailtxt = email.getText().toString(), passwordtxt = password.getText().toString(), cpasswordtxt = cpassword.getText().toString();

        if (usertxt.isEmpty() && emailtxt.isEmpty() && passwordtxt.isEmpty() && cpasswordtxt.isEmpty()) {
            username.setError("Required!");
            email.setError("Required!");
            password.setError("Required!");
            cpassword.setError("Required!");

        } else {
            if (!passwordtxt.equals(cpasswordtxt)) {
                Toast.makeText(getContext(), "both psswd are not same!", Toast.LENGTH_SHORT).show();
            }
            if (!usertxt.isEmpty() && !passwordtxt.isEmpty() && !cpasswordtxt.isEmpty() && !emailtxt.isEmpty() && passwordtxt.equals(cpasswordtxt)) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        EntityClss entityClss = new EntityClss(usertxt, passwordtxt, emailtxt);
                       if (date_textview.getText().toString().equals(""))
                       {
                           entityClss.setDate("no data available");
                       }else{
                           entityClss.setDate(date_textview.getText().toString());
                       }

                        EntityClss ob=roomDatabasecls.getDoa().checkEmail(emailtxt);
                       if (ob!=null)
                        {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "user already exist!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else
                       {
                           entityClss.setBitmap(bitmap);
                           entityClss.setStatus(false);
                           roomDatabasecls.getDoa().insert(entityClss);
                           new Handler(Looper.getMainLooper()).post(new Runnable() {
                               @Override
                               public void run() {
                                   Toast.makeText(getContext(), "data inserted", Toast.LENGTH_SHORT).show();
                               }
                           });
                       }
                    }
                }).start();


            }
            if (usertxt.isEmpty()) {
                username.setError("Required!");
            }
            if (passwordtxt.isEmpty()) {
                password.setError("Required!");
            }
            if (cpasswordtxt.isEmpty())
                cpassword.setError("Required!");
            if (emailtxt.isEmpty())
                email.setError("Required!");


        }


    }

}

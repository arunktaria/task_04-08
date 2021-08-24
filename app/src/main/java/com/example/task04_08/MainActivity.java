package com.example.task04_08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.task04_08.Adapters.ViewPagerAdapter;
import com.example.task04_08.Dialogs.show_profile_logout;
import com.example.task04_08.LiveData.ConnectionCheck;
import com.example.task04_08.LiveData.Livedata_List_viewmodel;
import com.example.task04_08.RoomDatabase.EntityClss;
import com.example.task04_08.RoomDatabase.RoomDatabasecls;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String usernamestr = "no user";
    Livedata_List_viewmodel viewmodelcls;
    MenuItem itemm;
    TabLayout tablayout;

    List<EntityClss> list;
    RoomDatabasecls roomDatabasecls;
    ViewPager2 pager2;
    ViewPagerAdapter adapter;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getSupportActionBar().setTitle("Project");
        list = new ArrayList<>();
        roomDatabasecls = RoomDatabasecls.getInstance(this);

        viewmodelcls = ViewModelProviders.of(MainActivity.this).get(Livedata_List_viewmodel.class);

        pager2 = findViewById(R.id.viewpagerob);
        tablayout = findViewById(R.id.tablayout);
        tablayout.addTab(tablayout.newTab().setText("login"));
        tablayout.addTab(tablayout.newTab().setText("signup"));
        tablayout.addTab(tablayout.newTab().setText("userlist"));
        tablayout.addTab(tablayout.newTab().setText("Retrolist"));
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        pager2.setAdapter(adapter);

       // ConnectionCheck connectionCheck=new ConnectionCheck(this);
      // getStat(connectionCheck.hasActiveObservers());


                tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        pager2.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tablayout.selectTab(tablayout.getTabAt(position));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.userlogin, menu);

        itemm = menu.findItem(R.id.menuuser_name);


        viewmodelcls.getOnlineStatus().observe(this, new Observer<EntityClss>() {
            @Override
            public void onChanged(EntityClss entityClss) {
                if (entityClss == null) {
                    itemm.setTitle(usernamestr);
                } else {
                    itemm.setTitle(entityClss.getUsername());
                }



            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menuuser_name:


                show_profile_logout ob = new show_profile_logout(MainActivity.this, bitmap);
                ob.show();

                break;


        }


        return super.onOptionsItemSelected(item);
    }


}
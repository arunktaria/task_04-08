package com.example.task04_08.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.task04_08.Fragements.RetrofitListFragment;
import com.example.task04_08.Login_signup.signUp;
import com.example.task04_08.Login_signup.userLogin;
import com.example.task04_08.Fragements.userList;

public class ViewPagerAdapter extends FragmentStateAdapter{


    public ViewPagerAdapter(@NonNull  FragmentManager fragmentManager, @NonNull  Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull

    @Override
    public Fragment createFragment(int position) {
      switch (position)
      {
          case 1: return new signUp();
          case 2: return new userList();
          case 3: return new RetrofitListFragment();
      }

        return new userLogin();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
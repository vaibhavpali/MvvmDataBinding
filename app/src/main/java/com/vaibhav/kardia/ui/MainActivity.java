package com.vaibhav.kardia.ui;

import android.os.Bundle;

import com.vaibhav.kardia.R;
import com.vaibhav.kardia.model.User;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, UserProfileInfoFragment.newInstance())
                    .commitNow();
        }
    }

    public void show(User user) {
        UserDetailFragment userDetailFragment = UserDetailFragment.forUser();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("userDetail")
                .replace(R.id.fragment_container, userDetailFragment, null).commit();
    }
}
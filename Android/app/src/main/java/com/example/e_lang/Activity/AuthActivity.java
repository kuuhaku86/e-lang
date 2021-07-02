package com.example.e_lang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.e_lang.Adapter.AuthPageAdapter;
import com.example.e_lang.Fragment.LoginFragment;
import com.example.e_lang.Fragment.RegisterFragment;
import com.example.e_lang.R;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthPageAdapter pagerAdapter = new AuthPageAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new LoginFragment());
        pagerAdapter.addFragment(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);
    }
}
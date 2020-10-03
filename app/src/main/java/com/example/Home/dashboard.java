package com.example.Home;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.itravel.R;
import com.example.profile_upgrade.Viewpaymentlogs;
import com.example.profile_upgrade.profile_upgrade_main;
import com.example.profile_upgrade.profile_upgrade_main_pre_pay_method;
import com.google.android.material.navigation.NavigationView;

public class dashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    ImageButton button1;
    ImageButton button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        button1 = (ImageButton) findViewById(R.id.Profile_btn);
        button2 = (ImageButton) findViewById(R.id.Feedback_btn);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i=new Intent(dashboard.this, profile_upgrade_main.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i=new Intent(dashboard.this, Viewpaymentlogs.class);
                startActivity(i);
            }
        });
    }
}
package com.example.profile_upgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.Home.dashboard;

public class Intro_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent i=new Intent(Intro_Page.this, dashboard.class);
        startActivity(i);
    }
}
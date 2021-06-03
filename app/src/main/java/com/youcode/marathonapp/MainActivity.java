package com.youcode.marathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
    }

    public void launchForm(View view){
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }


}
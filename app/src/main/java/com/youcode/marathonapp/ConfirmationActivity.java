package com.youcode.marathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("email");
        String birthday = intent.getStringExtra("birthday");
        String gender = intent.getStringExtra("gender");

        ((TextView) findViewById(R.id.NameText)).setText(name);
        ((TextView) findViewById(R.id.PhoneText)).setText(phone);
        ((TextView) findViewById(R.id.EmailText)).setText(email);
        ((TextView) findViewById(R.id.BirthdayText)).setText(birthday);
        ((TextView) findViewById(R.id.GenderText)).setText(gender);
    }

    public void launchHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
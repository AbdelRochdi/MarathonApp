package com.youcode.marathonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    public void launchForm(View view){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        String name = ( (TextView) findViewById(R.id.Name)).getText().toString();
        String phone = ( (TextView) findViewById(R.id.Phone)).getText().toString();
        String email = ( (TextView) findViewById(R.id.Email)).getText().toString();
        String birthday = ( (TextView) findViewById(R.id.Birthday)).getText().toString();
        int radio = ((RadioGroup) findViewById(R.id.genderRadio)).getCheckedRadioButtonId();
        String gender = ((RadioButton) findViewById(radio)).getText().toString();

        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("birthday", birthday);
        intent.putExtra("gender", gender);

        startActivity(intent);
    }
}

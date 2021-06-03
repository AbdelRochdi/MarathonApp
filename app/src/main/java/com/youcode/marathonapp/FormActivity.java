package com.youcode.marathonapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        DB = new DBHelper(this);
    }

    public void launchForm(View view){
        Intent intent = new Intent(this, ConfirmationActivity.class);

        String name = ( (TextView) findViewById(R.id.Name)).getText().toString();
        String phone = ( (TextView) findViewById(R.id.Phone)).getText().toString();
        String email = ( (TextView) findViewById(R.id.Email)).getText().toString();
        String birthday = ( (TextView) findViewById(R.id.Birthday)).getText().toString();
        int radio = ((RadioGroup) findViewById(R.id.GenderRadio)).getCheckedRadioButtonId();
        String gender = ((RadioButton) findViewById(radio)).getText().toString();

        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("birthday", birthday);
        intent.putExtra("gender", gender);

        Boolean insertData = DB.insertUserData(name, phone,birthday,email,gender);
        if (insertData == true){
            Toast.makeText(FormActivity.this, "New entry inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(FormActivity.this, "New entry not inserted", Toast.LENGTH_SHORT).show();
        }

        startActivity(intent);
    }



    public void update(View view){

        String name = ( (TextView) findViewById(R.id.Name)).getText().toString();
        String phone = ( (TextView) findViewById(R.id.Phone)).getText().toString();
        String email = ( (TextView) findViewById(R.id.Email)).getText().toString();
        String birthday = ( (TextView) findViewById(R.id.Birthday)).getText().toString();
        int radio = ((RadioGroup) findViewById(R.id.GenderRadio)).getCheckedRadioButtonId();
        String gender = ((RadioButton) findViewById(radio)).getText().toString();

        Boolean updateData = DB.updateUserData(name, phone,birthday,email,gender);
        if (updateData == true){
            Toast.makeText(FormActivity.this, "Entry updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(FormActivity.this, "Entry not updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view){

        String email = ( (TextView) findViewById(R.id.Email)).getText().toString();

        Boolean updateData = DB.deleteUserData(email);
        if (updateData == true){
            Toast.makeText(FormActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(FormActivity.this, "User not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(View view){
        Cursor res = DB.getData();
        if (res.getCount() == 0){
            Toast.makeText(FormActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Name :"+res.getString(0)+"\n");
            buffer.append("Phone :"+res.getString(1)+"\n");
            buffer.append("Email :"+res.getString(2)+"\n");
            buffer.append("Birthday :"+res.getString(3)+"\n");
            buffer.append("Gender :"+res.getString(4)+"\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Users");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}

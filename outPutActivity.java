package com.example.uploadretriveimagesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class outPutActivity extends AppCompatActivity
{
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_put);
        tv1=findViewById(R.id.tvTest123);
        String sessionId= getIntent().getStringExtra("USER_SESSION_ID");
        tv1.setText("Welcome " +sessionId);



    }
}

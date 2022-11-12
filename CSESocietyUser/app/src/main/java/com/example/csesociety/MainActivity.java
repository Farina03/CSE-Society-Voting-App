package com.example.csesociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button notifbtn, logoutbtn;
    FirebaseAuth mfirebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifbtn = findViewById(R.id.notifbtn);
        logoutbtn = findViewById(R.id.logoutbtn);
        mfirebaseauth = FirebaseAuth.getInstance();

        notifbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NotificationActivity.class));

            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfirebaseauth.signOut();
                startActivity(new Intent(MainActivity.this,FirstPage.class));
            }
        });
    }
}
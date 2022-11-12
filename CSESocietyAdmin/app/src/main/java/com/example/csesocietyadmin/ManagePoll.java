package com.example.csesocietyadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagePoll extends AppCompatActivity {

    //Spinner spinner;
    Button startPoll, endpollbtn;
    DatabaseReference rootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_poll);

        startPoll = findViewById(R.id.startpollbtn);
        //viewCandidates = findViewById(R.id.viewcandidates);
        //spinner = findViewById(R.id.adminspinner);
        endpollbtn = findViewById(R.id.endpollbtn);
        rootReference = FirebaseDatabase.getInstance().getReference();

        startPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootReference.child("current_pole").child("active_status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue(Boolean.class)) {
                            Toast.makeText(ManagePoll.this, "Poll is already running", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            rootReference.child("current_pole").child("active_status").setValue(true);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        endpollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootReference.child("current_pole").child("active_status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue(Boolean.class)) {
                            rootReference.child("current_pole").child("active_status").setValue(false);
                            Toast.makeText(ManagePoll.this, "Poll ended", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
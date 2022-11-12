package com.example.csesocietyadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddPoll extends AppCompatActivity {

    CheckBox chkbtn1, chkbtn2, chkbtn3, chkbtn4;
    Button createPoll;
    EditText pollnametxt, polldescriptiontxt;
    DatabaseReference rootreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poll);

        chkbtn1 = findViewById(R.id.VP);
        chkbtn2 = findViewById(R.id.GS);
        chkbtn3 = findViewById(R.id.AGS);
        chkbtn4 = findViewById(R.id.EM);
        createPoll = findViewById(R.id.createPollbtn);
        pollnametxt = findViewById(R.id.PollName);
        polldescriptiontxt = findViewById(R.id.Description);
        rootreference = FirebaseDatabase.getInstance().getReference();

        chkbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkbtn1.isChecked()) {
                    chkbtn1.setTextColor(getResources().getColor(R.color.teal_700));
                }
                else {
                    chkbtn1.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        chkbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkbtn2.isChecked()) {
                    chkbtn2.setTextColor(getResources().getColor(R.color.teal_700));
                }
                else {
                    chkbtn2.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        chkbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkbtn3.isChecked()) {
                    chkbtn3.setTextColor(getResources().getColor(R.color.teal_700));
                }
                else {
                    chkbtn3.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        chkbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkbtn4.isChecked()) {
                    chkbtn4.setTextColor(getResources().getColor(R.color.teal_700));
                }
                else {
                    chkbtn4.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        createPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object>init = new HashMap<>();
                init.put("vp",false);
                init.put("gs",false);
                init.put("ags",false);
                init.put("em",false);

                rootreference.child("current_pole").child("posts").setValue(init).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(AddPoll.this, "Poll Created Successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                String pollname = pollnametxt.getText().toString();
                String polldescription = polldescriptiontxt.getText().toString();

                if((chkbtn1.isChecked() == true) || (chkbtn2.isChecked() == true) || (chkbtn3.isChecked() == true) || (chkbtn4.isChecked() == true)) {
                    Toast.makeText(AddPoll.this, "condition check", Toast.LENGTH_SHORT).show();
                    rootreference.child("current_pole").child("poll_name").setValue(pollname).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                        }
                    });
                    rootreference.child("current_pole").child("poll_description").setValue(polldescription).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    rootreference.child("current_pole").child("active_status").setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                    if(chkbtn1.isChecked()) {
                        rootreference.child("current_pole").child("posts").child("vp").setValue(true).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });

                    }
                    if(chkbtn2.isChecked()) {
                        rootreference.child("current_pole").child("posts").child("gs").setValue(true).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });

                    }
                    if(chkbtn3.isChecked()) {
                        rootreference.child("current_pole").child("posts").child("ags").setValue(true).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });

                    }
                    if(chkbtn4.isChecked()) {
                        rootreference.child("current_pole").child("posts").child("em").setValue(true).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });

                    }
                    HashMap<String, Object> hashmap2 = new HashMap<>();
                    hashmap2.put("poll_name", pollname);
                    hashmap2.put("poll_description", polldescription);
                    String key = rootreference.child("notification").push().getKey();
                    rootreference.child("notification").child(key).setValue(hashmap2).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
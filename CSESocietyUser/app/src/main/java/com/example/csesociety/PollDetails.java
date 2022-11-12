package com.example.csesociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class PollDetails extends AppCompatActivity {

    Button applybtn, votebtn;
    DatabaseReference rootReference;
    Spinner mspinner;
    private ArrayList<String>arraylist = new ArrayList<>();
    TextView pollname2, polldescription2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_details);

        applybtn = findViewById(R.id.applybtn);
        votebtn = findViewById(R.id.votebtn);
        pollname2 = findViewById(R.id.pollname2);
        polldescription2 = findViewById(R.id.polldescription2);

        rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.child("current_pole").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //snapshot.getValue(String.class);
                for(DataSnapshot item : snapshot.getChildren()) {
                    if(item.getKey().equals("poll_name")) {
                        pollname2.setText(item.getValue(String.class));
                    }
                    if(item.getKey().equals("poll_description")) {
                        polldescription2.setText(item.getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        votebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PollDetails.this,VotingActivity.class));
            }
        });

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(PollDetails.this);
                View mview = getLayoutInflater().inflate(R.layout.dialoguespinner,null);
                mbuilder.setTitle("Apply");
                mspinner = mview.findViewById(R.id.spinnerdialogue);

                readdata(rootReference);

                mbuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mspinner.getSelectedItem().toString().equalsIgnoreCase("Apply for a post")) {
                            Toast.makeText(PollDetails.this, "Please Select a post to apply", Toast.LENGTH_SHORT).show();

                        } else {

                            String uid = FirebaseAuth.getInstance().getUid();
                            rootReference.child("users").child(uid).child("name").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    HashMap<String, Object>hashMap = new HashMap<>();
                                    String fullname = snapshot.getValue(String.class);
                                    hashMap.put("name",fullname);
                                    hashMap.put("uid", uid);
                                    hashMap.put("vote_count", 0);

                                    if(mspinner.getSelectedItem().toString().equalsIgnoreCase("Vice President")) {

                                        rootReference.child("current_pole").child("candidates").child("vp").child(FirebaseAuth.getInstance().getUid()).setValue(hashMap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(PollDetails.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    if(mspinner.getSelectedItem().toString().equalsIgnoreCase("General Secretary")) {
                                        rootReference.child("current_pole").child("candidates").child("gs").child(FirebaseAuth.getInstance().getUid()).setValue(hashMap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(PollDetails.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    if(mspinner.getSelectedItem().toString().equalsIgnoreCase("Assistant General Secretary")) {
                                        rootReference.child("current_pole").child("candidates").child("ags").child(FirebaseAuth.getInstance().getUid()).setValue(hashMap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(PollDetails.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    if(mspinner.getSelectedItem().toString().equalsIgnoreCase("Executive Member")) {
                                        rootReference.child("current_pole").child("candidates").child("em").child(FirebaseAuth.getInstance().getUid()).setValue(hashMap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(PollDetails.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    }
                });

                mbuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mbuilder.setView(mview);
                AlertDialog alertDialog = mbuilder.create();
                alertDialog.show();
            }
        });
    }

    void readdata(DatabaseReference rootreference) {
        rootreference.child("current_pole").child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arraylist.clear();
                arraylist.add("Choose a post");
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    if(dataSnapshot.getKey().equals("vp") && dataSnapshot.getValue(Boolean.class)) {
                        arraylist.add("Vice President");
                    }
                    if(dataSnapshot.getKey().equals("gs") && dataSnapshot.getValue(Boolean.class)) {
                        arraylist.add("General Secretary");
                    }
                    if(dataSnapshot.getKey().equals("ags") && dataSnapshot.getValue(Boolean.class)) {
                        arraylist.add("Assistant General Secretary");
                    }
                    if(dataSnapshot.getKey().equals("em") && dataSnapshot.getValue(Boolean.class)) {
                        arraylist.add("Executive Member");
                    }
                }
                ArrayAdapter<String>arrayadapter = new ArrayAdapter<>(PollDetails.this, android.R.layout.simple_spinner_item,arraylist);
                mspinner.setAdapter(arrayadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
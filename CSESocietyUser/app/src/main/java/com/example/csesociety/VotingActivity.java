package com.example.csesociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class VotingActivity extends AppCompatActivity {

    LinearLayout vplayout, gslayout, agslayout, emlayout;
    ArrayList<String> vparray = new ArrayList<>();
    ArrayList<String> gsarray = new ArrayList<>();
    ArrayList<String> agsarray = new ArrayList<>();
    ArrayList<String> emarray = new ArrayList<>();
    Spinner vpspinner,gsspinner,agsspinner,emspinner;
    DatabaseReference rootReference;
    Button votecast;
    HashMap<String, Object>vphashmap = new HashMap<>();
    HashMap<String, Object>gshashmap = new HashMap<>();
    HashMap<String, Object>agshashmap = new HashMap<>();
    HashMap<String, Object>emhashmap = new HashMap<>();
    String fullname = "teststring";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        vplayout = findViewById(R.id.vplayout);
        gslayout = findViewById(R.id.gslayout);
        agslayout = findViewById(R.id.agslayout);
        emlayout = findViewById(R.id.emlayout);
        votecast = findViewById(R.id.votecast);

        vpspinner = findViewById(R.id.vpspinner);
        gsspinner = findViewById(R.id.gsspinner);
        agsspinner = findViewById(R.id.agsspinner);
        emspinner = findViewById(R.id.emspinner);

        rootReference = FirebaseDatabase.getInstance().getReference();
        removeUnnecessaryLayouts();
        setSpinnerOptions("gs");
        setSpinnerOptions("ags");
        setSpinnerOptions("vp");
        setSpinnerOptions("em");
        
        votecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*getFullName("pd4jUwtASBcUn5xDpkBkqYODSW93");*/
                //Toast.makeText(VotingActivity.this, fullname, Toast.LENGTH_SHORT).show();

            }
        });

    }
    void setSpinnerOptions(String node) {

        rootReference.child("current_pole").child("candidates").child(node).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(node.equals("vp")) {
                    vparray.clear();
                    vparray.add("Choose a candidate");

                    for(DataSnapshot item : snapshot.getChildren()) {

                        vparray.add(item.child("name").getValue(String.class));
                        //vphashmap.put(fullname,item.child("uid").getValue(String.class));
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(VotingActivity.this, android.R.layout.simple_spinner_item, vparray);
                    vpspinner.setAdapter(arrayAdapter);
                }
                if(node.equals("gs")) {
                    gsarray.clear();
                    gsarray.add("Choose a candidate");
                    for(DataSnapshot item : snapshot.getChildren()) {


                        gsarray.add(item.child("name").getValue(String.class));
                        //gshashmap.put(fullname,item.child("uid").getValue(String.class));

                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(VotingActivity.this, android.R.layout.simple_spinner_item, gsarray);
                    gsspinner.setAdapter(arrayAdapter);
                }
                if(node.equals("ags")) {
                    agsarray.clear();
                    agsarray.add("Choose a candidate");
                    for(DataSnapshot item : snapshot.getChildren()) {

                        agsarray.add(item.child("name").getValue(String.class));
                        //agshashmap.put(fullname,item.child("uid").getValue(String.class));
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(VotingActivity.this, android.R.layout.simple_spinner_item, agsarray);
                    agsspinner.setAdapter(arrayAdapter);
                }
                if(node.equals("em")) {
                    emarray.clear();
                    emarray.add("Choose a candidate");
                    for(DataSnapshot item : snapshot.getChildren()) {

                        emarray.add(item.child("name").getValue(String.class));
                        //emhashmap.put(fullname,item.child("uid").getValue(String.class));

                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(VotingActivity.this, android.R.layout.simple_spinner_item, emarray);
                    emspinner.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    void removeUnnecessaryLayouts() {

        rootReference.child("current_pole").child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    if(item.getKey().equals("vp") && !item.getValue(Boolean.class)) {
                        ((ViewGroup)vplayout.getParent()).removeView(vplayout);
                    }
                    if(item.getKey().equals("gs") && !item.getValue(Boolean.class)) {
                        ((ViewGroup)gslayout.getParent()).removeView(gslayout);
                    }
                    if(item.getKey().equals("ags") && !item.getValue(Boolean.class)) {
                        ((ViewGroup)agslayout.getParent()).removeView(agslayout);
                    }
                    if(item.getKey().equals("em") && !item.getValue(Boolean.class)) {
                        ((ViewGroup)emlayout.getParent()).removeView(emlayout);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
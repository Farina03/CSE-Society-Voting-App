package com.example.csesociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DatasetFire>arrayList;
    private FirebaseRecyclerOptions<DatasetFire> options;
    private FirebaseRecyclerAdapter<DatasetFire, FirebaseViewHolder> adapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<DatasetFire>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("notification");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<DatasetFire>().setQuery(databaseReference,DatasetFire.class).build();
        adapter = new FirebaseRecyclerAdapter<DatasetFire, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull DatasetFire model) {

                holder.pollname.setText(model.getPoll_name());
                holder.polldescription.setText(model.getPoll_description());
                holder.polldetailsbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(NotificationActivity.this,PollDetails.class));

                    }
                });

            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewHolder(LayoutInflater.from(NotificationActivity.this).inflate(R.layout.row,parent,false));
            }
        };

        recyclerView.setAdapter(adapter);
    }
}
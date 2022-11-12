package com.example.csesociety;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    TextView pollname, polldescription;
    Button polldetailsbtn;
    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        pollname = itemView.findViewById(R.id.pollname);
        polldescription = itemView.findViewById(R.id.polldescription);
        polldetailsbtn = itemView.findViewById(R.id.polldetailsbtn);

    }
}

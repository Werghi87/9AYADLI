package com.example.a9ayadli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    public Context context;
    public Activity activity;
    public ArrayList note_id, note_title, note_deadline, note_notes;

    CustomAdapter(Activity activity, Context context,
                  ArrayList note_id,
                  ArrayList note_title,
                  ArrayList note_deadline,
                  ArrayList note_notes) {
        this.context = context;
        this.activity = activity;
        this.note_deadline = note_deadline;
        this.note_id = note_id;
        this.note_notes = note_notes;
        this.note_title = note_title;



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        holder.note_deadline_txt.setText(String.valueOf(note_deadline.get(position)));
        holder.note_notes_txt.setText(String.valueOf(note_notes.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(note_id.get(position)));
                intent.putExtra("title", String.valueOf(note_title.get(position)));
                intent.putExtra("Deadline", String.valueOf(note_deadline.get(position)));
                intent.putExtra("To Do", String.valueOf(note_notes.get(position)));
                activity.startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView note_id_txt, note_title_txt, note_deadline_txt, note_notes_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_id_txt = itemView.findViewById(R.id.note_id_txt);
            note_deadline_txt = itemView.findViewById(R.id.note_deadline_txt);
            note_title_txt = itemView.findViewById(R.id.note_title_txt);
            note_notes_txt = itemView.findViewById(R.id.note_notes_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }
}

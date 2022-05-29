package com.example.a9ayadli;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, Date_input, notes_input;

    AppCompatButton update_button, delete_button;

    String id, title, ddl, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        title_input = findViewById(R.id.title_input2);
        Date_input = findViewById(R.id.Date_input2);
        notes_input = findViewById(R.id.notes_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        //getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                if( savedInstanceState==null){

                    Bundle extras = getIntent().getExtras();
                    if (extras==null){
                        id=null;
                    }else   {
                        id=extras.getString("id");
                    }
                }else{
                    id=(String) savedInstanceState.getSerializable("id");
                }
                title = title_input.getText().toString().trim();
                ddl = Date_input.getText().toString().trim();
                notes = notes_input.getText().toString().trim();
                myDB.updateData(id, title, ddl, notes);
                finish();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                if( savedInstanceState==null){

                    Bundle extras = getIntent().getExtras();
                    if (extras==null){
                        id=null;
                    }else   {
                        id=extras.getString("id");
                    }
                }else{
                    id=(String) savedInstanceState.getSerializable("id");
                }
                myDB.deleteOneRow(id);
                finish();
            }
        });

    }

    /*void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("Deadline") && getIntent().hasExtra("notes")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            ddl = getIntent().getStringExtra("Deadline");
            notes = getIntent().getStringExtra("notes");

            //Setting Intent Data
            title_input.setText(title);
            Date_input.setText(ddl);
            notes_input.setText(notes);
            Log.d("stev", title+" "+ddl+" "+notes);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }*/

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}


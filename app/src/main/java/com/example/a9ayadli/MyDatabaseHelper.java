package com.example.a9ayadli;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABASE_NAME = "9ayadli.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "My_list";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "List_title";
    public static final String COLUMN_DEADLINE = "List_deadline";
    public static final String COLUMN_DO = "What_to_do";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_DEADLINE + " TEXT, " +
                        COLUMN_DO + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addNotes(String title,String date, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title );
        cv.put(COLUMN_DEADLINE, date );
        cv.put(COLUMN_DO, note );

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed, Try again!", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "Added Successfully ! :D", Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null ;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String date, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (title.length()>0){
            cv.put(COLUMN_TITLE, title);
        }
        if(date.length()>0){
            cv.put(COLUMN_DEADLINE, date);
        }
        if(note.length()>0){
            cv.put(COLUMN_DO, note);
        }


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
    }

}

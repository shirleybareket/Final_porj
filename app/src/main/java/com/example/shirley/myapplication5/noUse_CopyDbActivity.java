package com.example.shirley.myapplication5;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class noUse_CopyDbActivity extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_db);

        ((Button) findViewById(R.id.button01)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noUse_DatabaseHelper myDbHelper = new noUse_DatabaseHelper(noUse_CopyDbActivity.this);
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Toast.makeText(noUse_CopyDbActivity.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("station", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Toast.makeText(noUse_CopyDbActivity.this,
                                "_id: " + c.getString(0) + "\n" +
                                        "STATION: " + c.getString(1) + "\n" +
                                        "LONGITUDE: " + c.getString(2) + "\n" +
                                        "LATITUDE:  " + c.getString(3),
                                Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());
                }
            }
        });

    }


}

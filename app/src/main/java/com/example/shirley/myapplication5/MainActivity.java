package com.example.shirley.myapplication5;

import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                aboutUs();
                return true;
            case R.id.favorite:
                favorite();
                return true;
            case R.id.history:
                history();
                return true;
            case R.id.contact:
                contact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void aboutUs() {
    }

    private void favorite() {
    }

    private void history() {
    }

    private void contact() {
    }

    //
    public void findCurrentLoction(View view) {
        Intent intent = new Intent(this, CurrentLocation.class);
        startActivity(intent);
    }

    public void searchAddress(View view) {
        Intent intent = new Intent(this, SearchAddress.class);
        startActivity(intent);
    }
    public void buildTrack(View view){
        Intent intent = new Intent(this, BuildingRunningTrack.class);
        startActivity(intent);
    }
}



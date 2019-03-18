package com.example.munch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {
    private BottomNavigationView mMenu;

    private TextView mProfileNameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        mProfileNameTV = findViewById(R.id.tv_profile_name);
        mProfileNameTV.setText("Hello! This is your profile. Enjoy!");

        mMenu = findViewById(R.id.navigation_view);
        mMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_my_profile:
                                Intent intent2 = new Intent(getApplicationContext(), MyProfileActivity.class);
                                startActivity(intent2);
                                return true;
                            case R.id.navigation_my_home:
                                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent3);
                                return true;
                            case R.id.navigation_my_kitchen:
                                Intent intent = new Intent(getApplicationContext(), MyKitchenActivity.class);
                                startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });


    }

}

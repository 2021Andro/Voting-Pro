package com.example.votingpro.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.votingpro.Costume_Classes.MyApp;
import com.example.votingpro.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Thread.sleep(2*1000);

                    startActivity(new Intent(getApplicationContext(),Home_Activity.class));
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();



    }
}
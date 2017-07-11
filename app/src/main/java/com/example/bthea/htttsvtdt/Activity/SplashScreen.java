package com.example.bthea.htttsvtdt.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.bthea.htttsvtdt.R;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //creating thread that will sleep for 10 seconds
        Thread t = new Thread() {
            public void run() {

                try {
                    //sleep thread for 10 seconds, time in milliseconds
                    sleep(5000);

                    //start new activity
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);

                    //destroying Splash activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //start thread
        t.start();
    }
}

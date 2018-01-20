package com.example.subin.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private ImageView logo;
    private ImageView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = (ImageView) findViewById(R.id.logo_man);
        text = (ImageView) findViewById(R.id.logo_text);
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.my_transition);
        logo.startAnimation(myAnim);
        text.startAnimation(myAnim);

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }
}

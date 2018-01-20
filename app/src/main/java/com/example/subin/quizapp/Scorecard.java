package com.example.subin.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Scorecard extends AppCompatActivity {

    TextView computersTV, inventionsTV, sportsTV, scienceTV, generalTV;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        computersTV = (TextView) findViewById(R.id.computersScoreTV);
        inventionsTV = (TextView) findViewById(R.id.inventionsScoreTV);
        sportsTV = (TextView) findViewById(R.id.sportsScoreTV);
        scienceTV = (TextView) findViewById(R.id.scienceScoreTV);
        generalTV = (TextView) findViewById(R.id.generalScoreTV);

        backButton = (Button) findViewById(R.id.backButton);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        try {
            computersTV.setText("" + sharedPreferences.getInt("highScoreComputers", 0));
            inventionsTV.setText("" + sharedPreferences.getInt("highScoreSports", 0));
            sportsTV.setText("" + sharedPreferences.getInt("highScoreInventions", 0));
            scienceTV.setText("" + sharedPreferences.getInt("highScoreGeneral", 0));
            generalTV.setText("" + sharedPreferences.getInt("highScoreScience", 0));
        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        Intent intent=new Intent(getApplicationContext(),MenuScreen.class);
                        startActivity(intent);
                    }
                }, 1000);
            }
        });
    }
}

package com.example.subin.quizapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;

import javax.xml.transform.Result;


public class MenuScreen extends AppCompatActivity {

    Button computerButton, generalKnowledgeButton, inventionsButton, scienceButton, sportsButton, scorecardButton;

    public final static String Category = "com.example.subin.quizapp.CATEGORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        computerButton = (Button) findViewById(R.id.computerButton);
        generalKnowledgeButton = (Button) findViewById(R.id.generalKnowledgeButton);
        inventionsButton = (Button) findViewById(R.id.inventionsButton);
        scienceButton = (Button) findViewById(R.id.scienceButton);
        sportsButton = (Button) findViewById(R.id.sportsButton);
        scorecardButton = (Button) findViewById(R.id.scorecardButton);

        computerButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Preparing computer category questions",
                        Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        Intent intent=new Intent(getApplicationContext(),QuizActivity.class);
                        intent.putExtra(Category, "Computers");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        generalKnowledgeButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Preparing GK questions",
                        Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        Intent intent=new Intent(getApplicationContext(),QuizActivity.class);
                        intent.putExtra(Category, "General");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        inventionsButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Preparing inventions category questions",
                        Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        Intent intent=new Intent(getApplicationContext(),QuizActivity.class);
                        intent.putExtra(Category, "Inventions");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);

            }
        });

        scienceButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Preparing science category questions",
                        Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        Intent intent=new Intent(getApplicationContext(),QuizActivity.class);
                        intent.putExtra(Category, "Science");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        sportsButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Preparing sports category questions",
                        Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        Intent intent=new Intent(getApplicationContext(),QuizActivity.class);
                        intent.putExtra(Category, "Sports");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        scorecardButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Fetching your scores",
                        Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        Intent intent=new Intent(getApplicationContext(),Scorecard.class);
                        startActivity(intent);
                    }
                }, 1500);
            }
        });
    }
}

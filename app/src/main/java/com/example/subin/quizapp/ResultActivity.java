package com.example.subin.quizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView categoryTV, scoreTV, highScoreTV;
    Button finishButton;
    private ProgressDialog progressBar;

    public final static String Message = "com.example.subin.quizapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        categoryTV = (TextView) findViewById(R.id.categoryTV);
        scoreTV = (TextView) findViewById(R.id.scoreTV);
        highScoreTV = (TextView) findViewById(R.id.highScoreTV);
        finishButton = (Button) findViewById(R.id.finishButton);

        //read the values from previous activity and set the text views
        Intent intent = getIntent();
        String category = intent.getStringExtra(QuizActivity.Category); //receive string message from previous intent
        categoryTV.setText(category);

        int score = intent.getIntExtra(QuizActivity.Score, 0);
        scoreTV.setText("Your score: " + score);

        //use Shared preferences for storing and retrieving high score
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("highScore" + category,0);
        if(highScore > score){
            highScoreTV.setText("High Score: " + highScore);
        } else {
            highScoreTV.setText("New High Score: " + score);
            SharedPreferences.Editor spEditor = sharedPreferences.edit();
            spEditor.putInt("highScore" + category, score);
            spEditor.commit();
        }


        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(view.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage("Thank you...");//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 2 seconds

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent=new Intent(getApplicationContext(),MenuScreen.class);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
    }



}

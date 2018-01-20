package com.example.subin.quizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;

    AppCompatEditText usernameTV, emailTV, passwordTV;
    Button getStartedButton;
    private ProgressDialog progressBar;

    public final static String Message = "com.example.subin.quizapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTV = (AppCompatEditText) findViewById(R.id.userNameTV);
        emailTV = (AppCompatEditText) findViewById(R.id.emailTV);
        passwordTV = (AppCompatEditText) findViewById(R.id.passwordTV);
        getStartedButton = (Button) findViewById(R.id.getStartedButton);

        getStartedButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Get username, password from EditText
                String username = usernameTV.getText().toString();
                String password = passwordTV.getText().toString();

                // form validation
                if(username.trim().length() > 0){
                    if(password.trim().length() > 0){
                        // validate user and password
                        if(username.equals("team1") && password.equals("team1")){

//                            Toast.makeText(getApplicationContext(),
//                                    "Logging in",
//                                    Toast.LENGTH_SHORT).show();
//                            Thread myThread = new Thread(){
//                                @Override
//                                public void run(){
//                                    try{
//                                        sleep(1000);
//                                        Intent intent=new Intent(getApplicationContext(),MenuScreen.class);
//                                        startActivity(intent);
//                                        finish();
//                                    } catch (InterruptedException e){
//                                        e.printStackTrace();
//                                    }
//                                }
//                            };
//
//                            myThread.start();
                            //To show button click
                            new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                            progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                            progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                            progressBar.setMessage("Please wait... Logging in...");//Title shown in the progress bar
                            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                            progressBar.setProgress(0);//attributes
                            progressBar.setMax(100);//attributes
                            progressBar.show();//show the progress bar
                            //This handler will add a delay of 3 seconds
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Intent start to open the menu screen activity
                                    progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                                    Intent intent=new Intent(getApplicationContext(),MenuScreen.class);
                                    startActivity(intent);
                                }
                            }, 2000);

                        }else{

                            // username or password doesn't match&
                            Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();

                        }
                    }else{
                            // user didn't enter password
                            Toast.makeText(getApplicationContext(),
                                    "Please enter password",
                                    Toast.LENGTH_LONG).show();
                    }
                } else {

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username",
                            Toast.LENGTH_LONG).show();
                }

            };
        });
    }



    public void showHelp(View view) {
    }
}

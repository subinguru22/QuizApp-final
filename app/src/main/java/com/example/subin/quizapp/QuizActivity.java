package com.example.subin.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Result;

public class QuizActivity extends AppCompatActivity {

    TextView questionTV, questionNumberTV, showAnswerTV;
    Button option1Button, option2Button, option3Button, option4Button;

    String category;

    public final static String Category = "com.example.subin.quizapp.CATEGORY";
    public final static String Score = "com.example.subin.quizapp.SCORE";

    JSONArray questionsJsonArray;

    private Context context;
    private View view;

    private static int questionIndex = 0;
    int participantScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Initialize all view elements
        questionTV = (TextView) findViewById(R.id.questionTV);
        questionNumberTV = (TextView) findViewById(R.id.questionNumberTV);
        showAnswerTV = (TextView) findViewById(R.id.showAnswerTV);
        option1Button = (Button) findViewById(R.id.option1Button);
        option2Button = (Button) findViewById(R.id.option2Button);
        option3Button = (Button) findViewById(R.id.option3Button);
        option4Button = (Button) findViewById(R.id.option4Button);

        //Receive the category selected by the user
        Intent intent = getIntent();
        category = intent.getStringExtra(QuizActivity.Category); //receive string message from previous intent
        String quizCategory = category.toLowerCase();

        //Read the selected category file and load it it JSONObject and JSONArray
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(quizCategory, QuizActivity.this));   // call the read questions function
            JSONArray temp = obj.getJSONArray(quizCategory);
            //questionsJsonArray = shuffleJsonArray(temp);  // parse the json array, shuffle it and store them in questionsJsonArray
            questionsJsonArray = temp;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setQuestion(questionIndex++);   // set the question and increment the index by 1

        option1Button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call answer verify method
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkAnswer(questionIndex-1 ,"OptionA");
                        setQuestion(questionIndex++);      // set the next question
                    }
                }, 400);
            }
        });

        option2Button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call answer verify method
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkAnswer(questionIndex-1 ,"OptionB");
                        setQuestion(questionIndex++);       // set the next question
                    }
                }, 400);
            }
        });

        option3Button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call answer verify method
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkAnswer(questionIndex-1 , "OptionC");
                        setQuestion(questionIndex++);       // set the next question
                    }
                }, 400);
            }
        });

        option4Button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call answer verify method
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkAnswer(questionIndex-1 ,"OptionD");
                        setQuestion(questionIndex++);       // set the next question
                    }
                }, 400);
            }
        });
    }

    public String loadJSONFromAsset(String fileName, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONArray shuffleJsonArray(JSONArray array) throws JSONException {
        // Implementing Fisher–Yates shuffle
        Random rnd = new Random();
        for (int i = array.length() - 1; i >= 0; i--)
        {
            int j = rnd.nextInt(i + 1);
            // Simple swap
            Object object = array.get(j);
            array.put(j, array.get(i));
            array.put(i, object);
        }
        return array;
    }

    public JSONObject getQuestion(int index){
        try {
            return questionsJsonArray.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateQuestion(int index){
        JSONObject question = getQuestion(index);
        try {
            questionNumberTV.setText("Question " + (index+1));
            questionTV.setText(question.getString("Question"));
            option1Button.setText(question.getString("OptionA"));
            option2Button.setText(question.getString("OptionB"));
            option3Button.setText(question.getString("OptionC"));
            option4Button.setText(question.getString("OptionD"));
            showAnswerTV.setText("Pick your answer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(final int index){
        if(index < 20){
            //display small toast
            Toast.makeText(getApplicationContext(),
                    "Loading question",
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateQuestion(index);
                }
            },1500);
        } else {
            finish();
        }
    }

    public void checkAnswer(int index, String selectedOption){
        JSONObject question = getQuestion(index);
        try {
            String let = question.getString("Answer");
            String answer = question.getString("Option" + let);
            if(answer.equals(question.getString(selectedOption))){
                //showAnswerTV.setText("Correct Answer");
                showAnswerTV.setText("Your answer is correct" );
                participantScore++;
            } else {
                //showAnswerTV.setText("Your answer is incorrect");
                showAnswerTV.setText("Incorrect answer. Correct answer is: " + answer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void finish(){
        questionIndex = 1; // reset question number
        //display small toast
        Toast.makeText(getApplicationContext(),
                "Evaluating results",
                Toast.LENGTH_SHORT).show();

        //This handler will add a delay of 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent start to open the navigation drawer activity
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra(Category, category);//
                intent.putExtra(Score, participantScore);
                startActivity(intent);
            }
        }, 1500);
    }
}

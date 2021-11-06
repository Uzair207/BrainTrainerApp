package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button startButton;
TextView answerText;
TextView scoreText;
TextView timerText;
Button button1;
Button button2;
Button button3;
Button button4;
Random rand;
int score;
int a,b;
int noOfQuestions;
Button playagainButton;
Boolean hasGameEnded;
CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.start_button);
        answerText = findViewById(R.id.answerText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playagainButton = findViewById(R.id.play_again_button);
        rand = new Random();
        score = 0;
        noOfQuestions = 1;
        hasGameEnded = false;
        scoreText.setText(score+"/"+noOfQuestions);
        randomGameGenerator();
    }
    public void checkAnswer(View view){
        int result = a+b;
        Button b = (Button)view;
        if(noOfQuestions==20) {
            hasGameEnded = true;
        }
        if(hasGameEnded){
            playagainButton.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"You Got "+score+"Out of"+noOfQuestions,Toast.LENGTH_LONG).show();
        countDownTimer.cancel();
        timerText.setText("00"+"/"+"00");
        }
        else{
                if(b.getText().toString()==Integer.toString(result)){
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
                }
                noOfQuestions++;
                scoreText.setText(score + "/" + noOfQuestions);
                countDownTimer.cancel();
                randomGameGenerator();
            }
        }
    public void resetGame(View view){
        hasGameEnded = false;
        score = 0;
        noOfQuestions=1;
        scoreText.setText(score+"/"+noOfQuestions);
        playagainButton.setVisibility(View.INVISIBLE);
        randomGameGenerator();
    }

    public void startGame(View view){
        startButton.setVisibility(View.INVISIBLE);
    }
    public void randomGameGenerator(){
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        answerText.setText(Integer.toString(a) + "+" + Integer.toString(b));
        ArrayList<Integer> answersList = new ArrayList<Integer>();
        int index = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == index) {
                answersList.add(a + b);
            }
            else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answersList.add(wrongAnswer);
            }
        }
        button1.setText(answersList.get(0).toString());
        button2.setText(answersList.get(1).toString());
        button3.setText(answersList.get(2).toString());
        button4.setText(answersList.get(3).toString());

       countDownTimer =new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                long timeInMins = (millisUntilFinished/1000)/60;
                long timeInSecs = (millisUntilFinished/1000)%60;
                timerText.setText("0"+timeInMins+" : "+timeInSecs);
            }

            @Override
            public void onFinish() {
                if(noOfQuestions==20){
                    hasGameEnded = true;
                    timerText.setText("00:00");
                    playagainButton.setVisibility(View.VISIBLE);
                }
                else {
                    noOfQuestions++;
                    randomGameGenerator();
                }
                scoreText.setText(score+"/"+noOfQuestions);
            }
        }.start();
    }
}

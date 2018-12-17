package com.xencosworks.triv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String logText = "MainActivity";

    ScrollView mainScrollView;

    RadioGroup question1Input;
    RadioGroup question2Input;
    EditText question3Input;

    CheckBox question4Op1;
    CheckBox question4Op2;
    CheckBox question4Op3;
    CheckBox question4Op4;

    RadioGroup question5Ans;

    CheckBox question6Op1;
    CheckBox question6Op2;
    CheckBox question6Op3;
    CheckBox question6Op4;

    EditText question7Input;
    RadioGroup question8Input;

    View introCard;
    Button resetAnswersButton;
    Button showAnswersButton;
    Button submitAnswersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            getSupportActionBar().hide();
        }catch (NullPointerException e){
            Log.e(logText, "Null exception, error code: " + e.toString());
        }

        instantiatePublicViews();
        submitAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);
                submitAnswersButton.setVisibility(View.GONE);
                calculateScore();
            }
        });

        resetAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetInput();
                submitAnswersButton.setVisibility(View.VISIBLE);
                introCard.setVisibility(View.GONE);
            }
        });

        showAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswers();
                submitAnswersButton.setVisibility(View.GONE);
            }
        });
    }

    private void instantiatePublicViews(){
        mainScrollView = findViewById(R.id.scrollView_rootView);

        question1Input = findViewById(R.id.q1_input);
        question2Input = findViewById(R.id.q2_input);
        question3Input = findViewById(R.id.q3_input);

        question4Op1 = findViewById(R.id.q4_input1);
        question4Op2 = findViewById(R.id.q4_input2);
        question4Op3 = findViewById(R.id.q4_input3);
        question4Op4 = findViewById(R.id.q4_input4);

        question5Ans = findViewById(R.id.q5_input);

        question6Op1 = findViewById(R.id.q6_input1);
        question6Op2 = findViewById(R.id.q6_input2);
        question6Op3 = findViewById(R.id.q6_input3);
        question6Op4 = findViewById(R.id.q6_input4);

        question7Input = findViewById(R.id.q7_input);
        question8Input = findViewById(R.id.q8_input);

        introCard = findViewById(R.id.card_intro);
        showAnswersButton = findViewById(R.id.btn_show_answers);
        resetAnswersButton = findViewById(R.id.btn_reset_answers);
        submitAnswersButton = findViewById(R.id.btn_submit);
    }

    private void calculateScore(){
        int counter = 0;

        if (question1Input.getCheckedRadioButtonId() == R.id.q1_op3){
            counter+=1;
        }

        if(question2Input.getCheckedRadioButtonId() == R.id.q2_op2){
            counter+=1;
        }

        if(question3Input.getText().toString().equals("Pennsylvania") ||
                question3Input.getText().toString().equals("pennsylvania")){
            counter+=1;
        }

        if(!question4Op1.isChecked()&&question4Op2.isChecked()&&!question4Op3.isChecked()&&question4Op4.isChecked()){
            counter+=1;
        }

        if(question5Ans.getCheckedRadioButtonId() == R.id.q5_op3){
            counter+=1;
        }

        if(question6Op1.isChecked()&&question6Op2.isChecked()&&question6Op3.isChecked()&&question6Op4.isChecked()){
            counter+=1;
        }

        if(question7Input.getText().toString().equals("1989")){
            counter+=1;
        }

        if(question8Input.getCheckedRadioButtonId() == R.id.q8_op3){
            counter+=1;
        }


        if(counter==8){
            Toast.makeText(this, "Congrat! You got 'em all! Answers was reset for a new game.", Toast.LENGTH_LONG).show();
            resetInput();
        }else{
            Toast.makeText(this, "You're score was "+ counter + " out of 8", Toast.LENGTH_LONG).show();
            introCard.setVisibility(View.VISIBLE);
        }
    }

    private void showAnswers(){
        question1Input.check(R.id.q1_op3);

        question2Input.check(R.id.q2_op2);

        question3Input.setText(R.string.java_ans_pennsylvania);

        question4Op1.setChecked(false);
        question4Op2.setChecked(true);
        question4Op3.setChecked(false);
        question4Op4.setChecked(true);

        question5Ans.check(R.id.q5_op3);

        question6Op1.setChecked(true);
        question6Op2.setChecked(true);
        question6Op3.setChecked(true);
        question6Op4.setChecked(true);

        question7Input.setText(R.string.java_ans_1989);

        question8Input.check(R.id.q8_op3);


        Toast.makeText(this, "Correct answers are currently shown", Toast.LENGTH_SHORT).show();
    }

    private void resetInput(){
        question1Input.clearCheck();

        question2Input.clearCheck();

        question3Input.setText("");

        question4Op1.setChecked(false);
        question4Op2.setChecked(false);
        question4Op3.setChecked(false);
        question4Op4.setChecked(false);

        question5Ans.clearCheck();

        question6Op1.setChecked(false);
        question6Op2.setChecked(false);
        question6Op3.setChecked(false);
        question6Op4.setChecked(false);

        question7Input.setText("");

        question8Input.clearCheck();
    }

}

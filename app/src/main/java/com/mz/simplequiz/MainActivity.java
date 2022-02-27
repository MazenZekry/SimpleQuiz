package com.mz.simplequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> questionList;
    TextView tv_question_number,tv_question,tv_degree;
    RadioButton rb_answer1,rb_answer2,rb_answer3;
    RadioGroup radioGroup;
    Button btn_nextQuestion;
    int question_counter=0,degree=0,totalQuestion;
    Question currentQuestion;
    boolean flag = true;
    RadioButton radioButtonT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionList=new ArrayList<>();
        findViewById();
        addQuestion();
        totalQuestion=questionList.size();
        showQuestion();
        btn_nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    if (flag)
                    checkAnswer();
                    else
                    showQuestion();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Please choose Your answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showQuestion()
    {
        radioGroup.clearCheck();
        flag = true;
        if(question_counter<totalQuestion)
        {
            currentQuestion=questionList.get(question_counter);
            tv_question.setText(currentQuestion.getQuestion());
            rb_answer1.setText(currentQuestion.getAnswer1());
            rb_answer2.setText(currentQuestion.getAnswer2());
            rb_answer3.setText(currentQuestion.getAnswer3());
            question_counter++;
            tv_question_number.setText("Question no: "+question_counter);
            if (question_counter != 1)
                radioButtonT.setTextColor(Color.BLACK);
                btn_nextQuestion.setText("CHECK");
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                radioGroup.getChildAt(i).setEnabled(true);
            }
        }
    }
    private void checkAnswer(){
        radioButtonT=findViewById(radioGroup.getCheckedRadioButtonId());//بتساوي الاجابه الي انت اخترتها فال variable دا
        int answerNo=radioGroup.indexOfChild(radioButtonT)+1;
        if (answerNo==currentQuestion.getCorrectAnswer_no())
        {
            radioButtonT.setTextColor(Color.GREEN);
            degree++;
        }
        else{
            radioButtonT.setTextColor(Color.RED);
        }
        if(question_counter==totalQuestion) {
            findViewById(R.id.result_container).setVisibility(View.VISIBLE);
            findViewById(R.id.question_container).setVisibility(View.GONE);
            tv_degree.setText("Your Final Degree is : "+degree+"/"+totalQuestion);
        }
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
        flag = false;
        btn_nextQuestion.setText("Next Question");
    }


    private void addQuestion()
    {
        Question question1=new Question("derives the logically necessary conclusion from the given premises ? ","Deductive reasoning",
                "Inductive reasoning","Reasoning",1);
        questionList.add(question1);

        Question question2=new Question("What does ASCII stand for ? ","American Scientific Code for Information Interchange",
                "American Standard Code for Information Interchange"
                ,"American Scientific Code for Interchanging Information"
                ,2);
        questionList.add(question2);

        Question question3=new Question("Colour depth is : ","how many different colours for each pixel",
                "how many colour in the image","how many colour in inch",1);
        questionList.add(question3);

        Question question4=new Question("One of the golden rule in design: ","requirements",
                "analysis","understand computers",2);
        questionList.add(question4);

        Question question5=new Question("Human can hear frequencies from: ","15 HZ to 20 HZ ",
                "15 KHZ to 20 KHZ","15 HZ to 20 KHZ",1);
        questionList.add(question5);

    }
    private void findViewById()
    {
        tv_question=findViewById(R.id.tv_question);
        tv_question_number=findViewById(R.id.tv_question_number);
        rb_answer1=findViewById(R.id.rb_answer1);
        rb_answer2=findViewById(R.id.rb_answer2);
        btn_nextQuestion=findViewById(R.id.btn_nextQuestion);
        radioGroup=findViewById(R.id.radioGroup);
        rb_answer3=findViewById(R.id.rb_answer3);
        tv_degree=findViewById(R.id.tv_degree);
    }

}
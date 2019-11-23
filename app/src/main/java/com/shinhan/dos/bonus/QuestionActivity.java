package com.shinhan.dos.bonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    TextView question_tyoe_txt;
    TextView explain_field;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        question_tyoe_txt = (TextView)findViewById(R.id.question_type_txt);
        explain_field = (TextView)findViewById(R.id.explain_field);

        String stockEx = "주식설명이고요 \n\n\n\n\n\n 주식 \n\n\n\nrlskfjw \n\n\nlkfjsdf \nweflkj\nwelkfjew  ";
        String insuranceEx = "보험설명이고요 \n\n\n\n\n\n 주식 \n\n\n\nrlskfjw \n\n\nlkfjsdf \nweflkj\nwelkfjew  ";


        intent = getIntent();
        String name = intent.getExtras().getString("to"); /*String형*/


        if(name.equals("주식")){
            question_tyoe_txt.setText("주식으로 공제받기");
            explain_field.setText(stockEx);

        }else if(name.equals("보험")){
            question_tyoe_txt.setText("보험으로 공제받기");
            explain_field.setText(insuranceEx);
        }


    }
}

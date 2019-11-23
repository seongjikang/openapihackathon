package com.shinhan.dos.bonus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifySalaryActivity extends AppCompatActivity {

    private String mName;
    private String mHpno;
    private String mSalary;

    private EditText etSalary;
    private Button btnGoNext;
    private TextView tvName;
    private TextView tvBoxName;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_salary);

        pref = getSharedPreferences("pref", MODE_PRIVATE);

        mName = getIntent().getStringExtra("name");
        mHpno = getIntent().getStringExtra("hpno");

        initView();
    }

    private void initView() {
        etSalary = findViewById(R.id.et_salary);
        btnGoNext = findViewById(R.id.btn_go_next);
        tvName = findViewById(R.id.tv_name);
        tvBoxName = findViewById(R.id.tv_msg_box_name);

        tvName.setText(mName);
        tvBoxName.setText(mName);

        btnGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salary = etSalary.getText().toString();
                mSalary = salary;
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("salary", mSalary);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class  );
                intent.putExtra("salary", mSalary);
                intent.putExtra("name", mName);
                intent.putExtra("hpno", mHpno);
                startActivity(intent);
            }
        });
    }
}

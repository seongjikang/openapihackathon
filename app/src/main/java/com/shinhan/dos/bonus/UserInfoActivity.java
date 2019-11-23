package com.shinhan.dos.bonus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class UserInfoActivity extends AppCompatActivity {

    private static final String TAG = "UserInfoActivity";
    private SharedPreferences pref;

    private RelativeLayout rlMsgBox;
    private LinearLayout llMsgBox01;
    private LinearLayout llMsgBox02;

    private TextView tvName;
    private TextView tvSalary;
    private Button btnModifySalary;
    private Button btnGoMain;
    private ImageView ivArrow;

    private String mHpno;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initView();

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        mHpno = pref.getString("hpno", "");

        if ("".equals(mHpno)) {
            Log.d(TAG, "hpno is null");
            if (ActivityCompat.checkSelfPermission(this, READ_SMS) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                TelephonyManager tMgr = (TelephonyManager)   this.getSystemService(Context.TELEPHONY_SERVICE);
                String mPhoneNumber = tMgr.getLine1Number();
                Log.d(TAG, "1 hpno = " + mPhoneNumber);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("hpno", mPhoneNumber);
                editor.commit();
                mHpno = mPhoneNumber;
                return;
            } else {
                requestPermission();
            }
        } else {
            Log.d(TAG, "hpno is not null");
            getName(mHpno);
        }
    }

    private void initView() {
        rlMsgBox = findViewById(R.id.rl_msg_box);
        llMsgBox01 = findViewById(R.id.ll_msg_02);
        llMsgBox02 = findViewById(R.id.ll_msg_03);

        tvName = findViewById(R.id.tv_name1);
        tvSalary = findViewById(R.id.tv_salary);
        btnModifySalary = findViewById(R.id.btn_modify_salary);
        btnGoMain = findViewById(R.id.btn_go_main);
        ivArrow = findViewById(R.id.iv_arrow);

        rlMsgBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSalary(getmHpno());
            }
        });

        btnModifySalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModifySalaryActivity.class);
                intent.putExtra("name", mName);
                intent.putExtra("hpno", mHpno);
                startActivity(intent);
            }
        });

        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    public String getmHpno() {
        return mHpno;
    }

    private void getName(String hpno) {
        Map params = new LinkedHashMap<>();
        params.put("hpno", hpno);
        DataResult dataResult = new DataResultImpl();
        dataResult.getName(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//				response.body.get("dataBody").getAsJsonObject().get("insuranceList").getAsJsonArray().get(0)
                Log.d(TAG, "resp = " + response.toString());
                Log.d(TAG, "resp = " + response.body().get("name"));
                String name = response.body().get("name").toString().replace("\"", "");
                mName = name;
                if (!"".equals(name)) tvName.setText(name);

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("name", mName);
                editor.commit();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "resp = FAIL....");
            }
        }, params);
    }

    private void getSalary(String hpno) {
        Map params = new LinkedHashMap<>();
        params.put("hpno", hpno);
        DataResult dataResult = new DataResultImpl();
        dataResult.getSalaryAmt(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                Log.d(TAG, "resp = " + response.toString());
//                Log.d(TAG, "resp = " + response.body().toString());
                String amt = response.body().get("dataBody").getAsJsonObject().get("salaryAmt").toString();
//                Log.d(TAG, "resp = " + amt);

                DecimalFormat formatter = new DecimalFormat("###,###");
                String formattedAmt = formatter.format(Integer.parseInt(amt));
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("salary", amt);
                editor.commit();

                llMsgBox01.setVisibility(View.GONE);
                llMsgBox02.setVisibility(View.VISIBLE);
                tvSalary.setText(formattedAmt);
                ivArrow.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "resp = FAIL....");
            }
        }, params);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, 100);
        }
    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                String mPhoneNumber = tMgr.getLine1Number();
                Log.d(TAG, "2 hpno = " + mPhoneNumber);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("hpno", mPhoneNumber);
                editor.commit();
                mHpno = mPhoneNumber;
                break;
        }
    }
}

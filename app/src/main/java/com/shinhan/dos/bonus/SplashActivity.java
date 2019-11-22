package com.shinhan.dos.bonus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

	private static final String TAG = "SplashActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		launchApp();
	}

	private void launchApp() {
		checkPermissions();
		getFinancialInfo();
	}

	// TODO : 권한체크
	private void checkPermissions() {
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setMessage(R.string.set_permission);
	}

	// 사용자정보 불러오기
	private void getFinancialInfo() {
		Map params = new LinkedHashMap<>();
		params.put("hpno", "01071444074");
		DataResult dataResult = new DataResultImpl();
		dataResult.getLifeUsage(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//				response.body.get("dataBody").getAsJsonObject().get("insuranceList").getAsJsonArray().get(0)
				Log.d(TAG, response.toString());

				goToMainActivity();
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d(TAG, "FAIL....");

				goToMainActivity();
			}
		}, params);
	}

	private void goToMainActivity() {
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}

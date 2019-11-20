package com.shinhan.dos.bonus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

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

	// TODO : DB 또는 사용자정보 불러오기
	private void getFinancialInfo() {
		// on result
		goToMainActivity();
	}

	private void goToMainActivity() {
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}

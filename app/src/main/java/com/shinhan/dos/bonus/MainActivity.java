package com.shinhan.dos.bonus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private BottomSheetBehavior mBottomSheetBehavior;

	private NestedScrollView bottomSheet;
	private ProgressBar mProgressBar;
	private TextView mTvPercent;
	private LinearLayout mLlBottomFold;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setPercent(80);
	}

	// API 호출 샘플임!! 카드
	private void callCardApi() {
		Map params = new LinkedHashMap<>();
		params.put("hpno", "01071444074");
		DataResult dataResult = new DataResultImpl();
		dataResult.getCardUsage(new Callback<Map>() {
			@Override
			public void onResponse(Call<Map> call, Response<Map> response) {
				Log.d(TAG, response.toString());
			}

			@Override
			public void onFailure(Call<Map> call, Throwable t) {
				Log.d(TAG, "FAIL....");
			}
		}, params);
	}

	private void initView() {
		bottomSheet = findViewById(R.id.bottom_sheet);
		mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
		/*mBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {
				switch (newState) {
					case BottomSheetBehavior.STATE_EXPANDED:
						// TODO : 주석풀고 Animation
						bottomSheet.scrollTo(0, mLlBottomFold.getHeight());
						break;
				}
			}

			@Override
			public void onSlide(@NonNull View bottomSheet, float slideOffset) {

			}
		});*/

		mProgressBar = findViewById(R.id.progress_bar);
		mTvPercent = findViewById(R.id.tv_percent);
		mLlBottomFold = findViewById(R.id.ll_bottom_fold);
	}

	private void setPercent(int percent) {
		mProgressBar.setProgress(percent);
		mTvPercent.setText(percent + "");
	}
}

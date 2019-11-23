package com.shinhan.dos.bonus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.JsonObject;
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

	private void initView() {
		bottomSheet = findViewById(R.id.bottom_sheet);
		mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

		mProgressBar = findViewById(R.id.progress_bar);
		mTvPercent = findViewById(R.id.tv_percent);
		mLlBottomFold = findViewById(R.id.ll_bottom_fold);

		mBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {
				switch (newState) {
					case BottomSheetBehavior.STATE_EXPANDED:
						break;
					case BottomSheetBehavior.STATE_DRAGGING:
						break;
					case BottomSheetBehavior.STATE_COLLAPSED:
						break;
				}
			}

			@Override
			public void onSlide(@NonNull View bottomSheet, float slideOffset) {
			}
		});

		final LinearLayout inner = findViewById(R.id.ll_middle_buttons);
		final LinearLayout inner2 = findViewById(R.id.ll_top_total);
		inner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				inner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				int height = getScreenSize() - getStatusBarHeight();
				mBottomSheetBehavior.setPeekHeight((int) (height - (inner.getHeight() + inner2.getHeight() + height*0.13)));
			}
		});
	}

	private void setPercent(int percent) {
		mProgressBar.setProgress(percent);
		mTvPercent.setText(percent + "");
	}

	private int getScreenSize() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int height = displayMetrics.heightPixels;
		int width = displayMetrics.widthPixels;

		return height;
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}

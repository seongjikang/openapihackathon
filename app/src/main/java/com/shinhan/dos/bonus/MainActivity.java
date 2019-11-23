package com.shinhan.dos.bonus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.Insurance.InsuranceInquiryActivity;
import com.shinhan.dos.bonus.PlusMoney.PlusMoneyActivity;
import com.shinhan.dos.bonus.Stock.StockInquiryActivity;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private static final String TAG = "MainActivity";
	private BottomSheetBehavior mBottomSheetBehavior;

	private NestedScrollView bottomSheet;
	private ProgressBar mProgressBar;
	private TextView mTvPercent;
	private LinearLayout mLlBottomFold;
	private LinearLayout mLlTopTotal;
	private LinearLayout mLlMiddleGps;
	private LinearLayout mLlMiddleStock;
	private LinearLayout mLlMiddleInsurance;
	private LinearLayout ll_list_card;
	private LinearLayout ll_list_stock;
	private LinearLayout ll_list_insurance;
	private LinearLayout ll_list_bus;
	private LinearLayout ll_list_market;
	private LinearLayout ll_list_irp;
	private LinearLayout ll_list_house;
	private LinearLayout ll_list_fund;

	private ImageView iv_input_my;

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
		mLlTopTotal = findViewById(R.id.ll_top_total);

		mLlMiddleGps = findViewById(R.id.ll_middle_gps);
		mLlMiddleStock = findViewById(R.id.ll_middle_stock);
		mLlMiddleInsurance = findViewById(R.id.ll_middle_insurance);
		ll_list_card = findViewById(R.id.ll_list_card);
		ll_list_stock = findViewById(R.id.ll_list_stock);
		ll_list_insurance = findViewById(R.id.ll_list_insurance);
		ll_list_bus = findViewById(R.id.ll_list_bus);
		ll_list_market = findViewById(R.id.ll_list_market);
		ll_list_irp = findViewById(R.id.ll_list_irp);
		ll_list_house = findViewById(R.id.ll_list_house);
		ll_list_fund = findViewById(R.id.ll_list_fund);

		iv_input_my = findViewById(R.id.iv_input_my_money);

		mLlTopTotal.setOnClickListener(this);
		mLlMiddleGps.setOnClickListener(this);
		mLlMiddleStock.setOnClickListener(this);
		mLlMiddleInsurance.setOnClickListener(this);
		ll_list_card.setOnClickListener(this);
		ll_list_stock.setOnClickListener(this);
		ll_list_insurance.setOnClickListener(this);
		ll_list_bus.setOnClickListener(this);
		ll_list_market.setOnClickListener(this);
		ll_list_irp.setOnClickListener(this);
		ll_list_house.setOnClickListener(this);
		ll_list_fund.setOnClickListener(this);
		iv_input_my.setOnClickListener(this);

		final LinearLayout inner = findViewById(R.id.ll_middle_buttons);
		final LinearLayout inner2 = findViewById(R.id.ll_top_total);
		inner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				inner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				int height = getScreenSize() - getStatusBarHeight();
				mBottomSheetBehavior.setPeekHeight((int) (height - (inner.getHeight() + inner2.getHeight() + height * 0.13)));
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

	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		Intent intent = null;

		switch (viewId) {
			case R.id.ll_top_total:
				/*intent = new Intent(MainActivity.this, PlusMoneyActivity.class);
				startActivity(intent);*/
				break;
			case R.id.ll_middle_gps:
				// TODO
				intent = new Intent(MainActivity.this, TraditionalMarketActivity.class);
				startActivity(intent);
				break;
			case R.id.ll_middle_stock:
				intent = new Intent(MainActivity.this, StockInquiryActivity.class);
				startActivity(intent);
				break;
			case R.id.ll_middle_insurance:
				intent = new Intent(MainActivity.this, InsuranceInquiryActivity.class);
				startActivity(intent);
				break;
			case R.id.ll_list_card:
				intent = new Intent(MainActivity.this, PlusMoneyActivity.class);
				startActivity(intent);
				break;
			case R.id.ll_list_stock:
				intent = new Intent(MainActivity.this, PlusMoneyActivity.class);
				startActivity(intent);
				break;
			case R.id.ll_list_insurance:
				intent = new Intent(MainActivity.this, PlusMoneyActivity.class);
				startActivity(intent);
				break;
			case R.id.ll_list_bus:
				// TODO
				/*intent = new Intent(MainActivity.this, InsuranceInquiryActivity.class);
				startActivity(intent);*/
				break;
			case R.id.ll_list_market:
				// TODO
				/*intent = new Intent(MainActivity.this, InsuranceInquiryActivity.class);
				startActivity(intent);*/
				break;
			case R.id.ll_list_fund:
				// TODO
				/*intent = new Intent(MainActivity.this, InsuranceInquiryActivity.class);
				startActivity(intent);*/
				break;
			case R.id.ll_list_house:
				// TODO
				/*intent = new Intent(MainActivity.this, InsuranceInquiryActivity.class);
				startActivity(intent);*/
				break;
			case R.id.ll_list_irp:
				// TODO
				/*intent = new Intent(MainActivity.this, InsuranceInquiryActivity.class);
				startActivity(intent);*/
				break;
			case R.id.iv_input_my_money:
				startActivity(new Intent(this, UserInfoActivity.class));
				break;
		}
	}
}

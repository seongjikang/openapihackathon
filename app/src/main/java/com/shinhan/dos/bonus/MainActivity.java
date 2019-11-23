package com.shinhan.dos.bonus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
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

import java.text.DecimalFormat;
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
	//	private LinearLayout ll_list_fund;
	private View progress_default;

	private float mTotalMyDeduction = 0; // 공제총액
	private float mTotalMyDeductionLimits = 0; // 공제가능금액

	private String mCustomerName = "";
	private int mSalary = 0;
	private String mHpno = "";
	private int mCreditAmount = 0;// 신용카드사용금액
	private int mCheckAmount = 0;// 체크카드사용금액
	private int cultureTotalAmount = 0; // 전통시장
	private int publicTransferTotalAmount = 0; // 버스
	private int deductInsuranceTotalAmount = 0; // 보장성 보험 납입금액
	private int fundTotalAmount = 0; // 펀드
	private int investTotalAmount = 0; // 주식
	private float yumTotalAmount = 0; // 연금저축
	private int irpTotalAmount = 0; // IRP
	private int chungyakTotalAmount = 0; // 청약

	private JsonObject mResultData;

	private int progressWidth = 0;

	private ImageView iv_input_my;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("initUser", false);
		editor.commit();

		String salaryString = pref.getString("salary", "4000");
		if (salaryString != null && salaryString.length() > 0) {
			mSalary = Integer.parseInt(salaryString);
		}

		mHpno = pref.getString("hpno", "01071444074");
		if (mHpno == null) {
			mHpno = "01071444074";
		}
		mCustomerName = pref.getString("name", "최리나");
		if (mCustomerName == null) {
			mCustomerName = "";
		}

		initView();
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
//		ll_list_stock = findViewById(R.id.ll_list_stock);
		ll_list_insurance = findViewById(R.id.ll_list_insurance);
		ll_list_bus = findViewById(R.id.ll_list_bus);
		ll_list_market = findViewById(R.id.ll_list_market);
		ll_list_irp = findViewById(R.id.ll_list_irp);
		ll_list_house = findViewById(R.id.ll_list_house);
//		ll_list_fund = findViewById(R.id.ll_list_fund);
		progress_default = (View) findViewById(R.id.progress_default);
		((TextView) findViewById(R.id.tv_total_money)).setText(commaMoney(mSalary) + "");
		((TextView) findViewById(R.id.tv_user_name)).setText(mCustomerName);

		iv_input_my = findViewById(R.id.iv_input_my_money);

		mLlTopTotal.setOnClickListener(this);
		mLlMiddleGps.setOnClickListener(this);
		mLlMiddleStock.setOnClickListener(this);
		mLlMiddleInsurance.setOnClickListener(this);
		ll_list_card.setOnClickListener(this);
//		ll_list_stock.setOnClickListener(this);
		ll_list_insurance.setOnClickListener(this);
		ll_list_bus.setOnClickListener(this);
		ll_list_market.setOnClickListener(this);
		ll_list_irp.setOnClickListener(this);
		ll_list_house.setOnClickListener(this);
//		ll_list_fund.setOnClickListener(this);
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

		progress_default.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				progress_default.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				progressWidth = progress_default.getWidth();

				requestAllInfo();
			}
		});

	}

	private void requestAllInfo() {
		DataResult dataResult = new DataResultImpl();
		Map params = new LinkedHashMap<>();
		params.put("hpno", mHpno); // TODO Shared Preference에서 get
		dataResult.getMainInfo(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				Log.d(TAG, response.body().toString());

				mResultData = response.body().getAsJsonObject().get("dataBody").getAsJsonObject();
				if (mResultData != null) {
					setCardAndMoneyResult();
				}
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d(TAG, "FAIL..");
			}
		}, params);
	}

	private void setCirclePercent(int percent) {
		mProgressBar.setProgress(percent);
		mTvPercent.setText(percent + "");
	}

	private void setTotalAmoumts() {
		((TextView) findViewById(R.id.tv_total_deduction)).setText(commaMoney(Math.round(mTotalMyDeduction)) + " 만원"); // 총 공제금액
		((TextView) findViewById(R.id.tv_available_deduction_amt)).setText(commaMoney(Math.round(mTotalMyDeductionLimits)) + " 만원"); // 공제가능금액

		float rate = (float) ((float) mTotalMyDeduction / (float) mTotalMyDeductionLimits);
		int percent = Math.round(rate * 100);
		setCirclePercent(percent);
	}

	/**
	 * 카드 & 현금 공제
	 */
	private void setCardAndMoneyResult() {
		setUserMoneyInfo();

		drawFirstMoneyView();
		drawSecondMoneyView();
		drawThirdMoneyView();
//		drawStockView();
		drawBusView();
		drawInsuranceView();
		drawMarketView();
		drawIrpView();
		drawHouseView();
//		drawFundView();

		setTotalAmoumts();
	}

	private void setUserMoneyInfo() {
		String phoneNumber = mResultData.get("phoneNumber").getAsString();
		mCheckAmount = Integer.parseInt(mResultData.get("checkCardTotalAmount").getAsString());
		mCreditAmount = Integer.parseInt(mResultData.get("creditCardTotalAmount").getAsString()) + Integer.parseInt(mResultData.get("cashTotalAmount").getAsString());

		cultureTotalAmount = Integer.parseInt(mResultData.get("cultureTotalAmount").getAsString());
		publicTransferTotalAmount = Integer.parseInt(mResultData.get("publicTransferTotalAmount").getAsString());
		deductInsuranceTotalAmount = Integer.parseInt(mResultData.get("deductInsuranceTotalAmount").getAsString());
		fundTotalAmount = Integer.parseInt(mResultData.get("fundTotalAmount").getAsString());
		investTotalAmount = Integer.parseInt(mResultData.get("investTotalAmount").getAsString());
		yumTotalAmount = Float.parseFloat(mResultData.get("yumTotalAmount").getAsString());
		irpTotalAmount = Integer.parseInt(mResultData.get("IRPTotalAmount").getAsString());
		chungyakTotalAmount = Integer.parseInt(mResultData.get("chungyakTotalAmount").getAsString());
	}

	private void drawFirstMoneyView() {
		float maxMoney = 0;
		int dataMoney = 0;

		if (mSalary >= 15000000) {
			maxMoney = (float) (mSalary * 0.25);
		} else {
			maxMoney = (float) (mSalary * 0.2);
		}

		dataMoney = mCreditAmount + mCheckAmount;

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt1)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_card1)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_card1)).requestLayout();
			((TextView) findViewById(R.id.tv_yes_credit_card)).setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.tv_no_credit_card)).setVisibility(View.GONE);
		} else {
			((TextView) findViewById(R.id.tv_yes_credit_card)).setVisibility(View.GONE);
			((TextView) findViewById(R.id.tv_no_credit_card)).setVisibility(View.VISIBLE);
		}
		((TextView) findViewById(R.id.tv_max1)).setText(" / " + moneyToManwon(maxMoney));

	}

	private void drawSecondMoneyView() {
		int maxMoney = 0;
		int dataMoney = 0;
		float checkMoney = 0;

		maxMoney = mSalary;

		if (mSalary >= 15000000) {
			checkMoney = (float) (mSalary * 0.25);
		} else {
			checkMoney = (float) (mSalary * 0.2);
		}

		float percentCheck = (float) checkMoney / maxMoney;
		if (percentCheck < 1.0) {
			((View) findViewById(R.id.progress_card2_dis)).getLayoutParams().width = (int) (progressWidth * percentCheck);
		}

//		dataMoney = 신용카드사용금액 + 체크카드사용금액 + 현금이용금액(서버)
		dataMoney = mCreditAmount + mCheckAmount;

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt2)).setText(moneyToManwon(dataMoney) + "");

		if (percent < 1.0) {
			((View) findViewById(R.id.progress_card2)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_card2)).requestLayout();
		}

		if (checkMoney > dataMoney) {
			((TextView) findViewById(R.id.tv_can)).setVisibility(View.GONE);
			((TextView) findViewById(R.id.tv_cant)).setText(moneyToManwon(maxMoney - dataMoney) + ((TextView) findViewById(R.id.tv_cant)).getText().toString());
			((TextView) findViewById(R.id.tv_cant)).setVisibility(View.VISIBLE);
			((LinearLayout) findViewById(R.id.ll_third2)).setVisibility(View.GONE);
			((LinearLayout) findViewById(R.id.ll_third1)).setVisibility(View.GONE);
		} else {
			((TextView) findViewById(R.id.tv_cant)).setVisibility(View.GONE);
			((TextView) findViewById(R.id.tv_can)).setVisibility(View.VISIBLE);
			((LinearLayout) findViewById(R.id.ll_third2)).setVisibility(View.VISIBLE);
			((LinearLayout) findViewById(R.id.ll_third1)).setVisibility(View.VISIBLE);
		}
		((TextView) findViewById(R.id.tv_max2)).setText(" / " + moneyToManwon(maxMoney) + "");
	}

	private void drawThirdMoneyView() {
		int maxMoney = 0;
		float dataMoney1 = 0;
		float dataMoney2 = 0;

		if (mSalary <= 70000000) {
			maxMoney = 3000000;
		} else if (mSalary > 7000 && mSalary <= 12000) {
			maxMoney = 2500000;
		} else {
			maxMoney = 2000000;
		}

//		dataMoney1 = 신용카드사용금액 * 0.15
		dataMoney1 = (float) (mCreditAmount * 0.15);
//		dataMoney2 = 체크카드 및 현금 사용금액 * 0.30
		dataMoney2 = (float) (mCheckAmount * 0.30);

		float percent1 = (float) dataMoney1 / maxMoney;
		float percent2 = (float) dataMoney2 / maxMoney;

		((View) findViewById(R.id.progress_card4)).getLayoutParams().width = (int) (progressWidth * percent1);
		((View) findViewById(R.id.progress_card4)).requestLayout();

		if ((percent1 + percent2) <= 1.0) {
			((View) findViewById(R.id.progress_card3)).getLayoutParams().width = (int) (progressWidth * (percent1 + percent2));
			((View) findViewById(R.id.progress_card3)).requestLayout();
			((TextView) findViewById(R.id.tv_amt3)).setText(moneyToManwon(dataMoney1 + dataMoney2) + "");
//			((TextView) findViewById(R.id.tv_amt3)).setText(moneyToManwon(dataMoney1) + ", " + moneyToManwon(dataMoney2));
			mTotalMyDeduction += moneyToManwon(dataMoney1 + dataMoney2);
		} else {
			((TextView) findViewById(R.id.tv_amt3)).setText(moneyToManwon(maxMoney) + "");
			mTotalMyDeduction += moneyToManwon(maxMoney);
		}
		((TextView) findViewById(R.id.tv_max3)).setText(" / " + moneyToManwon(maxMoney) + "");

		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}
/*

	private void drawStockView() {
		float maxMoney = 0;
		float rate = 0;
		float dataMoney = investTotalAmount;

		if (dataMoney <= 30000000) {
			rate = 1;
			maxMoney = dataMoney * rate;
		} else if (dataMoney > 30000000 && dataMoney <= 50000000) {
			rate = (float) 0.7;
			maxMoney = dataMoney * rate;
		} else {
			rate = (float) 0.3;
			maxMoney = dataMoney * rate;
		}
		dataMoney = dataMoney * rate;

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt_stock)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_stock)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_stock)).requestLayout();
//			mTotalMyDeduction += moneyToManwon(dataMoney);
		} else {
			((TextView) findViewById(R.id.tv_amt_stock)).setText(moneyToManwon(maxMoney) + "");
//			mTotalMyDeduction += moneyToManwon(maxMoney);
		}

		((TextView) findViewById(R.id.tv_max_stock)).setText(" / " + moneyToManwon(maxMoney));
//		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}
*/

	private void drawInsuranceView() {
		float maxMoney = (float) (1000000 * 0.132);
		float dataMoney = (float) (deductInsuranceTotalAmount * 0.132);

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt_insurance)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_insurance)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_insurance)).requestLayout();
			mTotalMyDeduction += moneyToManwon(dataMoney);
		} else {
			((TextView) findViewById(R.id.tv_amt_insurance)).setText(moneyToManwon(maxMoney) + "");
			mTotalMyDeduction += moneyToManwon(maxMoney);
		}

		((TextView) findViewById(R.id.tv_max_insurance)).setText(" / " + moneyToManwon(maxMoney));
		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}

	private void drawBusView() {
		float maxMoney = (float) (1000000 * 0.4);
		float dataMoney = (float) (publicTransferTotalAmount * 0.4);

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt_bus)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_bus)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_bus)).requestLayout();
			mTotalMyDeduction += moneyToManwon(dataMoney);
		} else {
			((TextView) findViewById(R.id.tv_amt_bus)).setText(moneyToManwon(maxMoney) + "");
			mTotalMyDeduction += moneyToManwon(maxMoney);
		}

		((TextView) findViewById(R.id.tv_max_bus)).setText(" / " + moneyToManwon(maxMoney));
		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}

	private void drawMarketView() {
		float maxMoney = (float) (1000000 * 0.4);
		float dataMoney = (float) (cultureTotalAmount * 0.4);

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt_market)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_market)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_market)).requestLayout();
			mTotalMyDeduction += moneyToManwon(dataMoney);
		} else {
			((TextView) findViewById(R.id.tv_amt_market)).setText(moneyToManwon(maxMoney) + "");
			mTotalMyDeduction += moneyToManwon(maxMoney);
		}

		((TextView) findViewById(R.id.tv_max_market)).setText(" / " + moneyToManwon(maxMoney));
		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}

	private void drawIrpView() {
		float maxMoney = 0;
		float dataMoney = 0;
		float dataMoneyIrp = irpTotalAmount;
		float dataMoneyYum = yumTotalAmount;
		float rate = 0;
		// 연금
		dataMoney = yumTotalAmount;
		if (dataMoney <= 55000000) {
			rate = (float) 0.165;
			maxMoney = (float) (4000000 * rate);
		} else if (dataMoney > 55000000 && dataMoney <= 120000000) {
			rate = (float) 0.132;
			maxMoney = (float) (3000000 * rate);
		} else {
			rate = (float) 0.132;
			maxMoney = (float) (3000000 * rate);
		}

		// IRP
		if (irpTotalAmount > yumTotalAmount) {
			dataMoney = irpTotalAmount;
			if (dataMoney <= 55000000) {
				rate = (float) 0.165;
				maxMoney = (float) (7000000 * rate);
			} else if (dataMoney > 55000000 && dataMoney <= 120000000) {
				rate = (float) 0.132;
				maxMoney = (float) (7000000 * rate);
			} else {
				rate = (float) 0.132;
				maxMoney = (float) (7000000 * rate);
			}
		}
		dataMoney = dataMoney * rate;

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt_irp)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_irp)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_irp)).requestLayout();
			mTotalMyDeduction += moneyToManwon(dataMoney);
		} else {
			((TextView) findViewById(R.id.tv_amt_irp)).setText(moneyToManwon(maxMoney) + "");
			mTotalMyDeduction += moneyToManwon(maxMoney);
		}

		((TextView) findViewById(R.id.tv_max_irp)).setText(" / " + moneyToManwon(maxMoney));
		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}

	private void drawHouseView() {
		float maxMoney = (float) (2400000 * 0.4);
		float dataMoney = (float) (chungyakTotalAmount * 0.4);

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt_house)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_house)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_house)).requestLayout();
			mTotalMyDeduction += moneyToManwon(dataMoney);
		} else {
			((TextView) findViewById(R.id.tv_amt_house)).setText(moneyToManwon(maxMoney) + "");
			mTotalMyDeduction += moneyToManwon(maxMoney);
		}

		((TextView) findViewById(R.id.tv_max_house)).setText(" / " + moneyToManwon(maxMoney));
		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}
/*

	private void drawFundView() {
		float maxMoney = (float) (3000000 * 0.1);
		float dataMoney = (float) (fundTotalAmount * 0.1);

		float percent = (float) dataMoney / maxMoney;
		((TextView) findViewById(R.id.tv_amt_fund)).setText(moneyToManwon(dataMoney) + "");
		if (percent < 1.0) {
			// 공제 전
			((View) findViewById(R.id.progress_fund)).getLayoutParams().width = (int) (progressWidth * percent);
			((View) findViewById(R.id.progress_fund)).requestLayout();
			mTotalMyDeduction += moneyToManwon(dataMoney);
		} else {
			((TextView) findViewById(R.id.tv_amt_fund)).setText(moneyToManwon(maxMoney) + "");
			mTotalMyDeduction += moneyToManwon(maxMoney);
		}

		((TextView) findViewById(R.id.tv_max_fund)).setText(" / " + moneyToManwon(maxMoney));
		mTotalMyDeductionLimits += moneyToManwon(maxMoney);
	}
*/

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

	/**
	 * 10000 -> 1(만원) 으로 바꿈
	 *
	 * @param money
	 * @return
	 */
	private int moneyToManwon(float money) {
		float moneyManwon = (float) money / 10000;
		int moneyManwonInt = (int) Math.round(moneyManwon);

		return moneyManwonInt;
	}

	private String commaMoney(int money) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String formattedAmt = formatter.format(money);
		return formattedAmt;
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
			/*case R.id.ll_list_stock:
				intent = new Intent(MainActivity.this, PlusMoneyActivity.class);
				startActivity(intent);
				break;*/
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
			/*case R.id.ll_list_fund:
				// TODO
				*//*intent = new Intent(MainActivity.this, InsuranceInquiryActivity.class);
				startActivity(intent);*//*
				break;*/
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

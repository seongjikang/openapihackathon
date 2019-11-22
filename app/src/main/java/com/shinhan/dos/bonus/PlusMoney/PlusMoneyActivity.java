package com.shinhan.dos.bonus.PlusMoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shinhan.dos.bonus.PlusMoney.Fragment.CardProductFragment;
import com.shinhan.dos.bonus.PlusMoney.Fragment.StockProductFragment;
import com.shinhan.dos.bonus.PlusMoney.Fragment.insuranceProductFragment;
import com.shinhan.dos.bonus.R;

public class PlusMoneyActivity extends AppCompatActivity {

    TextView product_type_txt;
    TextView plus_money_amount;
    ImageView plus_card_btn;
    ImageView plus_stock_btn;
    ImageView plus_insurance_btn;

    ViewPager vp;
    pagerAdapter vpadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_money);

        vp = (ViewPager) findViewById(R.id.plus_ViewPager);
        product_type_txt = (TextView)findViewById(R.id.product_type_txt);
        plus_money_amount = (TextView)findViewById(R.id.plus_money_amount);
        plus_card_btn = (ImageView) findViewById(R.id.plus_card_btn);
        plus_stock_btn = (ImageView) findViewById(R.id.plus_stock_btn);
        plus_insurance_btn = (ImageView) findViewById(R.id.plus_insurance_btn);

        /**
         * viewpager 잘돌아가게 하기 위함
         */
        vpadapter = new pagerAdapter(getSupportFragmentManager());
        vp.setAdapter(vpadapter);
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        plus_card_btn.setImageResource(R.drawable.icon_card_prdt);
                        plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                        plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);

                        break;
                    case 1:
                        plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                        plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt);
                        plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);

                        break;
                    case 2:
                        plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                        plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                        plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt);

                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        plus_card_btn.setOnClickListener(movePageListener);
        plus_card_btn.setTag(0);
        plus_stock_btn.setOnClickListener(movePageListener);
        plus_stock_btn.setTag(1);
        plus_insurance_btn.setOnClickListener(movePageListener);
        plus_insurance_btn.setTag(2);

    }

    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            switch (tag) {
                case 0:
                    plus_card_btn.setImageResource(R.drawable.icon_card_prdt);
                    plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                    plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);

                    break;
                case 1:
                    plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                    plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt);
                    plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);

                    break;
                case 2:
                    plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                    plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                    plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt);

                    break;

            }
            vp.setCurrentItem(tag);

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        vpadapter.notifyDataSetChanged();
    }


    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CardProductFragment();
                case 1:
                    return new StockProductFragment();
                case 2:
                    return new insuranceProductFragment();

                default:
                    return null;
            }
        }
        public int getItemPosition(Object item) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
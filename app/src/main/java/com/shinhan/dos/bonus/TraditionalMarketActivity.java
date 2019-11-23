package com.shinhan.dos.bonus;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shinhan.dos.bonus.R;
import com.shinhan.dos.bonus.TraditionalMarket.TraditionalMarketAdapter;
import com.shinhan.dos.bonus.TraditionalMarket.TraditionalMarketData;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TraditionalMarketActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private RecyclerView mRecyclerViewTm;
    private TraditionalMarketAdapter mTMAdapter;

    private ArrayList<TraditionalMarketData> mResultList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traditional_market);

        //예제데이터
        TraditionalMarketData temp1 = new TraditionalMarketData(37.570496,126.999368,"c","광장시장",1000);
        TraditionalMarketData temp2 = new TraditionalMarketData(37.563484,126.995116,"c","인현시장",2000);
        TraditionalMarketData temp3 = new TraditionalMarketData(37.570427,126.991814,"a","신한은행 종로 5가 지점 ATM",3000);
        mResultList.add(temp1);mResultList.add(temp2);mResultList.add(temp3);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mRecyclerViewTm = findViewById(R.id.recyclerview_tm);
        mRecyclerViewTm.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mTMAdapter = new TraditionalMarketAdapter(mResultList);
        mRecyclerViewTm.setAdapter(mTMAdapter);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<LatLng> latLngs = new ArrayList<>();
        LatLng SEOUL = new LatLng(37.56, 126.97);
        for(int i=0; i< mResultList.size(); i++) {
            LatLng temp = new LatLng(mResultList.get(i).getX(),mResultList.get(i).getY());
            latLngs.add(temp);
        }

        ArrayList<MarkerOptions> markerOptionsList = new ArrayList<>();
        for(int i=0; i< mResultList.size(); i++) {
            MarkerOptions tempMarkerOptions = new MarkerOptions();
            tempMarkerOptions.position(latLngs.get(i));
            tempMarkerOptions.title(mResultList.get(i).getName());
            if(mResultList.get(i).getTmGbn().equals("c")) {
                tempMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            } else {
                tempMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }
            markerOptionsList.add(tempMarkerOptions);
        }

        Log.i("markerOptionsListSize",markerOptionsList.size()+"");
        for(int i =0; i<markerOptionsList.size();i++) {

            mMap.addMarker(markerOptionsList.get(i));
        }

       // MarkerOptions markerOptions = new MarkerOptions();
        //markerOptions.position(SEOUL);
       // markerOptions.title("서울");
       // markerOptions.snippet("한국의 수도");
        //mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }


}

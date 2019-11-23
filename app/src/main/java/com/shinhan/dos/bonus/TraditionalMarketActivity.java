package com.shinhan.dos.bonus;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.R;
import com.shinhan.dos.bonus.TraditionalMarket.TraditionalMarketAdapter;
import com.shinhan.dos.bonus.TraditionalMarket.TraditionalMarketData;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraditionalMarketActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "TraditionalMarket";

    private GoogleMap mMap;
    private RecyclerView mRecyclerViewTm;
    private TraditionalMarketAdapter mTMAdapter;
    private TextView mMoney;

    private ArrayList<TraditionalMarketData> mResultList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traditional_market);

        getATMInfo();
        getTraditionalMarketInfo();
        getCulturePayment();

        //예제데이터
        //TraditionalMarketData temp1 = new TraditionalMarketData(37.570496,126.999368,"c","광장시장",1000);
       // TraditionalMarketData temp2 = new TraditionalMarketData(37.563484,126.995116,"c","인현시장",2000);
        //TraditionalMarketData temp3 = new TraditionalMarketData(37.570427,126.991814,"a","신한은행 종로 5가 지점 ATM",3000);
        //mResultList.add(temp1);mResultList.add(temp2);mResultList.add(temp3);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mRecyclerViewTm = findViewById(R.id.recyclerview_tm);
        mRecyclerViewTm.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mTMAdapter = new TraditionalMarketAdapter(mResultList);
        mRecyclerViewTm.setAdapter(mTMAdapter);
        mMoney = findViewById(R.id.money);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        LatLng shinhan = new LatLng(37.566585, 126.988000);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(shinhan));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

    public void setGoogleMapMarker() {
        ArrayList<LatLng> latLngs = new ArrayList<>();
        LatLng shinhan = new LatLng(37.566585, 126.988000);
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

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(shinhan);
        markerOptions.title("내위치");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(shinhan));
      //  mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }
    // 전통 시장 정보 불러오기
    private void getTraditionalMarketInfo() {
        Map params = new LinkedHashMap<>();
        params.put("hpno", "01071444074");
        DataResult dataResult = new DataResultImpl();
        dataResult.getCultureList(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				//response.body().get("dataBody").getAsJsonObject().get("atmList").getAsJsonArray().get(0);
                Log.d(TAG, response.body().get("dataBody").getAsJsonObject().get("curtureList").getAsJsonArray().get(0).toString());
                JsonArray jsonArray = response.body().get("dataBody").getAsJsonObject().get("curtureList").getAsJsonArray();
                for(int i=0; i<jsonArray.size(); i++) {
                    JsonObject jsonObject = (JsonObject)jsonArray.get(i);
                   // TraditionalMarketData temp1 = new TraditionalMarketData(37.570496,126.999368,"c","광장시장",1000);
                    TraditionalMarketData temp = new TraditionalMarketData();
                    temp.setName(jsonObject.get("atmName").getAsString());
                    temp.setX(Double.parseDouble(jsonObject.get("x").getAsString().replace("\"","")));
                    temp.setY(Double.parseDouble(jsonObject.get("y").getAsString().replace("\"","")));

                    double distanceKiloMeter = distance(37.566585, 126.988000, Double.parseDouble(jsonObject.get("x").getAsString().replace("\"","")),
                            Double.parseDouble(jsonObject.get("y").getAsString().replace("\"","")), "kilometer");
                    temp.setDistance(distanceKiloMeter);
                    temp.setTmGbn("c");
                    mResultList.add(temp);
                    mTMAdapter.changeList(mResultList);
                    setGoogleMapMarker();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "curture FAIL....");

            }
        }, params);
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    //
    private void getCulturePayment() {
        Map params = new LinkedHashMap<>();
        params.put("hpno", "01071444074");
        DataResult dataResult = new DataResultImpl();
        dataResult.getCulturePayment(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, response.body().get("dataBody").getAsJsonObject().get("traditionalList").getAsJsonArray().get(0).toString());
                JsonArray jsonArray = response.body().get("dataBody").getAsJsonObject().get("traditionalList").getAsJsonArray();
                int sum =0;
                for(int i=0; i<jsonArray.size(); i++) {
                    JsonObject jsonObject = (JsonObject)jsonArray.get(i);
                    sum+=Integer.parseInt(jsonObject.get("amount").getAsString().replace("\"",""));
                    setGoogleMapMarker();
                }
                Log.d(TAG, "sum = "+sum);
                mMoney.setText((2500000-sum)+"원");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "culturePayment FAIL....");

            }
        }, params);
    }


    // Atm 정보 불러오기
    private void getATMInfo() {
        Map params = new LinkedHashMap<>();
        params.put("hpno", "01071444074");
        params.put("keyword", "서울");
        DataResult dataResult = new DataResultImpl();
        dataResult.getRetailAtm(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, response.body().get("dataBody").getAsJsonObject().get("atmList").getAsJsonArray().get(0).toString());
                JsonArray jsonArray = response.body().get("dataBody").getAsJsonObject().get("atmList").getAsJsonArray();
                for(int i=0; i<jsonArray.size(); i++) {
                    JsonObject jsonObject = (JsonObject)jsonArray.get(i);
                    // TraditionalMarketData temp1 = new TraditionalMarketData(37.570496,126.999368,"c","광장시장",1000);
                    TraditionalMarketData temp = new TraditionalMarketData();
                    temp.setName(jsonObject.get("atmName").getAsString());
                    temp.setX(Double.parseDouble(jsonObject.get("x").getAsString().replace("\"","")));
                    temp.setY(Double.parseDouble(jsonObject.get("y").getAsString().replace("\"","")));

                    double distanceKiloMeter = distance(37.566585, 126.988000, Double.parseDouble(jsonObject.get("x").getAsString().replace("\"","")),
                            Double.parseDouble(jsonObject.get("y").getAsString().replace("\"","")), "kilometer");
                    temp.setDistance(distanceKiloMeter);
                    temp.setTmGbn("a");
                    mResultList.add(temp);
                    mTMAdapter.changeList(mResultList);
                    setGoogleMapMarker();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "atm FAIL....");

            }
        }, params);
    }
}

package com.shinhan.dos.bonus.PlusMoney.Fragment.FragCard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.R;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardProductFragment extends Fragment {

    private RecyclerView productRecyclerview;
    private ProductAdapter adapter;
    GridLayoutManager mLayoutManager;
    private ArrayList<ProductData> productDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_card, null);
        productRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_product);
        productRecyclerview.setHasFixedSize(true);

        //레이아웃 매니저 설정
        mLayoutManager =new GridLayoutManager(getActivity(), 2); //설정에서 지정하는 상징의 크기에 따라 칼럼수변화
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new ProductAdapter(productDatas, clickEvent);
        productRecyclerview.setAdapter(adapter);


        Map params = new LinkedHashMap<>();
        params.put("hpno", "01071444074");
        DataResult dataResult = new DataResultImpl();
        dataResult.getCardList(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
                if(response.isSuccessful()){

                    Gson gson = new Gson();
                    ResultData result = gson.fromJson(response.body().get("dataBody"), ResultData.class);

                    result.getcreditCardList();
                    productDatas.addAll(result.creditCardList);
                    adapter = new ProductAdapter(productDatas, clickEvent);
                    productRecyclerview.setAdapter(adapter);

                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("STOCKJSON", "FAIL....");

            }
        }, params);


        adapter.notifyDataSetChanged();

        return view;
    }

    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            final int itemPosition = productRecyclerview.getChildPosition(v);
            Toast.makeText(getContext(), itemPosition + "번 리스트 클릭!!", Toast.LENGTH_SHORT).show();

        }
    };
}

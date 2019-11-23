package com.shinhan.dos.bonus.PlusMoney.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class StockProductFragment extends Fragment {


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



        /**
         * 2. 데이터 추가 (connect server)
         * */

        productDatas.add(new ProductData(1, "DeepDream", "이거 좋은 카드다₩~~"));
        productDatas.add(new ProductData(1, "DeepDream", "이거 좋은 카드다₩~~"));
        productDatas.add(new ProductData(1, "강성지", "이거 좋은 카드다₩~~"));
        productDatas.add(new ProductData(1, "최리나카드", "이거 좋은 카드다₩~~"));
        productDatas.add(new ProductData(1, "좋은카드", "이거 좋은 카드다₩~~"));
        productDatas.add(new ProductData(1, "DeepDream", "이거 좋은 카드다₩~~"));



        /**
         * 3. notify
         * */
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

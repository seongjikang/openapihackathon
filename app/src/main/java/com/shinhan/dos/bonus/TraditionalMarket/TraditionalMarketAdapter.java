package com.shinhan.dos.bonus.TraditionalMarket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TraditionalMarketAdapter extends RecyclerView.Adapter<TraditionalMarketAdapter.ItemHolder> {

    private ArrayList<TraditionalMarketData> traditionalMarketList = new ArrayList<TraditionalMarketData>();

    class ItemHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView distance;
        private ImageView marker;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            distance = itemView.findViewById(R.id.distance);
            marker = itemView.findViewById(R.id.marker);
        }
    }

    public TraditionalMarketAdapter(ArrayList<TraditionalMarketData> traditionalMarketList) {
        this.traditionalMarketList = traditionalMarketList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recylerview_atm_and_market_item, parent, false);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final TraditionalMarketData traditionalMarketData = traditionalMarketList.get(position);
        holder.name.setText(traditionalMarketData.getName());
        String tmGbn = traditionalMarketData.getTmGbn();
        if(tmGbn.equals("c")) {
            holder.marker.setImageResource(R.drawable.icon_marker_red);
        } else {
            holder.marker.setImageResource(R.drawable.icon_marker_blue);
        }
        double x = traditionalMarketData.getX();
        double y = traditionalMarketData.getY();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(ResultViewActivity.this, DetailViewActivity.class);
              //  intent.putExtra(SearchViewActivity.KEY_WORD, mTvKeyword.getText());
               // intent.putExtra(IMAGE_LINK, image.getLink());
              //  startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return traditionalMarketList.size();
    }

    public void changeList(ArrayList<TraditionalMarketData> newTraditionalMarketDataArrayList) {
        traditionalMarketList = newTraditionalMarketDataArrayList;
        notifyDataSetChanged();
    }
}

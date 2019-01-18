package com.bw.shen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.shen.R;
import com.bw.shen.bean.IndentAll;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2019-1-11.
 */

public class OversAdapter extends RecyclerView.Adapter<OversAdapter.ViewHolder>{
    private Context context;
    private List<IndentAll.DetailListBean> list;

    public OversAdapter(Context context, List<IndentAll.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_overs, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        String commodityPic = list.get(i).getCommodityPic();
        String[] split = commodityPic.split("\\,");

        holder.orderSdvImage.setImageURI(split[1]);
        holder.oederTxtTitle.setText(list.get(i).getCommodityName());
        holder.oederTxtPrice.setText("￥"+list.get(i).getCommodityPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView orderSdvImage;
        private final TextView oederTxtTitle;
        private final TextView oederTxtPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            orderSdvImage = itemView.findViewById(R.id.order_sdv_image);
            oederTxtTitle = itemView.findViewById(R.id.order_txt_title);
            oederTxtPrice = itemView.findViewById(R.id.order_txt_price);

        }
    }
}

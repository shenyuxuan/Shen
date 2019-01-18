package com.bw.shen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.shen.R;
import com.bw.shen.bean.Shops;

import java.util.List;

/**
 * Created by lenovo on 2019-1-2.
 */

public class PAdapter extends RecyclerView.Adapter<PAdapter.ViewHolder>{
    private Context mContext;
    private List<Shops.ResultBean.PzshBean.CommodityListBeanX> mList;

    public PAdapter(Context context, List<Shops.ResultBean.PzshBean.CommodityListBeanX> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_pz, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getMasterPic()).into(holder.img);
        holder.title.setText(mList.get(position).getCommodityName());
        holder.price.setText("￥:"+mList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
    }
}

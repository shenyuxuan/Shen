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

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder>{
    private Context mContext;
    private List<Shops.ResultBean.MlssBean.CommodityListBeanXX> mList;

    public MAdapter(Context context, List<Shops.ResultBean.MlssBean.CommodityListBeanXX> list) {
        mContext = context;
        mList = list;
    }
    //设置点击事件
    public interface onItemClickListener{
        void onItemClick(View itemView,int position);
    }

    public onItemClickListener listener;

    public void setonItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_ml, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getMasterPic()).into(holder.img);
        holder.title.setText(mList.get(position).getCommodityName());
        holder.price.setText("￥:"+mList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(v,position);
                }
            }
        });
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

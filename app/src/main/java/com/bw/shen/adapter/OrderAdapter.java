package com.bw.shen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.shen.MyAddSubView;
import com.bw.shen.R;
import com.bw.shen.bean.IndentAll;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2019-1-4.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private Context context;
    private List<IndentAll.DetailListBean> list;

    public OrderAdapter(Context context, List<IndentAll.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order, null);
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
    /**
     * 接口回调
     */
    public interface OrderListener{
        void changeNum(int i,int number);
    }
    public OrderListener mOrderListener;
    public void setOrderListener(OrderListener orderListener){
        mOrderListener = orderListener;
    }

    //计算商品总价
    public float AllNum(){
        float num = 0;
        for (int i  = 0  ;  i <list.size() ;  i++ ){
            int commodityCount = list.get(i).getCommodityCount();
            int commodityPrice = list.get(i).getCommodityPrice();
            num += commodityCount * commodityPrice;
        }
        return num;
    }

    public int AllSum(){
        int num = 0;
        for (int i  = 0  ;  i <list.size() ;  i++ ){
            int commodityCount = list.get(i).getCommodityCount();
            num += commodityCount ;
        }
        return num;
    }

    public void changeCurrentProductNumber(int Position,int number){
       IndentAll.DetailListBean listBean = list.get(Position);
        listBean.setCommodityCount(number);
    }
}

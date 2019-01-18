package com.bw.shen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.shen.R;
import com.bw.shen.bean.IndentAll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by lenovo on 2019-1-11.
 */

public class OverAdapter extends RecyclerView.Adapter<OverAdapter.ViewHolder>{
    private Context mContext;
    private List<IndentAll> mList;
    private ToBesAdapter mToBesAdapter;
    public OverAdapter(Context context) {
        mContext = context;
        mList=new ArrayList<>();
    }
    //定义日期格式
    public static final String FORMAT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME_PATTERN, Locale.getDefault());

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_over, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        holder.allOrderTxtDindan.setText(mList.get(i).getExpressSn()+"");
        holder.allOrderTxtDate.setText(mList.get(i).getOrderId());

        //  holder.allOrderTxtSum.setText("商品数量"+mList.get(0).getDetailList().size()+"需要支付"+mList.get(i).getPayAmount());
        //布局管理器
        holder.allOrderRecyItem.setLayoutManager(new LinearLayoutManager(mContext));
        //设置子条目
        List<IndentAll.DetailListBean> detailList =mList.get(0).getDetailList();
        mToBesAdapter = new ToBesAdapter(mContext, detailList);
        holder.allOrderRecyItem.setAdapter(mToBesAdapter);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void setData(List<IndentAll> result) {
        if (result!=null){
            mList=result;
            notifyDataSetChanged();
        }
    }

    public void addAll(List<IndentAll> result){
        if (result!=null){
            mList.addAll(result);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView allOrderTxtDindan;
        private final TextView allOrderTxtDate;
        private final RecyclerView allOrderRecyItem;
        private final Button allOrderBtnQuxiao;
        private final Button allOrderBtnBuy;
        private TextView time;
        //   private final TextView allOrderTxtSum;
        public ViewHolder(View itemView) {
            super(itemView);
            allOrderTxtDindan = itemView.findViewById(R.id.allorder_txt_dindan);
            allOrderTxtDate = itemView.findViewById(R.id.allorder_txt_date);
            allOrderRecyItem = itemView.findViewById(R.id.allorder_recy_item);
            // allOrderTxtSum = itemView.findViewById(R.id.allorder_txt_sum);
            allOrderBtnQuxiao = itemView.findViewById(R.id.allorder_btn_quxiao);
            allOrderBtnBuy = itemView.findViewById(R.id.allorder_btn_buy);
            time=itemView.findViewById(R.id.time);
        }
    }
}

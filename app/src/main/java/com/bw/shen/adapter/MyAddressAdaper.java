package com.bw.shen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.shen.R;
import com.bw.shen.bean.MyAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019-1-15.
 */

public class MyAddressAdaper extends RecyclerView.Adapter<MyAddressAdaper.ViewHolder> {
    private Context context;
    private List<MyAddress> list;

    public MyAddressAdaper(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addAll(List<MyAddress> result) {
        if (result !=null){
            list.addAll(result);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.myaddress_xrecy_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.myaddressTxtName.setText(list.get(position).getRealName());
        holder.myaddressTxtPhone.setText(list.get(position).getPhone());
        holder.myaddressTxtAddress.setText(list.get(position).getAddress());
        holder.myaddressBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(layoutPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //实现删除方法
    public void remove(int i) {
        list.remove(i);
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView myaddressTxtName;
        private final TextView myaddressTxtPhone;
        private final TextView myaddressTxtAddress;
        private final Button myaddressBtnUpdate;
        private final Button myaddressBtnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            myaddressTxtName = itemView.findViewById(R.id.myaddress_txt_name);
            myaddressTxtPhone = itemView.findViewById(R.id.myaddress_txt_phone);
            myaddressTxtAddress = itemView.findViewById(R.id.myaddress_txt_address);
            myaddressBtnUpdate = itemView.findViewById(R.id.myaddress_btn_update);
            myaddressBtnDelete = itemView.findViewById(R.id.myaddress_btn_delete);

        }
    }
}
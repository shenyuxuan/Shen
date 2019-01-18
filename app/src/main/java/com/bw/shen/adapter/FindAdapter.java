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
import com.bw.shen.bean.FindBean;

import java.util.List;

/**
 * Created by lenovo on 2019-1-3.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder>{
    private Context mContext;
    private List<FindBean.ResultBean> mList;
    public FindAdapter(Context context, List<FindBean.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public FindAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_find, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull FindAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getHeadPic()).into(holder.img);
        holder.name.setText(mList.get(position).getNickName());
        holder.names.setText(mList.get(position).getContent());
        holder.title.setText(mList.get(position).getNickName());
        Glide.with(mContext).load(mList.get(position).getImage()).into(holder.imgs);
        Glide.with(mContext).load(mList.get(position).getImage()).into(holder.imgss);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView names;
        private TextView title;
        private ImageView imgs;
        private ImageView imgss;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            names=itemView.findViewById(R.id.names);
            title=itemView.findViewById(R.id.title);
            imgs=itemView.findViewById(R.id.imgs);
            imgss=itemView.findViewById(R.id.imgss);
        }
    }
}

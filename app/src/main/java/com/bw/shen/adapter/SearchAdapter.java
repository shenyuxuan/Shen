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
import com.bw.shen.bean.Search;
import com.bw.shen.bean.ShopCarBean;

import java.util.List;

/**
 * Created by lenovo on 2019-1-8.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private Context mContext;
    private List<Search> mList;

    public SearchAdapter(Context context) {
        mContext = context;

    }
    public void setData(List<Search> result) {
        mList= result;
        notifyDataSetChanged();
    }
    public interface itemClickListener{
        void itemClick(View itemView, int position);
    }

    public itemClickListener mListener;

    public  void setitemClickListener(itemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_search, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getMasterPic()).into(holder.img);
        holder.title.setText(mList.get(position).getCommodityName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.itemClick(v,position);
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
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
        }
    }
}

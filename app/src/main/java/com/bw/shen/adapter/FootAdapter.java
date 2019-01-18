package com.bw.shen.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.shen.R;
import com.bw.shen.bean.Foot;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by lenovo on 2019-1-11.
 */

public class FootAdapter extends RecyclerView.Adapter<FootAdapter.ViewHolder> {
    public static final String FORMAT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private Context context;
    private List<Foot> list;

    public FootAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_myfoot_recy, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myfootSdvImage.setImageURI(Uri.parse(list.get(position).getMasterPic()));
        holder.myfootTxtContent.setText(list.get(position).getCommodityName());
        holder.myfootTxtPrice.setText("￥:"+list.get(position).getPrice());
        holder.myfootTxtBrowsenum.setText("已浏览："+list.get(position).getBrowseNum()+"次");
        //转换成日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME_PATTERN, Locale.getDefault());
        holder.myfootTxtDate.setText(dateFormat.format(list.get(position).getBrowseTime()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<Foot> result) {
        if (result !=null){
            list.addAll(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView myfootSdvImage;
        private final TextView myfootTxtContent;
        private final TextView myfootTxtPrice;
        private final TextView myfootTxtBrowsenum;
        private final TextView myfootTxtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            myfootSdvImage = itemView.findViewById(R.id.myfoot_sdv_image);
            myfootTxtContent = itemView.findViewById(R.id.myfoot_txt_content);
            myfootTxtPrice = itemView.findViewById(R.id.myfoot_txt_price);
            myfootTxtBrowsenum = itemView.findViewById(R.id.myfoot_txt_browsenum);
            myfootTxtDate = itemView.findViewById(R.id.myfoot_txt_date);
        }
    }
}


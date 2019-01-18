package com.bw.shen.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bw.shen.R;

import java.util.List;

/**
 * Created by lenovo on 2019-1-11.
 */
public class MyWalletAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public MyWalletAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.item_wo_qian_bao, null);
            viewHolder.te = view.findViewById(R.id.money);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.te.setText(list.get(i));
        return view;
    }

    class ViewHolder {
        TextView te;
    }
}
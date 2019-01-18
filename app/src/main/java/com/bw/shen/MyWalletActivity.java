package com.bw.shen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bw.shen.adapter.MyWalletAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyWalletActivity extends AppCompatActivity {
    private TextView my_waller_price;
    private ListView my_wallet_list_view;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        my_waller_price = findViewById(R.id.my_waller_price);
        my_wallet_list_view = findViewById(R.id.my_wallet_list_view);
        MyWalletAdapter adapter = new MyWalletAdapter(this, list);
        my_wallet_list_view.setAdapter(adapter);

        //设置字体加粗
        TextPaint paint = my_waller_price.getPaint();
        paint.setFakeBoldText(true);
        for (int i = 0; i < 10; i++) {
            list.add("￥：1120.00");
        }
    }
}

package com.bw.shen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bw.shen.adapter.SearchAdapter;
import com.bw.shen.bean.Result;
import com.bw.shen.bean.Search;
import com.bw.shen.searchmvp.presenter.SearchPresenter;
import com.bw.shen.searchmvp.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchView{

    @BindView(R.id.recyview)
    RecyclerView mRecyview;
    private List<Search> mList;
    private SearchAdapter adapter;
    private SearchPresenter mSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mList=new ArrayList<>();
        String keyword=intent.getStringExtra("keyword");
        Toast.makeText(this,""+keyword,Toast.LENGTH_SHORT).show();
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyview.setLayoutManager(manager);
        adapter=new SearchAdapter(this);

        mSearchPresenter=new SearchPresenter();
        mSearchPresenter.attach(this);
        mSearchPresenter.get(keyword,1,10);

        adapter.setitemClickListener(new SearchAdapter.itemClickListener() {
            @Override
            public void itemClick(View itemView, int position) {
                int commodityId = mList.get(position).getCommodityId();

                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                intent.putExtra("commodityId", commodityId);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(Result<List<Search>> data) {
       if (data!=null){
           adapter.setData(data.getResult());
           mRecyview.setAdapter(adapter);
       }
    }

    @Override
    public void onfailed(Exception e) {

    }
}

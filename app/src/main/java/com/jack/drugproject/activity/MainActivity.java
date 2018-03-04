package com.jack.drugproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.google.gson.reflect.TypeToken;
import com.jack.drugproject.R;
import com.jack.drugproject.bean.Drug;
import com.jack.drugproject.presenter.HttpRequestServer;
import com.jack.drugproject.utils.ResponseUtil;
import com.socks.library.KLog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drug_rv)
    RecyclerView drugRv;
    @BindView(R.id.null_rl)
    RelativeLayout nullRl;

    public static final String DRUG_DATA_URL = "/user/drugData";
    private ArrayList<Drug> drugArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        HttpRequestServer.create(this).doGet(DRUG_DATA_URL, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (ResponseUtil.verify(responseBody, true)) {
                    drugArrayList = (ArrayList<Drug>) ResponseUtil.getByType(new TypeToken<ArrayList<Drug>>(){}.getType());
                    initView();
                }
            }
        });
    }

    private void initView() {
        if (drugArrayList == null||drugArrayList.size() == 0){
            nullRl.setVisibility(View.VISIBLE);
            return;
        }
        nullRl.setVisibility(View.GONE);
        drugRv.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<Drug> adapter = new CommonAdapter<Drug>(this,R.layout.task_show,drugArrayList) {
            @Override
            protected void convert(ViewHolder holder, Drug drug, int position) {
                holder.setText(R.id.drug_name, drug.getDrug_name());
                holder.setText(R.id.drug_time, drug.getDrug_time());
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(MainActivity.this, DrugActivity.class);
                intent.putExtra("drug", drugArrayList.get(position));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        drugRv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_action,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search){
            KLog.i();
        }
        return super.onOptionsItemSelected(item);
    }
}

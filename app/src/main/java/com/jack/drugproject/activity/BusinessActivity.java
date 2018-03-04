package com.jack.drugproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.jack.drugproject.R;
import com.jack.drugproject.bean.Drug;
import com.jack.drugproject.bean.User;
import com.jack.drugproject.presenter.HttpRequestServer;
import com.jack.drugproject.utils.ResponseUtil;
import com.jack.drugproject.utils.ToastUtil;
import com.jack.drugproject.utils.UserUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;

public class BusinessActivity extends AppCompatActivity {

    @BindView(R.id.drug_list_rv)
    RecyclerView drugListRv;

    public static final String BUSINESS_URL = "/user/businessData";
    public static final String BUSINESS_GET_URL = "/user/orderBusiness";
    private String bsId;
    @BindView(R.id.null_rl)
    RelativeLayout nullRl;
    private ArrayList<Drug> drugArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String bsName = intent.getStringExtra("bsName");
        bsId = intent.getStringExtra("bsId");
        initActionBar(bsName);
        Map<String, String> map = new HashMap<>();
        map.put("business_id", bsId);
        HttpRequestServer.create(this).doGetWithParams(BUSINESS_URL, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (ResponseUtil.verify(responseBody, true)) {
                    drugArrayList = (ArrayList<Drug>) ResponseUtil.getByType(new TypeToken<ArrayList<Drug>>() {
                    }.getType());
                    initView();
                }
            }
        });
    }

    private void initView() {
        if (drugArrayList == null || drugArrayList.size() == 0) {
            nullRl.setVisibility(View.VISIBLE);
            return;
        }
        nullRl.setVisibility(View.GONE);
        drugListRv.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<Drug> adapter = new CommonAdapter<Drug>(this, R.layout.task_show, drugArrayList) {
            @Override
            protected void convert(ViewHolder holder, Drug drug, int position) {
                holder.setText(R.id.drug_name, drug.getDrug_name());
                holder.setText(R.id.drug_time, drug.getDrug_time());
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(BusinessActivity.this, DrugActivity.class);
                intent.putExtra("drug", drugArrayList.get(position));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        drugListRv.setAdapter(adapter);
    }

    private void initActionBar(String bsName) {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("店铺：" + bsName);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.business_btn)
    public void onViewClicked() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(((User) UserUtil.getCurrentUser()).getUser_id()));
        map.put("business_id", bsId);
        HttpRequestServer.create(this).doGetWithParams(BUSINESS_GET_URL, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (ResponseUtil.verify(responseBody, false)) {
                    ToastUtil.getInstance().log("收藏成功");
                    finish();
                }else {
                    ToastUtil.getInstance().log("不能重复收藏");
                }
            }
        });
    }
}

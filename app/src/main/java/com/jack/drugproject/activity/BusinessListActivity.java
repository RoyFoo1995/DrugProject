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
import com.jack.drugproject.bean.Business;
import com.jack.drugproject.bean.Drug;
import com.jack.drugproject.bean.User;
import com.jack.drugproject.presenter.HttpRequestServer;
import com.jack.drugproject.utils.ResponseUtil;
import com.jack.drugproject.utils.UserUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.Subscriber;

public class BusinessListActivity extends AppCompatActivity {

    @BindView(R.id.order_list_rv)
    RecyclerView orderListRv;
    @BindView(R.id.null_rl)
    RelativeLayout nullRl;

    public static String BUSINESS_LIST_URL = "/user/myorderBusiness";
    private ArrayList<Business> businessArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);
        ButterKnife.bind(this);
        initActionBar();
        initData();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("收藏的店铺");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(((User) UserUtil.getCurrentUser()).getUser_id()));
        HttpRequestServer.create(this).doGetWithParams(BUSINESS_LIST_URL, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (ResponseUtil.verify(responseBody, true)) {
                    businessArrayList = (ArrayList<Business>) ResponseUtil.getByType(new TypeToken<ArrayList<Business>>() {
                    }.getType());
                    initView();
                }
            }
        });
    }
    private void initView() {
        if (businessArrayList == null || businessArrayList.size() == 0) {
            nullRl.setVisibility(View.VISIBLE);
            return;
        }
        nullRl.setVisibility(View.GONE);
        orderListRv.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<Business> adapter = new CommonAdapter<Business>(this, R.layout.task_show, businessArrayList) {
            @Override
            protected void convert(ViewHolder holder, Business business, int position) {
                holder.setText(R.id.drug_name, business.getBusiness_name());
                holder.setText(R.id.drug_time, String.valueOf(business.getBusiness_phone()));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(BusinessListActivity.this, BusinessActivity.class);
                intent.putExtra("bsName", businessArrayList.get(position).getBusiness_name());
                intent.putExtra("bsId",String.valueOf(businessArrayList.get(position).getBusiness_id()));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        orderListRv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

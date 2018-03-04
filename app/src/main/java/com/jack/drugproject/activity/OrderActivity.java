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

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.order_list_rv)
    RecyclerView orderListRv;
    @BindView(R.id.null_rl)
    RelativeLayout nullRl;
    public static final String ORDER_LIST_URL = "/user/myOrder";
    private ArrayList<Drug> drugArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initActionBar();
        initData();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("预定的药品");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(((User) UserUtil.getCurrentUser()).getUser_id()));
        HttpRequestServer.create(this).doGetWithParams(ORDER_LIST_URL, map, new Subscriber<ResponseBody>() {
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
        orderListRv.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<Drug> adapter = new CommonAdapter<Drug>(this, R.layout.task_show, drugArrayList) {
            @Override
            protected void convert(ViewHolder holder, Drug drug, int position) {
                holder.setText(R.id.drug_name, drug.getDrug_name());
                holder.setText(R.id.drug_time, drug.getStatus_name());
            }
        };
//        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                Intent intent = new Intent(OrderActivity.this, DrugActivity.class);
//                intent.putExtra("drug", drugArrayList.get(position));
//                startActivity(intent);
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                return false;
//            }
//        });
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

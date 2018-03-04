package com.jack.drugproject.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jack.drugproject.R;
import com.jack.drugproject.bean.Drug;
import com.jack.drugproject.bean.User;
import com.jack.drugproject.presenter.HttpRequestServer;
import com.jack.drugproject.utils.ResponseUtil;
import com.jack.drugproject.utils.ToastUtil;
import com.jack.drugproject.utils.UserUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;

public class DrugActivity extends AppCompatActivity {

    @BindView(R.id.drug_name_et)
    TextView drugNameEt;
    @BindView(R.id.drug_info_et)
    TextView drugInfoEt;
    @BindView(R.id.drug_use_et)
    TextView drugUseEt;
    @BindView(R.id.drug_time_tv)
    TextView drugTimeTv;
    @BindView(R.id.business_tv)
    TextView businessTv;

    public static final String DRUG_DETEIL_URL = "/user/drugInfo";
    public static final String ORDER_DRUG_URL = "/user/orderDrug";
    private Drug drug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        ButterKnife.bind(this);
        initActionBar();
        initData();
        initView();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setTitle("药品详情");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void initView() {
        drugNameEt.setText(drug.getDrug_name());
        drugInfoEt.setText(drug.getDrug_info());
        drugTimeTv.setText(drug.getDrug_time());
        drugUseEt.setText(drug.getDrug_symptom());
    }

    private void initData() {
        drug = (Drug) getIntent().getSerializableExtra("drug");
    }

    @OnClick({R.id.business_tv, R.id.order_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.business_tv:
                go2Business();
                break;
            case R.id.order_btn:
                orderDrug();
                break;
        }
    }

    private void orderDrug() {
        Map<String, String> map = new HashMap<>();
        map.put("drug_id", String.valueOf(drug.getDrug_id()));
        map.put("business_id", String.valueOf(drug.getBusiness_id()));
        map.put("user_id", String.valueOf(((User) UserUtil.getCurrentUser()).getUser_id()));
        HttpRequestServer.create(this).doGetWithParams(ORDER_DRUG_URL, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (ResponseUtil.verify(responseBody, false)) {
                    ToastUtil.getInstance().log("预定成功");
                }else{
                    ToastUtil.getInstance().log("预定失败");
                }
                finish();
            }
        });
    }

    private void go2Business() {

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

package com.jack.drugproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.jack.drugproject.R;
import com.jack.drugproject.presenter.HttpRequestServer;
import com.jack.drugproject.utils.ResponseUtil;
import com.jack.drugproject.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;

    private static final String REGIST_URL = "/user/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_up_btn)
    public void onViewClicked() {
        String name = nameEt.getText().toString();
        String password = passwordEt.getText().toString();
        String phone = phoneEt.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("user_name", name);
        map.put("user_phone", phone);
        map.put("user_password", password);
        HttpRequestServer.create(this).doGetWithParams(REGIST_URL, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (ResponseUtil.verify(responseBody,false)){
                    ToastUtil.getInstance().log("注册成功");
                    finish();
                }else{
                    ToastUtil.getInstance().log("注册失败");
                }
            }
        });
    }
}

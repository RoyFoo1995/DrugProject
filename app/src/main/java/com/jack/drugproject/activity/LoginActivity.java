package com.jack.drugproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.jack.drugproject.R;
import com.jack.drugproject.bean.User;
import com.jack.drugproject.presenter.HttpRequestServer;
import com.jack.drugproject.utils.ResponseUtil;
import com.jack.drugproject.utils.ToastUtil;
import com.jack.drugproject.utils.UserUtil;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.password_et)
    EditText passwordEt;

    public static String LOGIN_URL = "/user/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ToastUtil.getInstance().init(getApplicationContext());
    }

    @OnClick({R.id.login_btn, R.id.signUp_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                login();
                break;
            case R.id.signUp_btn:
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                break;
        }
    }

    private void login() {
        String userName = phoneEt.getText().toString();
        String password = passwordEt.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("user_phone",userName);
        map.put("user_password", password);
        HttpRequestServer.create(this).doGetWithParams(LOGIN_URL, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (ResponseUtil.verify(responseBody, true)) {
                    UserUtil.setUser(ResponseUtil.getByType(User.class));
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }
        });

    }
}

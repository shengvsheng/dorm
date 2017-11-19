package cn.edu.pku.wangyongsheng.dorm.activity;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import cn.edu.pku.wangyongsheng.dorm.R;


/**
 * Created by xiaoshengsheng on 2017/11/2.
 */

public class LoginActivity extends BaseActicity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText et_username;
    private EditText et_pwd;
    private Button btn_login;

    @Override
    protected int setRootViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        et_username = findViewById(R.id.et_username);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (!TextUtils.isEmpty(et_username.getText()) && !TextUtils.isEmpty(et_pwd.getText())) {
                    login(et_username.getText().toString(), et_pwd.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "学号或密码不能为空~~", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void login(String uname, String upwd) {
        String url = "https://api.mysspku.com/index.php/V1/MobileCourse/Login?username=" + uname + "&password=" + upwd;
        OkGo.<String>get(url).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                JSONObject jsonObject = JSON.parseObject(response.body());
                int errcode = jsonObject.getInteger("errcode");
                switch (errcode) {
                    case 0:
                        editor.putBoolean("LOGIN_STATUS",true);
                        editor.commit();
                        getIntent().putExtra("BODY", response.body());
                        getIntent().putExtra("USERNAME", et_username.getText().toString());
                        setResult(RESULT_OK, getIntent());
                        finish();
                        break;
                    case 40001:
                        Toast.makeText(LoginActivity.this, "学号不存在", Toast.LENGTH_LONG).show();
                        break;
                    case 40002:
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_LONG).show();
                        break;
                    case 40009:
                        Toast.makeText(LoginActivity.this, "参数错误", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }
}

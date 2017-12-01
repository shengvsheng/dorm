package cn.edu.pku.wangyongsheng.dorm.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import cn.edu.pku.wangyongsheng.dorm.R;


/**
 *
 * 登录activity的实现，完成登录的逻辑功能
 * Created by xiaoshengsheng
 */

public class LoginActivity extends BaseActicity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText et_username;
    private EditText et_pwd;
    private Button btn_login;
    private ImageView iv_back;

    //绑定布局文件
    @Override
    protected int setRootViewId() {
        return R.layout.activity_login;
    }
    //初始化控件
    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        et_username = findViewById(R.id.et_username);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);

    }
    //初始化数据
    @Override
    protected void initData() {

    }
    //设置监听
    @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }
    //复写onClick方法，对不同的控件点击做出事件响应
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //登录判断输入为不为空，若为空，Toast信息提示,若不为空，调用登陆方法
                if (!TextUtils.isEmpty(et_username.getText()) && !TextUtils.isEmpty(et_pwd.getText())) {
                    login(et_username.getText().toString(), et_pwd.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "学号或密码不能为空~~", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.iv_back:
                //点击返回键，返回上一个activity
                onBackPressed();
                break;
        }
    }
    /**
     * 登录方法，使用OKGo访问网络框架，对结果做出判断
     * 使用ProgressDialog作出登录进行中的加载页面，在访问网络结束onFinish()方法取消
     *
     * */
    private void login(String uname, String upwd) {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("正在登录...");
        mProgressDialog.show();
        String url = "https://api.mysspku.com/index.php/V1/MobileCourse/Login?username=" + uname + "&password=" + upwd;
        OkGo.<String>get(url).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                JSONObject jsonObject = JSON.parseObject(response.body());
                int errcode = jsonObject.getInteger("errcode");
                switch (errcode) {
                    case 0:
                        editor.putBoolean("LOGIN_STATUS", true);
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

            @Override
            public void onFinish() {
                super.onFinish();
                mProgressDialog.cancel();
            }
        });

    }
}

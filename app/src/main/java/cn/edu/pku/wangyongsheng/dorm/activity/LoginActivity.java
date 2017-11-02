package cn.edu.pku.wangyongsheng.dorm.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.pku.wangyongsheng.dorm.R;

/**
 * Created by xiaoshengsheng on 2017/11/2.
 */

public class LoginActivity extends BaseActicity {
    TextView textView;

    @Override
    protected int setRootViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.tv_woailuo);

    }

    @Override
    protected void initData() {
        textView.setText("哇哈哈");
    }

    @Override
    protected void initListener() {
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_woailuo:Toast.makeText(LoginActivity.this, textView.getText().toString(), Toast.LENGTH_LONG).show();break;
        }
    }
}

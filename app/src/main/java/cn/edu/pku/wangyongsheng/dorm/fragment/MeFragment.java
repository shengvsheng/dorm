package cn.edu.pku.wangyongsheng.dorm.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import cn.edu.pku.wangyongsheng.dorm.R;
import cn.edu.pku.wangyongsheng.dorm.activity.LoginActivity;

/**
 * Created by xiaoshengsheng on 2017/10/25.
 */

public class MeFragment extends BaseFragment {
    Button btn_login;

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
        btn_login = view.findViewById(R.id.btn_login);
    }

    @Override
    protected void initListener() {
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}

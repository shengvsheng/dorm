package cn.edu.pku.wangyongsheng.dorm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by xiaoshengsheng on 2017/11/19.
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            initParam(getIntent().getExtras());
        }
        setContentView(setRootViewId());
        initView();
        initData();
        initListener();
    }

    protected void initParam(Bundle savedInstanceState) {
    }



    protected abstract int setRootViewId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
}

package cn.edu.pku.wangyongsheng.dorm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by xiaoshengsheng on 2017/11/2.
 */

public abstract class BaseActicity extends Activity implements View.OnClickListener {
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

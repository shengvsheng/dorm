package cn.edu.pku.wangyongsheng.dorm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * 封装一个FragmentActivity基类，使得结构清晰
 * Created by xiaoshengsheng
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
    //子类继承可复写的保存状态的方法
    protected void initParam(Bundle savedInstanceState) {
    }


    //子类继承需要实现的绑定布局方法
    protected abstract int setRootViewId();
    //子类继承需要实现的初始化布局方法
    protected abstract void initView();
    //子类继承需要实现的初始化数据方法
    protected abstract void initData();
    //子类继承需要实现的监听方法
    protected abstract void initListener();

}

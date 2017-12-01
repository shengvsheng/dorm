package cn.edu.pku.wangyongsheng.dorm.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 封装Fragment基类，使得子类继承代码结构化
 * Created by xiaoshengsheng
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    View mRootView;
    private Intent intent;

    //视图创建初始化控件和设置监听
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(setRootViewId(), container, false);
            initView(mRootView);
            initListener();
        }
        return mRootView;
    }
    //视图创建完毕后加载数据
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    //子类继承需要实现的绑定布局方法
    protected abstract int setRootViewId();
    //子类继承需要实现的初始化布局方法
    protected abstract void initView(View view);
    //子类继承需要实现的初始化数据方法
    protected abstract void initData();
    //子类继承需要实现的监听方法
    protected abstract void initListener();
    /**
     * 给子类提供一个简单的Intent通信方法
     * 通过createIntent().excuteActivity()或者带有RequestCode的Intent，通过
     * createIntent().excuteActivityForResult()
     */

    protected BaseFragment createIntent(Context context, Class<?> cls) {
        intent = new Intent(context, cls);
        return this;
    }
    protected BaseFragment createIntent(Context context, Class<?> cls,String key,String value) {
        intent = new Intent(context, cls);
        intent.putExtra(key, value);
        return this;
    }
    protected void excuteActivity() {
        startActivity(intent);

    }

    protected void excuteActivity(Bundle option) {
        startActivity(intent, option);

    }

    protected void excuteActivityForResult(int requestCode, Bundle bundle) {
        if (bundle != null) {
            startActivityForResult(intent, requestCode, bundle);
        } else {
            excuteActivityForResult(requestCode);
        }
    }

    protected void excuteActivityForResult(int requestCode) {
        startActivityForResult(intent, requestCode);
    }
}

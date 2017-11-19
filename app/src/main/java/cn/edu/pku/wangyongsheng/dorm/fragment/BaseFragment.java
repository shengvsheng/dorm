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
 * Created by xiaoshengsheng on 2017/11/3.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    View mRootView;
    private Intent intent;


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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract int setRootViewId();

    protected abstract void initView(View view);

    protected abstract void initListener();

    protected abstract void initData();

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

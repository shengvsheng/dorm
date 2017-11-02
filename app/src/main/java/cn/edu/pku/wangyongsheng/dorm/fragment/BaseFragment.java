package cn.edu.pku.wangyongsheng.dorm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaoshengsheng on 2017/11/3.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(setRootViewId(), container, false);
        initView(mRootView);
        initListener();
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
}

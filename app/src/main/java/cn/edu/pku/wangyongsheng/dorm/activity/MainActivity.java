package cn.edu.pku.wangyongsheng.dorm.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.edu.pku.wangyongsheng.dorm.R;

import cn.edu.pku.wangyongsheng.dorm.fragment.MeFragment;
import cn.edu.pku.wangyongsheng.dorm.fragment.MessageFragment;

public class MainActivity extends BaseFragmentActivity {
    private FragmentManager mFragemntManager;
    private FragmentTransaction mFragmentTransaction;
    private MessageFragment mMessageFragment;
    private MeFragment mMeFragment;
    private LinearLayout ll_message, ll_me;
    private ImageView iv_me, iv_message;

    @Override
    protected int setRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        iv_me = findViewById(R.id.iv_me);
        iv_message = findViewById(R.id.iv_message);
        ll_message = findViewById(R.id.ll_message);
        ll_me = findViewById(R.id.ll_me);
        replaceFragment(mMessageFragment, R.id.ll_message);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        ll_message.setOnClickListener(this);
        ll_me.setOnClickListener(this);
    }

    private void replaceFragment(Fragment fragment, int resId) {
        if (fragment == null) {

            if (resId == R.id.ll_message) {
                fragment = new MessageFragment();
            }
            if (resId == R.id.ll_me) {
                fragment = new MeFragment();
            }

        }
        if (resId == R.id.ll_message) {
            iv_me.setSelected(false);
            iv_message.setSelected(true);
            ll_me.setBackgroundResource(R.drawable.bg_unselected);
            ll_message.setBackgroundResource(R.drawable.bg_selected);
        }
        if (resId == R.id.ll_me) {
            iv_message.setSelected(false);
            iv_me.setSelected(true);
            ll_message.setBackgroundResource(R.drawable.bg_unselected);
            ll_me.setBackgroundResource(R.drawable.bg_selected);
        }
        mFragemntManager = getSupportFragmentManager();
        mFragmentTransaction = mFragemntManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_content, fragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_message:
                replaceFragment(mMessageFragment, R.id.ll_message);
                break;
            case R.id.ll_me:
                replaceFragment(mMeFragment, R.id.ll_me);
                break;
        }
    }
}

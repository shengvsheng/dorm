package cn.edu.pku.wangyongsheng.dorm.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.wangyongsheng.dorm.R;
import cn.edu.pku.wangyongsheng.dorm.utils.adpter.InfoFragmentPageAdapter;

/**
 * Created by xiaoshengsheng on 2017/10/25.
 */

public class MessageFragment extends BaseFragment {
    private View view;
    private ViewPager vp_info;
    private List<Fragment> fragmentList;
    private LinearLayout ll_news;
    private LinearLayout ll_notice;


    @Override
    protected int setRootViewId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view) {
        vp_info = view.findViewById(R.id.vp_info);
        ll_news = view.findViewById(R.id.ll_news);
        ll_notice = view.findViewById(R.id.ll_notice);
        fragmentList = new ArrayList<>();
    }

    @Override
    protected void initListener() {
        ll_notice.setOnClickListener(this);
        ll_news.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Fragment newsFragment = new NewsFragment();
        Fragment noticeFragment = new NoticeFragment();
        fragmentList.add(noticeFragment);
        fragmentList.add(newsFragment);
        FragmentManager fm = getChildFragmentManager();
        InfoFragmentPageAdapter newsFragmentPageAdapter = new InfoFragmentPageAdapter(fm, fragmentList); //new myFragmentPagerAdater记得带上两个参数

        vp_info.setAdapter(newsFragmentPageAdapter);
        vp_info.setCurrentItem(0);
        ll_notice.setSelected(true);
        vp_info.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    ll_notice.setSelected(true);
                    ll_news.setSelected(false);
                }
                if (position==1){
                    ll_notice.setSelected(false);
                    ll_news.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
//        //mProgressDialog.setContentView(R.layout.progress_loading);
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        mProgressDialog.setMessage("正在加载...");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_notice:
                vp_info.setCurrentItem(0); //设置当前页是第一页
                ll_notice.setSelected(true);
                ll_news.setSelected(false);
                break;
            case R.id.ll_news:
                vp_info.setCurrentItem(1); //设置当前页是第一页
                ll_notice.setSelected(false);
                ll_news.setSelected(true);
                break;

        }
    }


}

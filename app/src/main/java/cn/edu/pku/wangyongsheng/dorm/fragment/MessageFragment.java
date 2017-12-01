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

    //绑定布局文件
    @Override
    protected int setRootViewId() {
        return R.layout.fragment_message;
    }
    //初始化控件
    @Override
    protected void initView(View view) {
        vp_info = view.findViewById(R.id.vp_info);
        ll_news = view.findViewById(R.id.ll_news);
        ll_notice = view.findViewById(R.id.ll_notice);
        fragmentList = new ArrayList<>();
    }
    //设置监听
    @Override
    protected void initListener() {
        ll_notice.setOnClickListener(this);
        ll_news.setOnClickListener(this);
    }
    //初始化数据
    @Override
    protected void initData() {
        Fragment newsFragment = new NewsFragment();
        Fragment noticeFragment = new NoticeFragment();
        fragmentList.add(noticeFragment);
        fragmentList.add(newsFragment);
        FragmentManager fm = getChildFragmentManager();
        InfoFragmentPageAdapter newsFragmentPageAdapter = new InfoFragmentPageAdapter(fm, fragmentList); //new myFragmentPagerAdater记得带上两个参数
        //ViewPage适配Fragment
        vp_info.setAdapter(newsFragmentPageAdapter);
        vp_info.setCurrentItem(0);
        ll_notice.setSelected(true);
        //ViewPage设置换页监听
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
    }
    //复写onClick()方法，对不同的点击做出事件响应
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

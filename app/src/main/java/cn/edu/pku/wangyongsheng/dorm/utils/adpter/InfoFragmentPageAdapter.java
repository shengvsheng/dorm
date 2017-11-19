package cn.edu.pku.wangyongsheng.dorm.utils.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xiaoshengsheng on 2017/11/19.
 */

public class InfoFragmentPageAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmetnmanager;
    private List<Fragment> listfragment;
    public InfoFragmentPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmetnmanager=fm;
        this.listfragment=list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return listfragment.get(arg0);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

}

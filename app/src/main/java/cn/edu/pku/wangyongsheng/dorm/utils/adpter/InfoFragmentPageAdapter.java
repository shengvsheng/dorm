package cn.edu.pku.wangyongsheng.dorm.utils.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *ViewPage的Fragment适配器
 * Created by xiaoshengsheng
 */

public class InfoFragmentPageAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmetnmanager;
    private List<Fragment> listfragment;
    //提供有参构造方法，供初始化，传递参数
    public InfoFragmentPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmetnmanager=fm;
        this.listfragment=list;
    }
    //得到当前Fragment对象
    @Override
    public Fragment getItem(int arg0) {
        return listfragment.get(arg0);
    }
    //得到适配的Fragment对象个数
    @Override
    public int getCount() {
        return listfragment.size();
    }

}

package cn.edu.pku.wangyongsheng.dorm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.pku.wangyongsheng.dorm.R;

/**
 * Created by xiaoshengsheng on 2017/10/31.
 */

public class IndexFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_index,container,false);
        return view;
    }
}

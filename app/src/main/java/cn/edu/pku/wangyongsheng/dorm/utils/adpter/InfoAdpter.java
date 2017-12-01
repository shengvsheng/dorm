package cn.edu.pku.wangyongsheng.dorm.utils.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.pku.wangyongsheng.dorm.R;
import cn.edu.pku.wangyongsheng.dorm.bean.Info;

/**
 * 新闻、通知的适配器，继承BaseAdapter，实现必须复写的方法
 * Created by xiaoshengsheng
 */

public class InfoAdpter extends BaseAdapter {
    private Context context;
    private List<Info> infoList;
    //提供有参构造方法，供初始化，传递参数
    public InfoAdpter(Context context, List<Info> infoList){
        this.context=context;
        this.infoList = infoList;
    }
    //得到内容的个数
    @Override
    public int getCount() {
        return infoList.size();
    }
    //根据postion得到单个内容对象
    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }
    //根据position得到当前内容的Id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //绑定适配的子布局
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.fragment_message_list_item,parent,false);
        TextView tv_title=convertView.findViewById(R.id.tv_title);
        TextView tv_time=convertView.findViewById(R.id.tv_time);
        tv_title.setText(infoList.get(position).getTitle());
        tv_time.setText(infoList.get(position).getTime());
        return convertView;
    }
}

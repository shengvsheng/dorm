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
 * Created by xiaoshengsheng on 2017/11/19.
 */

public class InfoAdpter extends BaseAdapter {
    private Context context;
    private List<Info> infoList;
    public InfoAdpter(Context context, List<Info> infoList){
        this.context=context;
        this.infoList = infoList;
    }
    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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

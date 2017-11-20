package cn.edu.pku.wangyongsheng.dorm.fragment;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.wangyongsheng.dorm.R;
import cn.edu.pku.wangyongsheng.dorm.activity.ContentActivity;
import cn.edu.pku.wangyongsheng.dorm.bean.Info;
import cn.edu.pku.wangyongsheng.dorm.utils.adpter.InfoAdpter;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by xiaoshengsheng on 2017/11/19.
 */

public class NoticeFragment extends BaseFragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Handler mHandle;
    private ListView lv_news;
    private List<Info> infoList;
    private SwipeRefreshLayout srl_reload;
    @Override
    protected int setRootViewId() {
        return R.layout.fragment_notice;
    }
    @Override
    protected void initView(View view) {
        infoList =new ArrayList<>();
        lv_news=view.findViewById(R.id.lv_news);
        srl_reload=view.findViewById(R.id.srl_reload);

        sharedPreferences = getActivity().getSharedPreferences("info", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void initListener() {
        srl_reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_reload.setRefreshing(true);
                getInfo("20");
                srl_reload.setRefreshing(false);
            }
        });
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createIntent(getActivity(), ContentActivity.class,"href", infoList.get(position).getHref()).excuteActivity();
            }
        });
    }

    @Override
    protected void initData() {
        String notices=sharedPreferences.getString("notices","NULL");
        if(!notices.equals("NULL")){
            List<Info> list=JSON.parseArray(notices,Info.class);
            infoList=list;
            lv_news.setAdapter(new InfoAdpter(getActivity(), list));
        }else {
            getInfo("1");

        }
        mHandle=new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:lv_news.setAdapter(new InfoAdpter(getActivity(), infoList));
                        editor.putString("notices",JSON.toJSONString(infoList));
                        editor.commit();break;
                }
            }

        };

    }

    private void getInfo(final String page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://www.ss.pku.edu.cn/index.php/newscenter/notice?start="+page).get();
                    Elements els = doc.getElementById("info-list-ul").getAllElements();
                    for (int i=1;i<els.size();i+=4){
                        Elements elements=els.get(i).getAllElements();
                        if (elements.size()==4){
                            Info info =new Info();
                            info.setHref(elements.get(1).attr("href"));
                            info.setTime(elements.get(2).text());
                            info.setTitle(elements.get(3).text());
                            infoList.add(info);
                        }
                    }
                    Message msg=new Message();
                    msg.what=1;
                    mHandle.sendMessage(msg);
                }catch (Exception e){
                    Log.e("Exception",e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {

    }
}

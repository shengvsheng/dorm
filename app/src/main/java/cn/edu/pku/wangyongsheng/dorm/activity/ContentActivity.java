package cn.edu.pku.wangyongsheng.dorm.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.pku.wangyongsheng.dorm.R;

/**
 * Created by xiaoshengsheng on 2017/11/19.
 */

public class ContentActivity extends BaseActicity {
    private WebView wv_content;
    private Handler mHandle;
    private ImageView iv_close;

    @Override
    protected int setRootViewId() {
        return R.layout.activity_content;
    }

    @Override
    protected void initView() {
        wv_content = findViewById(R.id.wv_content);
        iv_close=findViewById(R.id.iv_close);
    }

    @Override
    protected void initData() {
        getInfo(getIntent().getStringExtra("href"));
        mHandle = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.i("Mss", msg.obj.toString());
                    loadWebView(msg.obj.toString());
                }
            }

        };
    }

    private void loadWebView(String msg) {
        wv_content.getSettings().setJavaScriptEnabled(true);
        wv_content.getSettings().setSupportZoom(true);
        wv_content.getSettings().setBuiltInZoomControls(true);
        wv_content.getSettings().setDisplayZoomControls(false);
        wv_content.getSettings().setDefaultTextEncodingName("UTF-8");
        wv_content.loadData("<html><body>" + msg + "</body></html>", "text/html; charset=UTF-8", null);
    }

    @Override
    protected void initListener() {
            iv_close.setOnClickListener(this);
    }

    private void getInfo(final String href) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://www.ss.pku.edu.cn" + href).get();
                    Elements acticle_title = doc.select("div.article-title");
                    Elements article_info = doc.select("div.article-info");
                    Elements article_content = doc.select("div.article-content");
                    String str = "<div class=\"item-page\"><div class=\"article-title\">" + acticle_title.get(0).text() + "</div><div class=\"article-info muted\">" + article_info.get(0).text() + "</div><div class=\"article-content\">" + article_content.get(0).html() + "</div></div>";
                    if (str.contains("img")) {
                        str = str.replace("/images/images/", "http://www.ss.pku.edu.cn/images/images/");
                        str = str.replace("width=\"550\"", "width=\"300\"");
                        str = str.replace("height=\"368\"", "height=\"200\"");
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = str;
                    mHandle.sendMessage(msg);
                } catch (Exception e) {
                    Log.e("Exceptionssssss", e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iv_close){
           onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

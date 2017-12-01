package cn.edu.pku.wangyongsheng.dorm.bean;

/**
 * 新闻、通知的JAVA Bean类
 * 包括发布时间、链接和标题
 * 以及提供set、get方法
 * Created by xiaoshengsheng
 */

public class Info {
    private String time;
    private String href;
    private String title;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

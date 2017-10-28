package cn.edu.pku.wangyongsheng.dorm.app;


import android.app.Application;

/**
 * Created by xiaoshengsheng on 2017/10/25.
 */

public class DormApplication extends Application {
    private static DormApplication mDormApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mDormApplication=this;
    }
    public static DormApplication getDormApplicationInstance(){
        return mDormApplication;
    }
}

package cn.edu.pku.wangyongsheng.dorm.utils.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by xiaoshengsheng on 2017/10/29.
 */

public class VolleyManager {
    private RequestQueue mRequestQueue;
    private static VolleyManager mVolleyManager = new VolleyManager();

    private VolleyManager() {
    }

    public static VolleyManager getInstance() {
        return mVolleyManager;
    }


    public RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }
}

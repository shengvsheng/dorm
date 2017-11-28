package cn.edu.pku.wangyongsheng.dorm.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import cn.edu.pku.wangyongsheng.dorm.R;
import cn.edu.pku.wangyongsheng.dorm.activity.LoginActivity;
import cn.edu.pku.wangyongsheng.dorm.activity.SelectDormActivity;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by xiaoshengsheng on 2017/10/25.
 */

public class MeFragment extends BaseFragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LinearLayout ll_no_status, ll_no_room,ll_map,ll_vcode;
    private TextView tv_name, tv_username, tv_gender, tv_location, tv_grade, tv_status, tv_vcode, tv_house_number, tv_house_id;
    private ScrollView sv_status;
    private boolean login_status;
    private Button btn_select;
    private ImageView iv_setting;
    private final static int REQUEST_CODE_LOGIN = 0;
    private final static int REQUEST_CODE_DORM = 1;

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
        ll_no_status = view.findViewById(R.id.ll_no_status);
        ll_no_room = view.findViewById(R.id.ll_no_room);
        ll_map = view.findViewById(R.id.ll_map);
        ll_vcode=view.findViewById(R.id.ll_vcode);
        iv_setting=view.findViewById(R.id.iv_setting);
        sv_status = view.findViewById(R.id.sv_status);
        btn_select=view.findViewById(R.id.btn_select);
        tv_name = view.findViewById(R.id.tv_name);
        tv_username = view.findViewById(R.id.tv_username);
        tv_gender = view.findViewById(R.id.tv_gender);
        tv_location = view.findViewById(R.id.tv_location);
        tv_grade = view.findViewById(R.id.tv_grade);
        tv_status = view.findViewById(R.id.tv_status);
        tv_vcode = view.findViewById(R.id.tv_vcode);
        tv_house_number = view.findViewById(R.id.tv_house_number);
        tv_house_id = view.findViewById(R.id.tv_house_id);

        sharedPreferences = getActivity().getSharedPreferences("status", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        login_status = sharedPreferences.getBoolean("LOGIN_STATUS", false);
    }

    @Override
    protected void initListener() {
        ll_no_status.setOnClickListener(this);
        btn_select.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (login_status) {
            iv_setting.setVisibility(View.VISIBLE);
            ll_no_status.setVisibility(View.GONE);
            sv_status.setVisibility(View.VISIBLE);
            loadData();
        }else {
            iv_setting.setVisibility(View.GONE);
            ll_no_status.setVisibility(View.VISIBLE);
            sv_status.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_no_status:
                createIntent(getActivity(), LoginActivity.class).excuteActivityForResult(REQUEST_CODE_LOGIN);
                break;
            case R.id.btn_select:
                createIntent(getActivity(), SelectDormActivity.class).excuteActivityForResult(REQUEST_CODE_DORM);
                break;
            case R.id.iv_setting:
                showExitDialog();
                break;
        }

    }

    private void showExitDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setMessage("确定要退出登录么?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.clear();
                        editor.commit();
                        initData();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_LOGIN:
                    Log.i("Request", data.getStringExtra("BODY"));
                    getUserInfo(data.getStringExtra("USERNAME"));
                    break;
                case REQUEST_CODE_DORM:break;
            }
        }
    }

    private void getUserInfo(String stuid) {
        String url = "https://api.mysspku.com/index.php/V1/MobileCourse/getDetail?stuid=" + stuid;
        OkGo.<String>get(url).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                JSONObject jsonObject = JSON.parseObject(response.body());
                int errcode = jsonObject.getInteger("errcode");
                if (errcode == 0) {
                    JSONObject jsonObj = jsonObject.getJSONObject("data");
                    editor.putString("USERNAME", jsonObj.getString("studentid"));
                    editor.putString("NAME", jsonObj.getString("name"));
                    editor.putString("GENDER", jsonObj.getString("gender"));
                    editor.putString("VCODE", jsonObj.getString("vcode"));
                    editor.putString("LOCATION", jsonObj.getString("location"));
                    editor.putString("GRADE", jsonObj.getString("grade"));
                    if (Integer.valueOf(jsonObj.getString("studentid")) % 2 == 0) {
                        editor.putString("ROOM", jsonObj.getString("room"));
                        editor.putString("BUILDING", jsonObj.getString("building"));
                    }
                    editor.commit();
                    ll_no_status.setVisibility(View.GONE);
                    sv_status.setVisibility(View.VISIBLE);
                    iv_setting.setVisibility(View.VISIBLE);
                    loadData();
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadData() {

        tv_name.setText(sharedPreferences.getString("NAME", "NULL"));
        tv_username.setText(sharedPreferences.getString("USERNAME", "NULL"));
        tv_gender.setText(sharedPreferences.getString("GENDER", "NULL"));
        tv_location.setText(sharedPreferences.getString("LOCATION", "NULL"));
        tv_grade.setText(sharedPreferences.getString("GRADE", "NULL"));
        tv_vcode.setText(sharedPreferences.getString("VCODE", "NULL"));
        if (Integer.valueOf(sharedPreferences.getString("USERNAME", "NULL")) % 2 == 0) {
            ll_no_room.setVisibility(View.VISIBLE);
            ll_vcode.setVisibility(View.GONE);
            tv_status.setText("已经完成办理");
            btn_select.setVisibility(View.GONE);
            ll_map.setVisibility(View.VISIBLE);
            tv_house_id.setText(sharedPreferences.getString("ROOM", "NULL"));
            tv_house_number.setText(sharedPreferences.getString("BUILDING", "NULL"));
        } else {
            tv_status.setText("未完成办理");
            btn_select.setVisibility(View.VISIBLE);
            ll_vcode.setVisibility(View.VISIBLE);
            ll_no_room.setVisibility(View.GONE);
            ll_map.setVisibility(View.GONE);
        }
    }
}

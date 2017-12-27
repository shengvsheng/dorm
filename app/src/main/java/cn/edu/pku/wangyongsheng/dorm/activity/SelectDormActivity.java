package cn.edu.pku.wangyongsheng.dorm.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.wangyongsheng.dorm.R;

/**
 * Created by xiaoshengsheng on 2017/11/20.
 */

public class SelectDormActivity extends BaseActicity {
    private Spinner sp_numbers;
    private Spinner sp_dorm_no;
    private LinearLayout ll_user1, ll_user2, ll_user3;
    private LinearLayout ll_5, ll_8, ll_9, ll_13, ll_14;
    private Button btn_submit;
    private ImageView iv_back;
    private ImageView iv_5, iv_8, iv_9, iv_13, iv_14;
    private List<String> dorms;
    private TextView tv_user_no, tv_house_5, tv_house_8, tv_house_9, tv_house_13, tv_house_14;
    private EditText et_user1_no, et_user2_no, et_user3_no;
    private EditText et_user1_code, et_user2_code, et_user3_code;
    private SharedPreferences sharedPreferences;
    private Handler mHandler;
    int seconds = 3;

    //绑定布局文件
    @Override
    protected int setRootViewId() {
        return R.layout.activity_selectdorm;
    }

    //初始化控件
    @Override
    protected void initView() {
        dorms = new ArrayList<>();
        sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        sp_dorm_no = findViewById(R.id.sp_dorm_no);
        sp_numbers = findViewById(R.id.sp_numbers);
        ll_user1 = findViewById(R.id.ll_user1);
        ll_user2 = findViewById(R.id.ll_user2);
        ll_user3 = findViewById(R.id.ll_user3);
        ll_5 = findViewById(R.id.ll_5);
        ll_8 = findViewById(R.id.ll_8);
        ll_9 = findViewById(R.id.ll_9);
        ll_13 = findViewById(R.id.ll_13);
        ll_14 = findViewById(R.id.ll_14);
        iv_5 = findViewById(R.id.iv_5);
        iv_8 = findViewById(R.id.iv_8);
        iv_9 = findViewById(R.id.iv_9);
        iv_13 = findViewById(R.id.iv_13);
        iv_14 = findViewById(R.id.iv_14);
        tv_user_no = findViewById(R.id.tv_user_no);
        iv_back = findViewById(R.id.iv_back);
        et_user1_no = findViewById(R.id.et_user1_no);
        et_user2_no = findViewById(R.id.et_user2_no);
        et_user3_no = findViewById(R.id.et_user3_no);
        et_user1_code = findViewById(R.id.et_user1_code);
        et_user2_code = findViewById(R.id.et_user2_code);
        et_user3_code = findViewById(R.id.et_user3_code);

        tv_house_5 = findViewById(R.id.tv_house_5);
        tv_house_8 = findViewById(R.id.tv_house_8);
        tv_house_9 = findViewById(R.id.tv_house_9);
        tv_house_13 = findViewById(R.id.tv_house_13);
        tv_house_14 = findViewById(R.id.tv_house_14);
        btn_submit = findViewById(R.id.btn_submit);
    }

    //初始化数据
    @Override
    protected void initData() {
        tv_user_no.setText(sharedPreferences.getString("USERNAME", "NULL"));
        startAnim();
        switchGender();
        makeDialog();
        addHandle();
    }

    //添加Handler方法，根据传入的message做出响应
    private void addHandle() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        btn_submit.setText("3秒回到主页，倒计时：" + String.valueOf(msg.obj));
                        if (Integer.valueOf(String.valueOf(msg.obj)) == 1) {
                            finish();
                        }
                        break;
                }
            }
        };
    }

    //添加动画的方法，在加载宿舍剩余的个数显示，加载完成就设置不可见
    private void startAnim() {
        ((AnimationDrawable) iv_5.getDrawable()).start();
        ((AnimationDrawable) iv_8.getDrawable()).start();
        ((AnimationDrawable) iv_9.getDrawable()).start();
        ((AnimationDrawable) iv_13.getDrawable()).start();
        ((AnimationDrawable) iv_14.getDrawable()).start();
        ll_5.setVisibility(View.VISIBLE);
        ll_8.setVisibility(View.VISIBLE);
        ll_9.setVisibility(View.VISIBLE);
        ll_13.setVisibility(View.VISIBLE);
        ll_14.setVisibility(View.VISIBLE);
        tv_house_5.setVisibility(View.GONE);
        tv_house_8.setVisibility(View.GONE);
        tv_house_9.setVisibility(View.GONE);
        tv_house_13.setVisibility(View.GONE);
        tv_house_14.setVisibility(View.GONE);
    }

    //设置监听
    @Override
    protected void initListener() {

        sp_numbers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ll_user1.setVisibility(View.GONE);
                        ll_user2.setVisibility(View.GONE);
                        ll_user3.setVisibility(View.GONE);
                        break;
                    case 1:
                        ll_user1.setVisibility(View.VISIBLE);
                        ll_user2.setVisibility(View.GONE);
                        ll_user3.setVisibility(View.GONE);
                        break;
                    case 2:
                        ll_user1.setVisibility(View.VISIBLE);
                        ll_user2.setVisibility(View.VISIBLE);
                        ll_user3.setVisibility(View.GONE);
                        break;
                    case 3:
                        ll_user1.setVisibility(View.VISIBLE);
                        ll_user2.setVisibility(View.VISIBLE);
                        ll_user3.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_submit.setOnClickListener(this);
        iv_back.setOnClickListener(this);

    }

    //复写onClick()方法
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submitDorm(sp_numbers.getSelectedItem().toString());
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    //根据不同性别，调用不同的api，得到宿舍的剩余数量
    private void switchGender() {
        String gender = sharedPreferences.getString("GENDER", "男");
        if (gender.equals("男")) {
            getDormsNumber("1");
            dorms.add("5号楼");
            dorms.add("9号楼");
            dorms.add("13号楼");
        }
        if (gender.equals("女")) {
            getDormsNumber("2");
            dorms.add("5号楼");
            dorms.add("8号楼");
            dorms.add("14号楼");
        }
        ArrayAdapter<String> dormsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dorms);
        sp_dorm_no.setAdapter(dormsAdapter);
        dormsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    /**
     * 得到宿舍房间剩余数方法，使用OKGo访问网络框架，对结果做出判断
     * 若返回成功，更新UI，不成功，设置加载失败
     */
    private void getDormsNumber(String gender) {

        String url = "https://api.mysspku.com/index.php/V1/MobileCourse/getRoom?gender=" + gender;
        OkGo.<String>get(url).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                JSONObject jsonObject = JSON.parseObject(response.body());
                int errcode = jsonObject.getInteger("errcode");
                if (errcode == 0) {

                    JSONObject jsonObj = JSON.parseObject(jsonObject.getString("data"));
                    tv_house_5.setText(jsonObj.getString("5"));
                    tv_house_8.setText(jsonObj.getString("8"));
                    tv_house_9.setText(jsonObj.getString("9"));
                    tv_house_13.setText(jsonObj.getString("13"));
                    tv_house_14.setText(jsonObj.getString("14"));
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                tv_house_5.setText("获取失败");
                tv_house_8.setText("获取失败");
                tv_house_9.setText("获取失败");
                tv_house_13.setText("获取失败");
                tv_house_14.setText("获取失败");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                ll_5.setVisibility(View.GONE);
                ll_8.setVisibility(View.GONE);
                ll_9.setVisibility(View.GONE);
                ll_13.setVisibility(View.GONE);
                ll_14.setVisibility(View.GONE);
                tv_house_5.setVisibility(View.VISIBLE);
                tv_house_8.setVisibility(View.VISIBLE);
                tv_house_9.setVisibility(View.VISIBLE);
                tv_house_13.setVisibility(View.VISIBLE);
                tv_house_14.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * 选宿舍方法，使用OKGo访问网络框架，对结果做出判断
     * 若返回成功，提示选择成功，并倒计时3秒，返回主页，不成功，提示失败
     */
    private void selectDorm(int num, int buildingNo, String stuid, String stu1id, String v1code, String stu2id, String v2code, String stu3id, String v3code) {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("正在提交...");
        mProgressDialog.show();
        String url = "https://api.mysspku.com/index.php/V1/MobileCourse/SelectRoom";
        OkGo.<String>post(url).params("num", num).params("buildingNo", buildingNo).params("stuid", stuid).params("stu1id", stu1id).params("v1code", v1code).params("stu2id", stu1id).params("v2code", v1code).params("stu3id", stu1id).params("v3code", v1code).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                JSONObject jsonObject = JSON.parseObject(response.body());
                int errcode = jsonObject.getInteger("errcode");
                if (errcode == 0) {
                    makeToast("选择成功");
                    btn_submit.setEnabled(false);
                    et_user1_no.setEnabled(false);
                    et_user1_code.setEnabled(false);
                    et_user2_no.setEnabled(false);
                    et_user2_code.setEnabled(false);
                    et_user3_no.setEnabled(false);
                    et_user3_code.setEnabled(false);
                    addTimer(mHandler);

                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                Toast.makeText(SelectDormActivity.this, response.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mProgressDialog.cancel();
            }
        });
    }

    //倒计时方法，供选择成功后，倒计时返回主页
    private void addTimer(final Handler handler) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        Message message = new Message();
                        message.what = 1;
                        message.obj = seconds;
                        handler.sendMessage(message);
                        seconds--;
                        if (seconds == 0) {
                            break;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * 进入选宿舍界面，首先弹出的注意事项Dialog
     * 通过AlertDialog.Builder构造一个dialog
     * 自定义一个view，供dialog绑定
     */
    private void makeDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_message, null);
        ImageView iv_close = dialogView.findViewById(R.id.iv_close);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.height = (int) (d.getHeight() * 0.6);
        p.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    //选择宿舍成功后，提示成功的Toast方法
    private void makeToast(String text) {
        Toast toast = Toast.makeText(SelectDormActivity.this,
                text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(getApplicationContext());
        imageCodeProject.setImageResource(R.drawable.ic_ok);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    //根据选择几人同时选宿舍，传递不同的参数，选择宿舍方法
    private void submitDorm(String numbers) {
        if (numbers.equals("单人办理")) {
            selectDorm(1, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), "", "", "", "", "", "");
        }
        if (numbers.equals("2人办理")) {
            if (!TextUtils.isEmpty(et_user1_no.getText()) && !TextUtils.isEmpty(et_user1_code.getText())) {
                if (!et_user1_no.getText().toString().equals(sharedPreferences.getString("USERNAME", "NULL"))) {
                    selectDorm(2, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), String.valueOf(et_user1_no.getText()), String.valueOf(et_user1_code.getText()), "", "", "", "");
                } else {
                    Toast.makeText(SelectDormActivity.this, "同住人学号重复~", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(SelectDormActivity.this, "你还有一下信息没有填写~", Toast.LENGTH_LONG).show();
            }
        }
        if (numbers.equals("3人办理")) {
            if (!TextUtils.isEmpty(et_user1_no.getText()) && !TextUtils.isEmpty(et_user1_code.getText()) && !TextUtils.isEmpty(et_user2_no.getText()) && !TextUtils.isEmpty(et_user2_code.getText())) {
                if (!et_user1_no.getText().toString().equals(sharedPreferences.getString("USERNAME", "NULL")) && !et_user2_no.getText().toString().equals(sharedPreferences.getString("USERNAME", "NULL")) && !et_user1_no.getText().toString().equals(et_user2_no.getText().toString())) {
                    selectDorm(3, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), String.valueOf(et_user1_no.getText()), String.valueOf(et_user1_code.getText()), String.valueOf(et_user2_no.getText()), String.valueOf(et_user2_code.getText()), "", "");
                } else {
                    Toast.makeText(SelectDormActivity.this, "同住人学号重复~", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(SelectDormActivity.this, "你还有一下信息没有填写~", Toast.LENGTH_LONG).show();
            }
        }
        if (numbers.equals("4人办理")) {
            if (!TextUtils.isEmpty(et_user1_no.getText()) && !TextUtils.isEmpty(et_user1_code.getText()) && !TextUtils.isEmpty(et_user2_no.getText()) && !TextUtils.isEmpty(et_user2_code.getText()) && !TextUtils.isEmpty(et_user3_no.getText()) && !TextUtils.isEmpty(et_user3_code.getText())) {
                if (!et_user1_no.getText().toString().equals(sharedPreferences.getString("USERNAME", "NULL")) && !et_user2_no.getText().toString().equals(sharedPreferences.getString("USERNAME", "NULL")) && !et_user3_no.getText().toString().equals(sharedPreferences.getString("USERNAME", "NULL")) && !et_user1_no.getText().toString().equals(et_user2_no.getText().toString())
                        && !et_user1_no.getText().toString().equals(et_user3_no.getText().toString()) && !et_user2_no.getText().toString().equals(et_user3_no.getText().toString())) {
                    selectDorm(4, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), String.valueOf(et_user1_no.getText()), String.valueOf(et_user1_code.getText()), String.valueOf(et_user2_no.getText()), String.valueOf(et_user2_code.getText()), String.valueOf(et_user3_no.getText()), String.valueOf(et_user3_code.getText()));
                } else {
                    Toast.makeText(SelectDormActivity.this, "同住人学号重复~", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(SelectDormActivity.this, "你还有一下信息没有填写~", Toast.LENGTH_LONG).show();
            }
        }
    }

}

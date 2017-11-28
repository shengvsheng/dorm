package cn.edu.pku.wangyongsheng.dorm.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import cn.edu.pku.wangyongsheng.dorm.R;

/**
 * Created by xiaoshengsheng on 2017/11/20.
 */

public class SelectDormActivity extends BaseActicity {
    private Spinner sp_numbers;
    private Spinner sp_dorm_no;
    private LinearLayout ll_user1,ll_user2,ll_user3;
    private LinearLayout ll_5,ll_8,ll_9,ll_13,ll_14;
    private Button btn_submit;
    private ImageView iv_back;
    private ImageView iv_5,iv_8,iv_9,iv_13,iv_14;

    private TextView tv_user_no, tv_house_5, tv_house_8, tv_house_9, tv_house_13, tv_house_14;
    private EditText et_user1_no, et_user2_no, et_user3_no;
    private EditText et_user1_code, et_user2_code, et_user3_code;
    private SharedPreferences sharedPreferences;


    @Override
    protected int setRootViewId() {
        return R.layout.activity_selectdorm;
    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        sp_dorm_no = findViewById(R.id.sp_dorm_no);
        sp_numbers = findViewById(R.id.sp_numbers);
        ll_user1 = findViewById(R.id.ll_user1);
        ll_user2 = findViewById(R.id.ll_user2);
        ll_user3 = findViewById(R.id.ll_user3);
        ll_5=findViewById(R.id.ll_5);
        ll_8=findViewById(R.id.ll_8);
        ll_9=findViewById(R.id.ll_9);
        ll_13=findViewById(R.id.ll_13);
        ll_14=findViewById(R.id.ll_14);
        iv_5=findViewById(R.id.iv_5);
        iv_8=findViewById(R.id.iv_8);
        iv_9=findViewById(R.id.iv_9);
        iv_13=findViewById(R.id.iv_13);
        iv_14=findViewById(R.id.iv_14);
        tv_user_no = findViewById(R.id.tv_user_no);
        iv_back=findViewById(R.id.iv_back);
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

    @Override
    protected void initData() {
        tv_user_no.setText(sharedPreferences.getString("USERNAME", "NULL"));
        startAnim();
        switchGender();
    }

    private void startAnim(){
        ((AnimationDrawable)iv_5.getDrawable()).start();
        ((AnimationDrawable)iv_8.getDrawable()).start();
        ((AnimationDrawable)iv_9.getDrawable()).start();
        ((AnimationDrawable)iv_13.getDrawable()).start();
        ((AnimationDrawable)iv_14.getDrawable()).start();
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

    private void switchGender() {
        String gender = sharedPreferences.getString("GENDER", "男");
        if (gender.equals("男")) {
            getDormsNumber("1");
        }
        if (gender.equals("女")) {
            getDormsNumber("2");
        }
    }

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

    private void makeToast(String text) {
         Toast toast = Toast.makeText(SelectDormActivity.this,
                 text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(getApplicationContext());
        imageCodeProject.setImageResource(R.drawable.ic_ok);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    private void submitDorm(String numbers) {
        if (numbers.equals("单人办理")) {
            selectDorm(1, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), "", "", "", "", "", "");
        }
        if (numbers.equals("2人办理")) {
            if (!TextUtils.isEmpty(et_user1_no.getText()) && !TextUtils.isEmpty(et_user1_code.getText())) {
                selectDorm(2, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), String.valueOf(et_user1_no.getText()), String.valueOf(et_user1_code.getText()), "", "", "", "");
            } else {
                Toast.makeText(SelectDormActivity.this, "你还有一下信息没有填写~", Toast.LENGTH_LONG).show();
            }
        }
        if (numbers.equals("3人办理")) {
            if (!TextUtils.isEmpty(et_user1_no.getText()) && !TextUtils.isEmpty(et_user1_code.getText()) && !TextUtils.isEmpty(et_user2_no.getText()) && !TextUtils.isEmpty(et_user2_code.getText())) {
                selectDorm(3, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), String.valueOf(et_user1_no.getText()), String.valueOf(et_user1_code.getText()), String.valueOf(et_user2_no.getText()), String.valueOf(et_user2_code.getText()), "", "");
            } else {
                Toast.makeText(SelectDormActivity.this, "你还有一下信息没有填写~", Toast.LENGTH_LONG).show();
            }
        }
        if (numbers.equals("4人办理")) {
            if (!TextUtils.isEmpty(et_user1_no.getText()) && !TextUtils.isEmpty(et_user1_code.getText()) && !TextUtils.isEmpty(et_user2_no.getText()) && !TextUtils.isEmpty(et_user2_code.getText()) && !TextUtils.isEmpty(et_user3_no.getText()) && !TextUtils.isEmpty(et_user3_code.getText())) {
                selectDorm(4, Integer.valueOf(sp_dorm_no.getSelectedItem().toString().split("号")[0]), String.valueOf(tv_user_no.getText()), String.valueOf(et_user1_no.getText()), String.valueOf(et_user1_code.getText()), String.valueOf(et_user2_no.getText()), String.valueOf(et_user2_code.getText()), String.valueOf(et_user3_no.getText()), String.valueOf(et_user3_code.getText()));
            } else {
                Toast.makeText(SelectDormActivity.this, "你还有一下信息没有填写~", Toast.LENGTH_LONG).show();
            }
        }
    }
}

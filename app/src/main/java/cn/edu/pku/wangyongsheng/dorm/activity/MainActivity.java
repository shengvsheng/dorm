package cn.edu.pku.wangyongsheng.dorm.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.edu.pku.wangyongsheng.dorm.R;
import cn.edu.pku.wangyongsheng.dorm.fragment.MeFragment;
import cn.edu.pku.wangyongsheng.dorm.fragment.MessageFragment;

public class MainActivity extends Activity implements View.OnClickListener{
    private FragmentManager mFragemntManager;
    private FragmentTransaction mFragmentTransaction;
    private MessageFragment mMessageFragment;
    private MeFragment mMeFragment;
    private LinearLayout ll_message;
    private LinearLayout ll_me;
    private ImageView iv_me;
    private ImageView iv_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        iv_me=findViewById(R.id.iv_me);
        iv_message=findViewById(R.id.iv_message);
        ll_message=findViewById(R.id.ll_message);
        ll_me=findViewById(R.id.ll_me);
        ll_message.setOnClickListener(this);
        ll_me.setOnClickListener(this);
        replaceFragment(mMessageFragment,R.id.ll_message);
    }
    private void replaceFragment(Fragment fragment,int resId){
        if (fragment==null){
            if (resId==R.id.ll_message){
                fragment=new MessageFragment();
            }
            if (resId==R.id.ll_me){
                fragment=new MeFragment();
            }

        }
        if (resId==R.id.ll_message){
            iv_me.setSelected(false);


            iv_message.setSelected(true);
            ll_me.setBackgroundResource(R.drawable.bg_unselected);
            ll_message.setBackgroundResource(R.drawable.bg_selected);
        }
        if (resId==R.id.ll_me){
            iv_message.setSelected(false);
            iv_me.setSelected(true);
            ll_message.setBackgroundResource(R.drawable.bg_unselected);
            ll_me.setBackgroundResource(R.drawable.bg_selected);
        }
        mFragemntManager= getFragmentManager();
        mFragmentTransaction=mFragemntManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_content,fragment);
        mFragmentTransaction.commit();


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_message:replaceFragment(mMessageFragment,R.id.ll_message);break;
            case R.id.ll_me:replaceFragment(mMessageFragment,R.id.ll_me);break;
        }
    }
}

package com.example.ztt.city.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.ztt.city.R;
import com.example.ztt.city.utils.tool.SharedPreferencesTool;
import com.example.ztt.city.view.fragement.BookFragment;
import com.example.ztt.city.view.fragement.MessFragment;
import com.example.ztt.city.view.fragement.OtherFragment;
import com.example.ztt.city.view.fragement.ScheduleFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnClickListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;


    private LinearLayout mTabBtnSchedule;
    private LinearLayout mTabBtnBook;
    private LinearLayout mTabBtnMess;
    private LinearLayout mTabBtnOther;

    private ImageButton mImgSchedule;
    private ImageButton mImgBook;
    private ImageButton mImgMess;
    private ImageButton mImgOther;

    private boolean firstStatu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //第一次登陆
        firstLogin();
        initView();
        initEvent();
        setSelect(0);
    }

    /**
     * 判断应用是否第一次登录
     */

    private void firstLogin() {
        //判断程序是否第一次打开
        firstStatu = openApp();
        SharedPreferencesTool mSharedPreferencesTool = new SharedPreferencesTool(this);
        boolean loginStatu = mSharedPreferencesTool.getloginstatu();
        if (!loginStatu) {
            this.finish();
            startActivity(new Intent(this, loginActivity.class));
        }
    }

    private boolean openApp() {

        SharedPreferences appInfo = getSharedPreferences("appInfo", Activity.MODE_PRIVATE);
        boolean appOpenStatu = appInfo.getBoolean("app", false);

        if (appOpenStatu == false) {
            SharedPreferences.Editor editor = appInfo.edit();
            editor.putBoolean("app", true);
            editor.commit();
            return true;
        } else {
            return false;
        }

    }

    /**
     * 监听事件
     */
    private void initEvent() {
        mTabBtnSchedule.setOnClickListener(this);
        mTabBtnBook.setOnClickListener(this);
        mTabBtnMess.setOnClickListener(this);
        mTabBtnOther.setOnClickListener(this);
    }

    /**
     * 进行初始化
     */
    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.id_content);

        mTabBtnSchedule = (LinearLayout) findViewById(R.id.id_tab_bottom_schedule);
        mTabBtnBook = (LinearLayout) findViewById(R.id.id_tab_bottom_book);
        mTabBtnMess = (LinearLayout) findViewById(R.id.id_tab_bottom_mess);
        mTabBtnOther = (LinearLayout) findViewById(R.id.id_tab_bottom_other);

        mImgSchedule = (ImageButton) findViewById(R.id.btn_tab_bottom_schedule);
        mImgBook = (ImageButton) findViewById(R.id.btn_tab_bottom_book);
        mImgMess = (ImageButton) findViewById(R.id.btn_tab_bottom_mess);
        mImgOther = (ImageButton) findViewById(R.id.btn_tab_bottom_other);

        mFragments = new ArrayList<Fragment>();
        Fragment mScheduled = new ScheduleFragment();
        Fragment mBook = new BookFragment();
        Fragment mMess = new MessFragment();
        Fragment mOther = new OtherFragment();
        mFragments.add(mScheduled);
        mFragments.add(mBook);
        mFragments.add(mMess);
        mFragments.add(mOther);
        //适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

    }

    /**
     * 监听点击事件
     */
    @Override
    public void onClick(View v) {

        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_bottom_schedule:
                setSelect(0);
                break;
            case R.id.id_tab_bottom_book:
                setSelect(1);
                break;
            case R.id.id_tab_bottom_mess:
                setSelect(2);
                break;
            case R.id.id_tab_bottom_other:
                setSelect(3);
                break;

            default:
                break;
        }

    }

    /**
     * tab随着滑动而变化
     */
    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
        //下面tab随着滑动而变化
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });


    }

    /**
     * 设置Fragement的图片
     */
    private void setTab(int i) {
        resetImgs();
        //设置图片为亮色
        //切换内容区域
        switch (i) {
            case 0:
                mImgSchedule.setImageResource(R.drawable.schedule_pressed);
                break;
            case 1:
                mImgBook.setImageResource(R.drawable.book_pressed);
                break;
            case 2:
                mImgMess.setImageResource(R.drawable.mess_pressed);
                break;
            case 3:
                mImgOther.setImageResource(R.drawable.other_pressed);
                break;
            default:
                break;
        }
    }

    /**
     * 让图片变为暗色
     */
    private void resetImgs() {
        mImgSchedule.setImageResource(R.drawable.schedule_normal);
        mImgBook.setImageResource(R.drawable.book_normal);
        mImgMess.setImageResource(R.drawable.mess_normal);
        mImgOther.setImageResource(R.drawable.other_normal);
    }




}

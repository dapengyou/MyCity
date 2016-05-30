package com.example.ztt.city.view.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ztt.city.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他界面
 */
public class OtherFragment extends Fragment {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private TabPageIndicator mTabPageIndicator;
    private List<String> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contactsLayout = inflater.inflate(R.layout.fragment_other,
                container, false);

        mViewPager = (ViewPager) contactsLayout.findViewById(R.id.id_viewpager);
        mTabPageIndicator = (TabPageIndicator) contactsLayout.findViewById(R.id.id_indicator);

        //添加fragment
        mFragments = new ArrayList<Fragment>();
        Fragment mTabScore = new scoreFragment();
        Fragment mTabNews = new newsFragment();
        Fragment mTabFit = new fitFragment();

//		scoreFragment mTabScore = new scoreFragment();
//        newsFragment mTabNews = new newsFragment();
//		fitFragment mTabFit = new fitFragment();
        mFragments.add(mTabScore);
        mFragments.add(mTabNews);
        mFragments.add(mTabFit);

        //添加最后一个界面的头标题
        mList = new ArrayList<String>();
        mList.add("成绩");
        mList.add("新闻");
        mList.add("设置");

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            //显示title
            @Override
            public CharSequence getPageTitle(int position) {
                return mList.get(position);
            }

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
        mTabPageIndicator.setViewPager(mViewPager, 0);

        return contactsLayout;
    }

}

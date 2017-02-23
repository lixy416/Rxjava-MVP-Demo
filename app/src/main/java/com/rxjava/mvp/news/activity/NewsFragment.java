package com.rxjava.mvp.news.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxjava.mvp.R;
import com.rxjava.mvp.commoms.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/8 14:47.
 */
public class NewsFragment extends BaseFragment {
    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;
    private List<TabPagerItem> tabList;
    private TabLayout mTablayout;
    private ViewPager mViewPager;

    public NewsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (tabList == null){
            tabList = new ArrayList<>();
        }
        tabList.clear();
        tabList.add(new TabPagerItem(getString(R.string.top),NEWS_TYPE_TOP));
        tabList.add(new TabPagerItem(getString(R.string.nba),NEWS_TYPE_NBA));
        tabList.add(new TabPagerItem(getString(R.string.cars),NEWS_TYPE_CARS));
        tabList.add(new TabPagerItem(getString(R.string.jokes),NEWS_TYPE_JOKES));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news,null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        FragmentPagerAdapter pagerAdapter = new MainFragmentPageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);
        return view;
    }


    /**
     *
     */
    static class TabPagerItem{
        private final CharSequence mTitle;
        private final int tabId;
        public TabPagerItem(CharSequence Title,int tabId){
            mTitle = Title;
            this.tabId = tabId;
        }

        public Fragment crateFragment(int position){
            return NewsListFragment.newInstance(mTitle.toString(),tabId,position);
        }
        public CharSequence getmTitle(){
            return mTitle;
        }
    }

    /**
     * fragment适配器
     */
    class MainFragmentPageAdapter extends FragmentPagerAdapter{

        public MainFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabList.get(position).crateFragment(position);
        }

        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position).getmTitle();
        }
    }
}

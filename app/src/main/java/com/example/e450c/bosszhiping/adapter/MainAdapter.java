package com.example.e450c.bosszhiping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by e450c on 2016/10/24.
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    private List<String> tablists;

    public MainAdapter(FragmentManager fm, List<Fragment> fragmentList,List<String> tabLists) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tablists = tabLists;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablists.get(position);
    }
}

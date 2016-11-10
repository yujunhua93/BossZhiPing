package com.example.e450c.bosszhiping.activity;

import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


import android.view.Window;
import android.widget.TextView;

import com.example.e450c.bosszhiping.R;
import com.example.e450c.bosszhiping.adapter.MainAdapter;
import com.example.e450c.bosszhiping.fragment.CompanyFragment;
import com.example.e450c.bosszhiping.fragment.MessageFragment;
import com.example.e450c.bosszhiping.fragment.PositionFragment;
import com.example.e450c.bosszhiping.fragment.UserFragment;
import com.example.e450c.bosszhiping.widget.MainViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {


//    @InjectView(R.id.main_viewpager)
//    MainViewPager main_viewpager;

    @InjectView(R.id.main_tablayout)
    TabLayout main_tablayout;

//    private List<Fragment> fragmentList;

    private List<String> tabsList;

    private MainAdapter mainAdapter;

    private PositionFragment positionFragment;

    private CompanyFragment companyFragment;

    private MessageFragment messageFragment;

    private UserFragment userFragment;

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.inject(this);
        initFragmentList();
        initEventListener();
    }



    private void initFragmentList() {

        positionFragment = PositionFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.content,positionFragment).commit();
        tabsList = new ArrayList<String>();
        tabsList.add("职位");
        tabsList.add("公司");
        tabsList.add("消息");
        tabsList.add("我的");

//        fragmentList = new ArrayList<Fragment>();
//        fragmentList.add(PositionFragment.newInstance());
//        fragmentList.add(CompanyFragment.newInstance());
//        fragmentList.add(MessageFragment.newInstance());
//        fragmentList.add(UserFragment.newInstance());
//        mainAdapter = new MainAdapter(getSupportFragmentManager(),fragmentList,tabsList);
//        main_viewpager.setAdapter(mainAdapter);
        //重写viewpager让其不能自动滑动
//        main_viewpager.setScrollble(false);
//        main_tablayout.setupWithViewPager(main_viewpager);
        main_tablayout.addTab(main_tablayout.newTab().setText("职位").setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_find_pre)));
        main_tablayout.addTab(main_tablayout.newTab().setText("公司").setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_company_nor)));
        main_tablayout.addTab(main_tablayout.newTab().setText("消息").setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_contacts_nor)));
        main_tablayout.addTab(main_tablayout.newTab().setText("我的").setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_my_nor)));
//        main_tablayout.getTabAt(0).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_find_pre));
//        main_tablayout.getTabAt(1).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_company_nor));
//        main_tablayout.getTabAt(2).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_contacts_nor));
//        main_tablayout.getTabAt(3).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_my_nor));
    }
    private void initEventListener() {
            main_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    if (tab == main_tablayout.getTabAt(0)){
                        if (positionFragment == null){
                            positionFragment = PositionFragment.newInstance();

                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.content,positionFragment).commit();
                        main_tablayout.getTabAt(0).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_find_pre));
//                        main_viewpager.setCurrentItem(0);

                    }
                    if (tab == main_tablayout.getTabAt(1)){
                        if (companyFragment == null){
                            companyFragment = CompanyFragment.newInstance();

                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.content,companyFragment).commit();
                        main_tablayout.getTabAt(1).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_company_pre));
//                        main_viewpager.setCurrentItem(1);

                    }
                    if (tab == main_tablayout.getTabAt(2)){
                        if (messageFragment == null){
                            messageFragment = MessageFragment.newInstance();

                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.content,messageFragment).commit();
                        main_tablayout.getTabAt(2).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_contacts_pre));
//                        main_viewpager.setCurrentItem(2);

                    }
                    if (tab == main_tablayout.getTabAt(3)){
                        if (userFragment == null){
                            userFragment = UserFragment.newInstance();

                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.content,userFragment).commit();
                        main_tablayout.getTabAt(3).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_my_pre));
//                        main_viewpager.setCurrentItem(3);

                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    if (tab == main_tablayout.getTabAt(0)){
                        main_tablayout.getTabAt(0).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_find_nor));
                    }
                    if (tab == main_tablayout.getTabAt(1)){
                        main_tablayout.getTabAt(1).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_company_nor));

                    }
                    if (tab == main_tablayout.getTabAt(2)){
                        main_tablayout.getTabAt(2).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_contacts_nor));

                    }
                    if (tab == main_tablayout.getTabAt(3)){

                        main_tablayout.getTabAt(3).setIcon(getResources().getDrawable(R.mipmap.ic_main_tab_my_nor));
                    }


                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


    }

}

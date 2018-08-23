package com.example.a1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class a1dMerca extends AppCompatActivity {
    public static List<Taskk2>mDatas1;
    public static List<Taskk2>mDatas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1_1);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        //设置adapter，滑动事件
        final ViewPager mViewPager = findViewById(R.id.vp_content);
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //绑定tab点击事件
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "merca work", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    protected void onStop() {
        writeListIntoSDcard("remainRes",mDatas1);
        writeListIntoSDcard("workRes",mDatas2);
        super.onStop();
    }

    /**
     * write into sdcard (object)
     * @param list
     */
    public void writeListIntoSDcard(String str1,List<Taskk2> list){
        if (new OutputUtil<Taskk2>().writeListIntoSDcard(str1, list)) {
           // Toast.makeText(a1dMerca.this, "写入SD卡成功", Toast.LENGTH_SHORT).show();
        }
        else {
           // Toast.makeText(a1dMerca.this, "写入SD卡失败", Toast.LENGTH_SHORT).show();
        }
    }

}

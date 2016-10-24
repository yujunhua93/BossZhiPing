package com.example.e450c.bosszhiping.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by e450c on 2016/10/24.
 */
public class MainViewPager extends ViewPager{

    private boolean scrollble = true;

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble){
            return  true;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }
}

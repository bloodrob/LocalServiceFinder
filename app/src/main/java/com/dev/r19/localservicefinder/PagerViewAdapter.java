package com.dev.r19.localservicefinder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class PagerViewAdapter extends FragmentPagerAdapter{
    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i)
        {
            case 0 : fragment = new Page1();
                     break;
            case 1 : fragment = new Page2();
                     break;
            case 2 : fragment = new Page3();
                     break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

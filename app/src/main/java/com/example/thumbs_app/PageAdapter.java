package com.example.thumbs_app;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mFragmne;
    ArrayList<String> mFragmneList;

    PageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        mFragmne = new ArrayList<>();
        mFragmneList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmne.get(position);
    }

    @Override
    public int getCount() {
        return mFragmne.size();
    }
    public void addFragment(Fragment fragment,String title){
        mFragmne.add(fragment);
        mFragmneList.add(title);

    }

    /////////////////////////////////////////////


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmneList.get(position);
    }
}

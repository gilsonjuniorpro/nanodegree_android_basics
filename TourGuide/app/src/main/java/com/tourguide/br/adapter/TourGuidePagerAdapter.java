package com.tourguide.br.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tourguide.br.fragment.HiddenGemsFragment;
import com.tourguide.br.fragment.MuseumsFragment;
import com.tourguide.br.fragment.PopularFragment;
import com.tourguide.br.fragment.SideTripsFragment;
import com.tourguide.br.util.Dominios;

/**
 * Created by gilsonjuniorpro on 4/24/17.
 */

public class TourGuidePagerAdapter extends FragmentPagerAdapter{

    private final int PAGES = 4;

    private Context mContext;

    public TourGuidePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    private String titles[] = new String[]{
            Dominios.POPULAR,
            Dominios.SIDE_TRIPS,
            Dominios.MUSEUMS,
            Dominios.HIDDEN_GEMS
    };

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = Fragment.instantiate(mContext, PopularFragment.class.getName());
                break;
            case 1:
                fragment = Fragment.instantiate(mContext, SideTripsFragment.class.getName());
                break;
            case 2:
                fragment = Fragment.instantiate(mContext, MuseumsFragment.class.getName());
                break;
            case 3:
                fragment = Fragment.instantiate(mContext, HiddenGemsFragment.class.getName());
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

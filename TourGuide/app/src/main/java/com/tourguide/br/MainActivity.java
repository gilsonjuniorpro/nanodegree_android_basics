package com.tourguide.br;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tourguide.br.adapter.TourGuidePagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        FragmentPagerAdapter tourGuidePagerAdapter = new TourGuidePagerAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(tourGuidePagerAdapter);

        TabLayout tab = (TabLayout)findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);
    }
}

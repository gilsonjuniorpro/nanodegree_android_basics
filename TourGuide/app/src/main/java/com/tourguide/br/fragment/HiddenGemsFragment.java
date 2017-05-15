package com.tourguide.br.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tourguide.br.R;
import com.tourguide.br.adapter.TourGuideAdapter;
import com.tourguide.br.entity.Guide;

import java.util.ArrayList;

/**
 * Created by gilsonjuniorpro on 4/25/17.
 */

public class HiddenGemsFragment extends Fragment {

    public HiddenGemsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hidden_gems, container, false);

        ArrayList<Guide> guides = new ArrayList<>();

        guides.add(new Guide(getString(R.string.title_irishtown), getString(R.string.category_irishtown),
                getString(R.string.duration_irishtown), getString(R.string.contact_irishtown), R.drawable.irishtown_nature_park));

        guides.add(new Guide(getString(R.string.title_mapleton), getString(R.string.category_mapleton),
                getString(R.string.duration_mapleton), getString(R.string.contact_mapleton), R.drawable.mapleton_park));

        guides.add(new Guide(getString(R.string.title_magnetic_hill_winery), getString(R.string.category_magnetic_hill_winery),
                getString(R.string.duration_magnetic_hill_winery), getString(R.string.contact_magnetic_hill_winery), R.drawable.magnetic_hill_winery));

        ListView listView = (ListView) rootView.findViewById(R.id.lvHiddenGems);
        TourGuideAdapter adapter = new TourGuideAdapter(rootView.getContext(), guides);
        listView.setAdapter(adapter);

        return rootView;
    }
}

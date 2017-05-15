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
 * Created by gilsonjuniorpro on 4/24/17.
 */

public class PopularFragment extends Fragment{

    public PopularFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_popular, container, false);

        ArrayList<Guide> guides = new ArrayList<>();

        guides.add(new Guide(getString(R.string.title_magnetic_hill_zoo), getString(R.string.category_magnetic_hill_zoo),
                getString(R.string.duration_magnetic_hill_zoo), getString(R.string.contact_magnetic_hill_zoo), R.drawable.magnetic_hill_zoo));

        guides.add(new Guide(getString(R.string.title_casino_new_brunswick), getString(R.string.category_casino_new_brunswick),
                getString(R.string.duration_casino_new_brunswick), getString(R.string.contact_casino_new_brunswick), R.drawable.casino_new_brunswick));

        guides.add(new Guide(getString(R.string.title_tidal_bore), getString(R.string.category_tidal_bore),
                getString(R.string.duration_tidal_bore), getString(R.string.contact_tidal_bore), R.drawable.tidal_bore));

        ListView listView = (ListView) rootView.findViewById(R.id.lvPopular);
        TourGuideAdapter adapter = new TourGuideAdapter(rootView.getContext(), guides);
        listView.setAdapter(adapter);

        return rootView;
    }
}

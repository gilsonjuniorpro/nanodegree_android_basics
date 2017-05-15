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

public class SideTripsFragment extends Fragment {

    public SideTripsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_side_trips, container, false);

        ArrayList<Guide> guides = new ArrayList<>();

        guides.add(new Guide(getString(R.string.title_fundy_national_park), getString(R.string.category_fundy_national_park),
                getString(R.string.duration_fundy_national_park), getString(R.string.contact_fundy_national_park), R.drawable.fundy_national_park));

        guides.add(new Guide(getString(R.string.title_parlee_beach), getString(R.string.category_parlee_beach),
                getString(R.string.duration_parlee_beach), getString(R.string.contact_parlee_beach), R.drawable.parlee_beach));

        guides.add(new Guide(getString(R.string.title_hopewell_rocks), getString(R.string.category_hopewell_rocks),
                getString(R.string.duration_hopewell_rocks), getString(R.string.contact_hopewell_rocks), R.drawable.hopewell_rocks));

        ListView listView = (ListView) rootView.findViewById(R.id.lvSideTrips);
        TourGuideAdapter adapter = new TourGuideAdapter(rootView.getContext(), guides);
        listView.setAdapter(adapter);

        return rootView;
    }
}

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

public class MuseumsFragment extends Fragment {

    public MuseumsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_museums, container, false);

        ArrayList<Guide> guides = new ArrayList<>();

        guides.add(new Guide(getString(R.string.title_magnetic_hill_park), getString(R.string.category_magnetic_hill_park),
                getString(R.string.duration_magnetic_hill_park), getString(R.string.contact_magnetic_hill_park), R.drawable.magnetic_hill_park));

        guides.add(new Guide(getString(R.string.title_resurgo_place), getString(R.string.category_resurgo_place),
                getString(R.string.duration_resurgo_place), getString(R.string.contact_resurgo_place), R.drawable.resurgo_place));

        guides.add(new Guide(getString(R.string.title_acadian_museum), getString(R.string.category_acadian_museum),
                getString(R.string.duration_acadian_museum), getString(R.string.contact_acadian_museum), R.drawable.acadian_museum));

        ListView listView = (ListView) rootView.findViewById(R.id.lvMuseums);
        TourGuideAdapter adapter = new TourGuideAdapter(rootView.getContext(), guides);
        listView.setAdapter(adapter);

        return rootView;
    }
}

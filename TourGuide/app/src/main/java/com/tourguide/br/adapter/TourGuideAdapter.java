package com.tourguide.br.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tourguide.br.R;
import com.tourguide.br.entity.Guide;

import java.util.ArrayList;

/**
 * Created by gilsonjuniorpro on 4/24/17.
 */

public class TourGuideAdapter extends ArrayAdapter<Guide>{

    private Context mContext;

    public TourGuideAdapter(Context context, ArrayList<Guide> guides) {
        super(context, 0, guides);
        mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Guide guide = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivPhoto);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvCategory = (TextView)convertView.findViewById(R.id.tvCategory);
        TextView tvContact = (TextView)convertView.findViewById(R.id.tvContact);

        imageView.setImageResource(guide.getImage());
        tvTitle.setText(guide.getTitle());
        tvCategory.setText(guide.getCategory());
        tvContact.setText(guide.getContact());

        return convertView;
    }
}

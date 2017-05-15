package com.news.br.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.news.br.R;
import com.news.br.pojo.Guide;
import com.news.br.ui.MainActivity;
import com.news.br.ui.SectionActivity;

import java.util.List;

/**
 * Created by gilsonjuniorpro on 5/3/17.
 */

public class GuideAdapter extends ArrayAdapter<Guide> {

    private Context context;
    private List<Guide> news;

    public GuideAdapter(MainActivity context, List<Guide> guides) {
        super(context, 0, guides);
        this.context = context;
        this.news = guides;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.guide_list_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Guide currentGuide = getItem(position);

        viewHolder.tvTitle.setText(currentGuide.getWebTitle());

        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, news.get(position).getWebTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), SectionActivity.class);
                intent.putExtra("section", news.get(position).getId());
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    class ViewHolder{
        private TextView tvTitle;

        public ViewHolder(@NonNull View view) {
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }
}

package com.news.br.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.news.br.R;
import com.news.br.pojo.Guide;
import com.news.br.ui.SectionActivity;

import java.util.List;

/**
 * Created by gilsonjuniorpro on 5/4/17.
 */

public class SectionAdapter extends ArrayAdapter<Guide> {

    private Context context;
    private List<Guide> news;

    public SectionAdapter(SectionActivity context, List<Guide> guides) {
        super(context, 0, guides);
        this.context = context;
        this.news = guides;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.section_list_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Guide currentGuide = getItem(position);

        viewHolder.tvSection.setText(currentGuide.getSectionName());

        //I can't understand why doesn't work
        //viewHolder.tvDate.setText(Utils.formataDataComTimeZone(currentGuide.getWebPublicationDate()));

        if(currentGuide.getWebPublicationDate() != null){
            String date = currentGuide.getWebPublicationDate().substring(0, 10);
            viewHolder.tvDate.setText(date);
        }


        viewHolder.tvTitle.setText(currentGuide.getWebTitle());

        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.get(position).getWebUrl()));
                context.startActivity(browserIntent);
            }
        });

        return convertView;
    }



    class ViewHolder{
        private TextView tvSection;
        private TextView tvTitle;
        private TextView tvDate;

        public ViewHolder(@NonNull View view) {
            this.tvSection = (TextView) view.findViewById(R.id.tvSection);
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvDate = (TextView) view.findViewById(R.id.tvDate);
        }
    }
}
package com.booklisting.br.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.booklisting.br.R;
import com.booklisting.br.pojo.Book;
import com.booklisting.br.util.Utils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by gilsonjuniorpro on 4/28/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private Context context;

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        Book currentBook = getItem(position);

        ImageView ivThumbnail = (ImageView) listItemView.findViewById(R.id.ivThumbnail);
        TextView tvTitle = (TextView) listItemView.findViewById(R.id.tvTitle);
        TextView tvAuthors = (TextView) listItemView.findViewById(R.id.tvAuthors);
        TextView tvRating = (TextView) listItemView.findViewById(R.id.tvRating);

        Glide.with(context)
                .load(currentBook.getThumbnail())
                .placeholder(R.drawable.content)
                .into(ivThumbnail);

        tvTitle.setText(currentBook.getTitle());
        tvAuthors.setText(currentBook.getAuthors());
        tvRating.setText(Utils.formatDoubleToString(currentBook.getRating()));

        return listItemView;
    }
}
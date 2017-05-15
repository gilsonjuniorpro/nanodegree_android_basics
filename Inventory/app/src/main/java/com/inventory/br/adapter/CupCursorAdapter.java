/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.inventory.br.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inventory.br.R;
import com.inventory.br.data.CupContract.CupEntry;
import com.inventory.br.ui.MainActivity;

public class CupCursorAdapter extends CursorAdapter {

    private MainActivity mContext;

    public CupCursorAdapter(MainActivity context, Cursor c) {
        super(context, c, 0);
        this.mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvQuantity = (TextView) view.findViewById(R.id.tvQuantity);
        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        ImageButton btSell = (ImageButton) view.findViewById(R.id.btSell);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        btSell.setFocusable(false);
        btSell.setFocusableInTouchMode(false);
        btSell.setClickable(true);

        final long id = cursor.getLong(cursor.getColumnIndex(CupEntry._ID));
        int titleColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_TITLE);
        int quantityColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_PRICE);
        int imageColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_IMAGE);

        String title = cursor.getString(titleColumnIndex);
        final String quantity = cursor.getString(quantityColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        String image = cursor.getString(imageColumnIndex);

        if (TextUtils.isEmpty(price)) {
            price = context.getString(R.string.unknown_price);
        }

        tvTitle.setText(title);
        tvQuantity.setText(quantity);
        tvPrice.setText("$ " + price);
        thumbnail.setImageURI(Uri.parse(image));


        btSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qtd = Integer.parseInt(quantity);
                if(qtd > 0) {
                    Toast.makeText(mContext, R.string.item_sold, Toast.LENGTH_LONG).show();
                    mContext.clickOnSale(id, qtd, mContext);
                }
            }
        });
    }
}

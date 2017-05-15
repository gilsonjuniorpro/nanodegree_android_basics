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
package com.inventory.br.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.inventory.br.data.CupContract.CupEntry;

/**
 * Created by gilsonjuniorpro on 5/6/17.
 */
public class CupDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = CupDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventary.db";

    private static final int DATABASE_VERSION = 1;

    public CupDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_CUPS_TABLE =  "CREATE TABLE " + CupEntry.TABLE_NAME + " ("
                + CupEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CupEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + CupEntry.COLUMN_MATERIAL + " INTEGER NOT NULL DEFAULT 0, "
                + CupEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + CupEntry.COLUMN_TYPE + " TEXT NOT NULL, "
                + CupEntry.COLUMN_PRICE + " TEXT NOT NULL, "
                + CupEntry.COLUMN_IMAGE + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_CUPS_TABLE);
    }

    public Cursor getData() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                CupEntry._ID,
                CupEntry.COLUMN_TITLE,
                CupEntry.COLUMN_QUANTITY,
                CupEntry.COLUMN_PRICE,
                CupEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                CupEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
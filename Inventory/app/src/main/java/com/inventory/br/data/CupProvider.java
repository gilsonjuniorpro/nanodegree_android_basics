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

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.inventory.br.data.CupContract.CupEntry;

/**
 * Created by gilsonjuniorpro on 5/6/17.
 */
public class CupProvider extends ContentProvider {

    public static final String LOG_TAG = CupProvider.class.getSimpleName();

    private static final int CUPS = 100;

    private static final int CUP_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(CupContract.CONTENT_AUTHORITY, CupContract.PATH_CUPS, CUPS);

        sUriMatcher.addURI(CupContract.CONTENT_AUTHORITY, CupContract.PATH_CUPS + "/#", CUP_ID);
    }

    private CupDbHelper mDbHelper;


    @Override
    public boolean onCreate() {
        mDbHelper = new CupDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case CUPS:
                cursor = database.query(CupEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CUP_ID:
                selection = CupEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(CupEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CUPS:
                return insertCup(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }


    private Uri insertCup(Uri uri, ContentValues values) {
        String title = values.getAsString(CupEntry.COLUMN_TITLE);
        if (title == null) {
            throw new IllegalArgumentException("Cup requires a title");
        }

        Integer material = values.getAsInteger(CupEntry.COLUMN_MATERIAL);
        if (material == null || !CupEntry.isValidMaterial(material)) {
            throw new IllegalArgumentException("Cup requires a material");
        }

        Integer quantity = values.getAsInteger(CupEntry.COLUMN_QUANTITY);
        if (quantity != null && quantity < 0) {
            throw new IllegalArgumentException("Cup requires a quantity");
        }

        String type = values.getAsString(CupEntry.COLUMN_TYPE);
        if (type == null) {
            throw new IllegalArgumentException("Cup requires a type");
        }

        String price = values.getAsString(CupEntry.COLUMN_PRICE);
        if (price == null) {
            throw new IllegalArgumentException("Cup requires a price");
        }

        String image = values.getAsString(CupEntry.COLUMN_IMAGE);
        if (image == null) {
            throw new IllegalArgumentException("Cup requires a image");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(CupEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case CUPS:
                return updateCup(uri, contentValues, selection, selectionArgs);

            case CUP_ID:
                selection = CupEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateCup(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }


    private int updateCup(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(CupEntry.COLUMN_TITLE)) {
            String title = values.getAsString(CupEntry.COLUMN_TITLE);
            if (title == null) {
                throw new IllegalArgumentException("Cup requires a title");
            }
        }

        if (values.containsKey(CupEntry.COLUMN_MATERIAL)) {
            Integer material = values.getAsInteger(CupEntry.COLUMN_MATERIAL);
            if (material == null || !CupEntry.isValidMaterial(material)) {
                throw new IllegalArgumentException("Cup requires a material");
            }
        }

        if (values.containsKey(CupEntry.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(CupEntry.COLUMN_QUANTITY);
            if (quantity != null && quantity < 0) {
                throw new IllegalArgumentException("Cup requires a quantity");
            }
        }

        if (values.containsKey(CupEntry.COLUMN_TYPE)) {
            String type = values.getAsString(CupEntry.COLUMN_TYPE);
            if (type == null) {
                throw new IllegalArgumentException("Cup requires a type");
            }
        }

        if (values.containsKey(CupEntry.COLUMN_PRICE)) {
            String price = values.getAsString(CupEntry.COLUMN_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Cup requires a price");
            }
        }

        if (values.containsKey(CupEntry.COLUMN_IMAGE)) {
            String image = values.getAsString(CupEntry.COLUMN_IMAGE);
            if (image == null) {
                throw new IllegalArgumentException("Cup requires a image");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(CupEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CUPS:
                rowsDeleted = database.delete(CupEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case CUP_ID:
                selection = CupEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(CupEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }


    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CUPS:
                return CupEntry.CONTENT_LIST_TYPE;
            case CUP_ID:
                return CupEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }



    public void sellOneItem(long itemId, int quantity, Context mContext) {
        if(mDbHelper == null)
            mDbHelper = new CupDbHelper(mContext);

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues values = new ContentValues();
        values.put(CupEntry.COLUMN_QUANTITY, newQuantity);
        String selection = CupEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        database.update(CupEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public Cursor getData(Context context) {
        CupDbHelper helper = new CupDbHelper(context);
        return helper.getData();
    }
}

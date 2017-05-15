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

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by gilsonjuniorpro on 5/6/17.
 */
public final class CupContract {

    private CupContract() {}

    public static final String CONTENT_AUTHORITY = "com.inventory.br.cups";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CUPS = "cups";

    public static final class CupEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CUPS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CUPS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CUPS;

        public final static String TABLE_NAME = "inventory";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_TITLE ="title";

        public final static String COLUMN_MATERIAL = "material";

        public final static String COLUMN_QUANTITY = "quatity";

        public final static String COLUMN_TYPE = "type";

        public final static String COLUMN_PRICE = "price";

        public final static String COLUMN_IMAGE = "image";

        public static final int MATERIAL_CERAMIC = 0;
        public static final int MATERIAL_PLASTIC = 1;
        public static final int MATERIAL_METAL = 2;

        public static boolean isValidMaterial(int material) {
            if (material == MATERIAL_CERAMIC || material == MATERIAL_PLASTIC || material == MATERIAL_METAL) {
                return true;
            }
            return false;
        }
    }

}


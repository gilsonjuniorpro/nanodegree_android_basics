package com.habittracker.br.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by gilsonjuniorpro on 5/4/17.
 */

public final class HabitTrackerContract {

    private HabitTrackerContract() {}

    public static final String CONTENT_AUTHORITY = "com.habittracker.br.habits";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_HABITS = "habits";

    public static final class HabitEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HABITS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HABITS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HABITS;

        public final static String TABLE_NAME = "habit";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_HABIT_SUBJECT ="subject";

        public final static String COLUMN_HABIT_THEME = "theme";

        public final static String COLUMN_HABIT_TIME = "time";

        public final static String COLUMN_HABIT_LEVEL = "level";

        public static final int COLUMN_HABIT_LEVEL_BEGINNER = 0;
        public static final int COLUMN_HABIT_LEVEL_INTERMEDIATE = 1;
        public static final int COLUMN_HABIT_LEVEL_ADVANCED = 2;

        public static boolean isValidLevel(int level) {
            if (level == COLUMN_HABIT_LEVEL_BEGINNER || level == COLUMN_HABIT_LEVEL_INTERMEDIATE
                    || level == COLUMN_HABIT_LEVEL_ADVANCED) {
                return true;
            }
            return false;
        }
    }
}

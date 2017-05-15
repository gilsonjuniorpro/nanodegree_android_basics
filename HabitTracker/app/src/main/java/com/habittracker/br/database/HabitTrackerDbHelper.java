package com.habittracker.br.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.habittracker.br.database.HabitTrackerContract.HabitEntry;

/**
 * Created by gilsonjuniorpro on 5/4/17.
 */

public class HabitTrackerDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitTrackerDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "habittracker.db";

    private static final int DATABASE_VERSION = 1;

    public HabitTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABIT_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_SUBJECT + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_THEME + " TEXT, "
                + HabitEntry.COLUMN_HABIT_TIME + " TEXT, "
                + HabitEntry.COLUMN_HABIT_LEVEL + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

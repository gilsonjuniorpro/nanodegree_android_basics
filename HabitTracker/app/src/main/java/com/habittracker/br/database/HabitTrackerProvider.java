package com.habittracker.br.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.habittracker.br.database.HabitTrackerContract.HabitEntry;

/**
 * Created by gilsonjuniorpro on 5/4/17.
 */

public class HabitTrackerProvider extends ContentProvider {

    public static final String LOG_TAG = HabitTrackerProvider.class.getSimpleName();

    private static final int HABITS = 100;

    private static final int HABIT_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(HabitTrackerContract.CONTENT_AUTHORITY, HabitTrackerContract.PATH_HABITS, HABITS);

        sUriMatcher.addURI(HabitTrackerContract.CONTENT_AUTHORITY, HabitTrackerContract.PATH_HABITS + "/#", HABIT_ID);
    }

    private HabitTrackerDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new HabitTrackerDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case HABITS:
                cursor = database.query(HabitEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case HABIT_ID:
                selection = HabitEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(HabitEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case HABITS:
                return insertHabit(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }


    private Uri insertHabit(Uri uri, ContentValues values) {
        String subject = values.getAsString(HabitEntry.COLUMN_HABIT_SUBJECT);
        if (subject == null) {
            throw new IllegalArgumentException("Habit requires a subject");
        }

        String theme = values.getAsString(HabitEntry.COLUMN_HABIT_THEME);
        if (theme == null) {
            throw new IllegalArgumentException("Habit requires valid theme");
        }

        String time = values.getAsString(HabitEntry.COLUMN_HABIT_TIME);
        if (time == null) {
            throw new IllegalArgumentException("Habit requires valid time");
        }

        Integer level = values.getAsInteger(HabitEntry.COLUMN_HABIT_LEVEL);
        if (level == null || !HabitEntry.isValidLevel(level)) {
            throw new IllegalArgumentException("Habit requires valid level");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(HabitEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }



    private int updateHabit(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(HabitEntry.COLUMN_HABIT_SUBJECT)) {
            String subject = values.getAsString(HabitEntry.COLUMN_HABIT_SUBJECT);
            if (subject == null) {
                throw new IllegalArgumentException("Habit requires a subject");
            }
        }

        if (values.containsKey(HabitEntry.COLUMN_HABIT_THEME)) {
            String theme = values.getAsString(HabitEntry.COLUMN_HABIT_THEME);
            if (theme == null) {
                throw new IllegalArgumentException("Habit requires valid theme");
            }
        }

        if (values.containsKey(HabitEntry.COLUMN_HABIT_TIME)) {
            String time = values.getAsString(HabitEntry.COLUMN_HABIT_TIME);
            if (time == null) {
                throw new IllegalArgumentException("Habit requires valid time");
            }
        }

        if (values.containsKey(HabitEntry.COLUMN_HABIT_LEVEL)) {
            Integer level = values.getAsInteger(HabitEntry.COLUMN_HABIT_LEVEL);
            if (level == null || !HabitEntry.isValidLevel(level)) {
                throw new IllegalArgumentException("Habit requires valid level");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(HabitEntry.TABLE_NAME, values, selection, selectionArgs);

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
            case HABITS:
                rowsDeleted = database.delete(HabitEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case HABIT_ID:
                selection = HabitEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(HabitEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case HABITS:
                return HabitEntry.CONTENT_LIST_TYPE;
            case HABIT_ID:
                return HabitEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case HABITS:
                return updateHabit(uri, values, selection, selectionArgs);

            case HABIT_ID:
                selection = HabitEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateHabit(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
}

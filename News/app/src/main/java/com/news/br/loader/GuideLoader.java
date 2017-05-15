package com.news.br.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.news.br.pojo.Guide;
import com.news.br.util.QueryUtils;

import java.util.List;

/**
 * Created by gilsonjuniorpro on 5/3/17.
 */

public class GuideLoader extends AsyncTaskLoader<List<Guide>> {

    private static final String LOG_TAG = GuideLoader.class.getName();

    private String mSection;

    public GuideLoader(Context context, String section) {
        super(context);
        mSection = section;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Guide> loadInBackground() {
        List<Guide> guides = QueryUtils.fetchGuideData(mSection);
        return guides;
    }
}

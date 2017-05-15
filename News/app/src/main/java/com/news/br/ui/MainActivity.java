package com.news.br.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.news.br.R;
import com.news.br.adapter.GuideAdapter;
import com.news.br.loader.GuideLoader;
import com.news.br.pojo.Guide;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Guide>>{

    public static final String TAG = MainActivity.class.getName();

    private static final int LOADER_ID = 1;
    private GuideAdapter mAdapter;
    private ListView bookListView;
    private TextView tvEmptyView;
    private String section;
    private ConnectivityManager connMgr;
    private View loadingIndicator;
    private LoaderManager loaderManager;
    //private List<Guide> guides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookListView = (ListView) findViewById(R.id.listView);
        tvEmptyView = (TextView) findViewById(R.id.tvEmptyView);
        loadingIndicator = findViewById(R.id.loadingIndicator);

        loadingIndicator.setVisibility(View.VISIBLE);
        loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, MainActivity.this);
    }


    @Override
    public Loader<List<Guide>> onCreateLoader(int i, Bundle bundle) {
        return new GuideLoader(this, section);
    }

    @Override
    public void onLoadFinished(Loader<List<Guide>> loader, List<Guide> guides) {
        if(mAdapter == null){
            mAdapter = new GuideAdapter(this, guides);
        }

        loadingIndicator.setVisibility(View.GONE);
        if (guides != null && guides.size() > 0) {
            mAdapter.addAll(guides);
            bookListView.setAdapter(mAdapter);
        }else{
            tvEmptyView.setText(R.string.no_news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Guide>> loader) {
        mAdapter.clear();
    }

}
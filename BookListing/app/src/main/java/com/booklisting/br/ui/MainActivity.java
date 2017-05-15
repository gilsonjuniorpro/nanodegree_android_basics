package com.booklisting.br.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.booklisting.br.R;
import com.booklisting.br.adapter.BookAdapter;
import com.booklisting.br.controller.BookController;
import com.booklisting.br.pojo.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private static final String LIST_BOOKS = "listBooks";
    private BookAdapter mAdapter;
    private ListView bookListView;
    private TextView tvEmptyView;
    private EditText edTitle;
    private String title;
    private ConnectivityManager connMgr;
    private View loadingIndicator;
    private ArrayList<Book> mBookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookListView = (ListView) findViewById(R.id.listView);
        tvEmptyView = (TextView) findViewById(R.id.tvEmptyView);
        edTitle = (EditText) findViewById(R.id.edTitle);
        loadingIndicator = findViewById(R.id.loadingIndicator);

        mBookArrayList = new ArrayList<>();
        mAdapter = new BookAdapter(this, mBookArrayList);

        if (mBookArrayList.size() > 0) {
            mAdapter.clear();
            mAdapter = new BookAdapter(this, mBookArrayList);
            bookListView.setAdapter(mAdapter);
        } else {
            loadingIndicator.setVisibility(View.GONE);
            tvEmptyView.setText(R.string.no_search_query);
            bookListView.setEmptyView(tvEmptyView);
        }

        Button btSearch = (Button)findViewById(R.id.btSearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = edTitle.getText().toString();

                connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    new BookAsyncTask().execute(title);
                } else {
                    loadingIndicator = findViewById(R.id.loadingIndicator);
                    loadingIndicator.setVisibility(View.GONE);
                    mAdapter.clear();
                    tvEmptyView.setText(R.string.no_internet_connection);
                }
            }
        });
    }


    public class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {

            try {
                mBookArrayList = (ArrayList<Book>) BookController.listBooks(title);
            } catch (Exception e) {
                Log.e(TAG, "loading error" + e.toString());
            }
            return mBookArrayList;
        }

        protected void onPostExecute(final List<Book> books) {
            super.onPostExecute(books);

            loadingIndicator.setVisibility(View.GONE);
            if (mBookArrayList.size() > 0) {
                mAdapter = new BookAdapter(MainActivity.this, books);
                bookListView.setAdapter(mAdapter);
            } else {
                tvEmptyView.setText(R.string.no_books);
                bookListView.setEmptyView(tvEmptyView);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            Log.d(TAG, "onRestoreInstanceState");
            mBookArrayList = savedInstanceState.getParcelableArrayList(LIST_BOOKS);
            mAdapter.clear();
            mAdapter.addAll(mBookArrayList);
            bookListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList(LIST_BOOKS, mBookArrayList);
        Log.d(TAG, "onSaveInstanceState");

        super.onSaveInstanceState(savedInstanceState);
    }
}
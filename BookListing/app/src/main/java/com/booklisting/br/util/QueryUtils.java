package com.booklisting.br.util;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.booklisting.br.pojo.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilsonjuniorpro on 4/28/17.
 */

public final class QueryUtils {

    private static final String TAG = QueryUtils.class.getSimpleName();
    private static String title;
    private static String authors = "";
    private static String publisher = "";
    private static String description = "";
    private static String isbn = "";
    private static double rating = 0.0;
    private static String thumbnail = "";

    private static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes";

    private QueryUtils() {
    }

    public static List<Book> fetchBookData(String title) {

        URL url = createUrl(title);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }

        List<Book> books = extractFeatureFromJson(jsonResponse);

        return books;
    }


    private static URL createUrl(String title) {
        URL url = null;
        try {
            Uri baseUri = Uri.parse(REQUEST_URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("maxResults", "10");
            uriBuilder.appendQueryParameter("q", title);

            url = new URL(uriBuilder.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the book JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static List<Book> extractFeatureFromJson(String bookJSON) {

        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        List<Book> books = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(bookJSON);

            JSONArray bookArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < bookArray.length(); i++) {

                JSONObject currentBook = bookArray.getJSONObject(i);

                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                if (volumeInfo.has("title"))
                    title = volumeInfo.getString("title");

                StringBuilder builder = new StringBuilder();
                if (volumeInfo.has("authors")) {
                    JSONArray authorsArray = volumeInfo.getJSONArray("authors");
                    int count = 1;
                    for (int a = 0; a < authorsArray.length(); a++) {
                        builder.append(authorsArray.getString(a));
                        if (authorsArray.length() > count) {
                            builder.append(",");
                            count++;
                        }
                    }
                    authors = builder.toString();
                }
                if (volumeInfo.has("publisher"))
                    publisher = volumeInfo.getString("publisher");

                if (volumeInfo.has("description"))
                    description = volumeInfo.getString("description");

                JSONArray industryIdentifiersArray = volumeInfo.getJSONArray("industryIdentifiers");

                JSONObject identifiers = industryIdentifiersArray.getJSONObject(0);

                if (identifiers.has("type") && identifiers.has("identifier"))
                    isbn = identifiers.getString("type") + " - " + identifiers.getString("identifier");

                if (volumeInfo.has("averageRating"))
                    rating = volumeInfo.getDouble("averageRating");

                if (volumeInfo.has("imageLinks")) {
                    JSONObject imgs = volumeInfo.getJSONObject("imageLinks");

                    if (imgs.has("thumbnail"))
                        thumbnail = imgs.getString("thumbnail");
                }

                Book book = new Book(title, authors, publisher, description, isbn, rating, thumbnail);

                books.add(book);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the book JSON results", e);
        }

        return books;
    }
}


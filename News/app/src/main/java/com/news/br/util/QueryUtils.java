package com.news.br.util;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.news.br.pojo.Guide;

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
    private static String status = null;
    private static String userTier = null;
    private static int total = 1;
    private static int startIndex = 1;
    private static int pageSize = 1;
    private static int currentPage = 1;
    private static int pages = 1;
    private static String orderBy = null;
    private static String id = null;
    private static String type = null;
    private static String sectionId = null;
    private static String sectionName = null;
    private static String webPublicationDate = null;
    private static String webTitle = null;
    private static String webUrl = null;
    private static String apiUrl = null;
    private static boolean isHosted = true;

    private QueryUtils() {
    }

    public static List<Guide> fetchGuideData(String section) {

        Uri.Builder uriBuilder;
        //uriBuilder.appendQueryParameter("tag", section);
        if (!TextUtils.isEmpty(section) && section != null) {
            Uri baseUri = Uri.parse(Constants.GUARDIAN_URL);
            uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("api-key", Constants.GUARDIAN_KEY);
            uriBuilder.appendQueryParameter("q", section);
        }else{
            Uri baseUri = Uri.parse(Constants.GUARDIAN_URL_SECTIONS);
            uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("api-key", Constants.GUARDIAN_KEY);
        }

        URL url = createUrl(uriBuilder.toString());

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }

        List<Guide> guides = extractFeatureFromJson(jsonResponse);

        return guides;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
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
            Log.e(TAG, "Problem retrieving the news JSON results.", e);
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


    private static List<Guide> extractFeatureFromJson(String guideJSON) {

        if (TextUtils.isEmpty(guideJSON)) {
            return null;
        }

        List<Guide> news = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(guideJSON);

            JSONObject response = baseJsonResponse.getJSONObject("response");

            JSONArray guideArray = response.getJSONArray("results");

            if (response.has("status"))
                status = response.getString("status");

            if (response.has("userTier"))
                userTier = response.getString("userTier");

            if (response.has("total"))
                total = response.getInt("total");

            if (response.has("startIndex"))
                startIndex = response.getInt("startIndex");

            if (response.has("pageSize"))
                pageSize = response.getInt("pageSize");

            if (response.has("currentPage"))
                currentPage = response.getInt("currentPage");

            if (response.has("pages"))
                pages = response.getInt("pages");

            if (response.has("orderBy"))
                userTier = response.getString("orderBy");

            for (int i = 0; i < guideArray.length(); i++) {

                JSONObject currentGuide = guideArray.getJSONObject(i);

                if (currentGuide.has("id"))
                    id = currentGuide.getString("id");

                if (currentGuide.has("type"))
                    type = currentGuide.getString("type");

                if (currentGuide.has("sectionId"))
                    sectionId = currentGuide.getString("sectionId");

                if (currentGuide.has("sectionName"))
                    sectionName = currentGuide.getString("sectionName");

                if (currentGuide.has("webPublicationDate"))
                    webPublicationDate = currentGuide.getString("webPublicationDate");

                if (currentGuide.has("webTitle"))
                    webTitle = currentGuide.getString("webTitle");

                if (currentGuide.has("webUrl"))
                    webUrl = currentGuide.getString("webUrl");

                if (currentGuide.has("apiUrl"))
                    apiUrl = currentGuide.getString("apiUrl");

                if (currentGuide.has("isHosted"))
                    isHosted = currentGuide.getBoolean("isHosted");

                Guide guide = new Guide(status, userTier, total, startIndex, pageSize,
                        currentPage, pages, orderBy, id, type, sectionId,
                        sectionName, webPublicationDate, webTitle, webUrl,
                        apiUrl, isHosted);

                news.add(guide);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        return news;
    }
}


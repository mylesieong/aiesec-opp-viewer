package com.myles.util.aov;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AOV {    

    public void help() {
        System.out.println("AOV:help");
    }
    
    public void searchByCountry(String country){
        String urlString = "https://gis-api.aiesec.org/v2/opportunities.json?access_token=e316ebe109dd84ed16734e5161a2d236d0a7e6daf499941f7c110078e3c75493&filters[last_interaction][from]=2017-01-30&sort=-application_count&per_page=4";
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<Opportunity> opps = searchLevelExtractionFromJson(jsonResponse);
        for (int i = 0; i < opps.size(); i++ ){
            System.out.println(i + "\t" + opps.get(i).toString(Opportunity.BRIEF_STYLE));
        }
    }
    
    public void searchByKeyword(String keyword){
        System.out.println("AOV:searchByKeyword");
    }
    
    public void showOpportunity(String oppId){
        String urlString = "https://gis-api.aiesec.org/v2/opportunities/" + oppId + ".json?access_token=e316ebe109dd84ed16734e5161a2d236d0a7e6daf499941f7c110078e3c75493";
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Opportunity opp = detailLevelExtractionFromJson(jsonResponse);
        System.out.println(opp.toString(Opportunity.BRIEF_STYLE));
    }

    private String readFromStream(InputStream inputStream) throws IOException {
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

    private List<Opportunity> searchLevelExtractionFromJson(String json) {
        ArrayList<Opportunity> opps = new ArrayList<Opportunity>();
        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray featureArray = baseJsonResponse.getJSONArray("data");

            // If there are results in the features array
            for (int i = 0; i < featureArray.length(); i++) {
                JSONObject properties = featureArray.getJSONObject(i);

                int id = properties.getInt("id");
                String title = properties.getString("title");
                String company = properties.getJSONObject("branch").getString("name");
                int duration = properties.getInt("duration");
                String country = properties.getJSONObject("office").getString("country");
                // JSONArray authorList = properties.getJSONArray("authors");
                // ArrayList<String> authors = new ArrayList<String>();
                // for (int j = 0; j < authorList.length(); j++) {
                    // authors.add(authorList.getString(j));
                // }
                // String date = properties.getString("publishedDate");

                Opportunity opp = new Opportunity();
                opp.setId(id);
                opp.setTitle(title);
                opp.setCompany(company);
                opp.setDuration(duration);
                opp.setCountry(country);
                opps.add(opp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return opps;
    }
    
    private Opportunity detailLevelExtractionFromJson(String json){
        Opportunity opp = new Opportunity();
        try {
            JSONObject properties = new JSONObject(json);

            int id = properties.getInt("id");
            String title = properties.getString("title");
            String company = properties.getJSONObject("branch").getString("name");
            int duration = properties.getInt("duration");
            String country = properties.getJSONObject("home_lc").getString("country");

            opp.setId(id);
            opp.setTitle(title);
            opp.setCompany(company);
            opp.setDuration(duration);
            opp.setCountry(country);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return opp;
    }
    
}
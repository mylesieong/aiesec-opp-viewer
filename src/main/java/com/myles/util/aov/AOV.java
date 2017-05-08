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

    final static public int DEFAULT_READ_TIMEOUT = 20000;
    final static public int DEFAULT_CONNECTION_TIMEOUT = 30000;

    public void help() {
        System.out.println("AOV:help");
    }

    public void gep(){
        String urlString = "https://gis-api.aiesec.org/v2/opportunities.json?access_token=e316ebe109dd84ed16734e5161a2d236d0a7e6daf499941f7c110078e3c75493&filters%5Bis_gep%5D=true";
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(AOV.DEFAULT_READ_TIMEOUT); //10,000 ms = 10s
            urlConnection.setConnectTimeout(AOV.DEFAULT_CONNECTION_TIMEOUT);
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
    
    public void searchByCountry(String country){
        String countryCode = "";
        if (country.compareToIgnoreCase("us") == 0){
            countryCode = "1621";
        }else if (country.compareToIgnoreCase("ca") == 0){
            countryCode = "1554";
        }
        String urlString = "https://gis-api.aiesec.org/v2/opportunities.json?access_token=e316ebe109dd84ed16734e5161a2d236d0a7e6daf499941f7c110078e3c75493&filters[home_mcs][]=" + countryCode + "&filters[programmes][]=2&filters[last_interaction][from]=2017-01-30&earliest_start_date=2017-5-7&sort=earliest_start_date";
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(AOV.DEFAULT_READ_TIMEOUT); //10,000 ms = 10s
            urlConnection.setConnectTimeout(AOV.DEFAULT_CONNECTION_TIMEOUT);
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
        String urlString = "https://gis-api.aiesec.org/v2/opportunities.json?access_token=e316ebe109dd84ed16734e5161a2d236d0a7e6daf499941f7c110078e3c75493&q=" + keyword + "&filters[last_interaction][from]=2017-01-30&earliest_start_date=2017-5-7&sort=earliest_start_date";
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(AOV.DEFAULT_READ_TIMEOUT); //10,000 ms = 10s
            urlConnection.setConnectTimeout(AOV.DEFAULT_CONNECTION_TIMEOUT);
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
    
    public void showOpportunity(String oppId){
        String urlString = "https://gis-api.aiesec.org/v2/opportunities/" + oppId + ".json?access_token=e316ebe109dd84ed16734e5161a2d236d0a7e6daf499941f7c110078e3c75493";
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(AOV.DEFAULT_READ_TIMEOUT);
            urlConnection.setConnectTimeout(AOV.DEFAULT_CONNECTION_TIMEOUT);
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
        System.out.println(opp.toString(Opportunity.DETAIL_STYLE));
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

                Opportunity opp = new Opportunity();
                opp._id = id;
                opp._title = title; 
                opp._company = company; 
                opp._duration = duration;
                opp._country = country; 
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

            opp._id = id;
            opp._title = title;
            opp._company = company;
            opp._duration = duration;
            opp._country = country;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return opp;
    }
    
}

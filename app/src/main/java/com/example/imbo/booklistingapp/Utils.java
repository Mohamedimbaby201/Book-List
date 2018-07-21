package com.example.imbo.booklistingapp;

import android.util.EventLogTags;
import android.util.Log;

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

public class Utils {
    static String TITLE_TAG = "title";
    static  String Image_TAG ="thumbnail";
    static   String Preview_TAG = "previewLink";
    static   String Authors_TAG ="authors";
    static String Description_TAG ="description";

    static ArrayList<Books> fetchBooksData(String urlBooksApi)
{String jsonRespone ="";
    if(urlBooksApi==null)
    {return  null;}
  URL url= CreateURL(urlBooksApi);
    InputStream inputStream =MakeHttpRequest(url);
    if(inputStream==null)
    {return null;}
     jsonRespone = readFromStream(inputStream);
    if(jsonRespone=="")
    {return null;}

   ArrayList<Books>Books= readFromJson(jsonRespone);

Log.e("No " , Books.toString());
    return Books;

}
static URL CreateURL (String urlBooksApi)
{
    URL url = null;
    try {
        url=new URL(urlBooksApi);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
return url;
}
static InputStream MakeHttpRequest(URL url )
{
    HttpURLConnection urlConnection=null;
    InputStream inputStream =null;
    try {
         urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(10000);
        urlConnection.connect();
        if(urlConnection.getResponseCode()==200)
        {
            inputStream = urlConnection.getInputStream();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
return inputStream;
}

    static String readFromStream(InputStream inputStream )
    {
     StringBuilder output = new StringBuilder() ;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
        String    Line = reader.readLine();
        while (Line!=null)
        {
            output.append(Line);
            Line=reader.readLine();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    static ArrayList<Books> readFromJson(String jsonResponse)
    {
        ArrayList <Books> Books = new ArrayList<>();
        try {

            JSONObject root = new JSONObject(jsonResponse);
            JSONArray items = root.getJSONArray("items");
            for (int i=0;i<items.length();i++)
            {
                JSONObject item = items.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                String title= volumeInfo.getString(TITLE_TAG);
                String PreviewLink= volumeInfo.getString(Preview_TAG);
                String pageCount= volumeInfo.getString("pageCount")+" pages ";

                String description= volumeInfo.getString(Description_TAG);
                JSONObject imageLinnks = volumeInfo.getJSONObject("imageLinks");
                String image = imageLinnks.getString("thumbnail");




Books.add(new Books(description,title,image,PreviewLink,pageCount));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    return  Books;
    }
}

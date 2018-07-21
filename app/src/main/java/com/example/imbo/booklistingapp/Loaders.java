package com.example.imbo.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Loaders extends AsyncTaskLoader<List<Books>> {
    String Url_BooksApi;
    public Loaders(Context context , String Url_BooksApi) {
        super(context);
        this.Url_BooksApi=Url_BooksApi;

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    forceLoad();
    }

    @Override
    public List<Books> loadInBackground() {
      ArrayList<Books> Books= Utils.fetchBooksData(Url_BooksApi);


        return Books;
    }
}

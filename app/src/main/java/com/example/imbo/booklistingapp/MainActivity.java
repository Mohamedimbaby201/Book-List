package com.example.imbo.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imbo.booklistingapp.Adapters.BooksAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<Books>>,View.OnClickListener {
BooksAdapter adapter ;
String Url_BooksApi="https://www.googleapis.com/books/v1/volumes?q=";
ProgressBar indection ;
ListView list;
EditText text ;
TextView EmptyStateText;
Button Search;
String o;
    Boolean Network;
    int Counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
indection = findViewById(R.id.indection);
indection.setVisibility(View.GONE);
        list = findViewById(R.id.list);
        text = findViewById(R.id.edit);
        Search = findViewById(R.id.search);
        EmptyStateText = findViewById(R.id.EmptyStateText);
        list.setEmptyView(EmptyStateText);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Network = true;
        } else {
            EmptyStateText.setText("No Internet Connection ");
            Network = false;

        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Books book = adapter.getItem(i);
                Uri uri = Uri.parse(book.getPreview_TAG());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
Search.setOnClickListener(this);
    adapter= new BooksAdapter(this,new ArrayList<Books>());
    list.setAdapter(adapter);
    }
    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
Log.e("TAAAAAAAAAAG" , o);
        return new Loaders(this,o);
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> books) {
        indection.setVisibility(View.GONE);
        Log.e("TEST","ON  Load Finish");
        if (books!=null&&!books.isEmpty())
        {
adapter.addAll(books);
        }
else {
            EmptyStateText.setText("No Books Matched  ");

        }

    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
adapter.clear();

    }

    @Override
    public void onClick(View view) {
if (Network) {
    adapter.clear();
    indection.setVisibility(View.VISIBLE);
    String keyword = text.getText().toString();
    ++Counter;
 o =    Url_BooksApi+keyword;
    LoaderManager loaderManager = getLoaderManager();
    loaderManager.restartLoader(Counter, null, this);

}

    }
}


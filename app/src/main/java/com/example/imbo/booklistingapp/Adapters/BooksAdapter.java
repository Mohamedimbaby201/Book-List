package com.example.imbo.booklistingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imbo.booklistingapp.Books;
import com.example.imbo.booklistingapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BooksAdapter extends ArrayAdapter<Books> {
    public BooksAdapter(@NonNull Context context, @NonNull List<Books> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View ListView =convertView;
    if (ListView==null)
    {
        ListView=LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

    }
        TextView title = (TextView)ListView.findViewById(R.id.title);

        TextView description = (TextView)ListView.findViewById(R.id.description);
        TextView author = (TextView)ListView.findViewById(R.id.author);
        final ImageView image = (ImageView) ListView.findViewById(R.id.image);

        final Books currentBook =getItem(position);
        title.setText(currentBook.getTITLE_TAG());
        description.setText(currentBook.getDescription_TAG());
        author.setText(currentBook.getAuthors_TAG());
        Picasso.get().load(currentBook.getImage_TAG()).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.book).into(image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(currentBook.getImage_TAG()).placeholder(R.drawable.book).into(image);
            }
        });



        return ListView;
    }
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}

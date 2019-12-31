package com.example.thumbs_app;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DrivesList extends ArrayAdapter implements Filterable {
    private Activity context;
    private List<Tremp> drivesList;



    public DrivesList(Activity context, List<Tremp> drivesList) {
        super(context, R.layout.activity_profile, android.R.id.text1, drivesList);
        this.context = context;
        this.drivesList = drivesList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisView = inflater.inflate(R.layout.list_layout, null, true);

        ImageView diverImage = (ImageView) lisView.findViewById(R.id.image);
        TextView name = (TextView) lisView.findViewById(R.id.driverName);
        TextView dayTime = (TextView) lisView.findViewById(R.id.timeDay);

        ImageView call = (ImageView) lisView.findViewById(R.id.imageCall);





        final Tremp tremp = drivesList.get(position);
        name.setText(tremp.getName());
        dayTime.setText("מ: " + tremp.getLocationStart() +" ל: " + tremp.getLocationEnd() +" ביום: " + tremp.getDay());


//        call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent makeAcall = new Intent(Intent.ACTION_DIAL);
//                makeAcall.setData(Uri.parse("tel:0544586686"));
//                context.startActivity(makeAcall);
//            }
//        });



        return lisView;


    }
}

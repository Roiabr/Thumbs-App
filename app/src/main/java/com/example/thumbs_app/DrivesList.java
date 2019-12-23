package com.example.thumbs_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DrivesList extends ArrayAdapter {
    private Activity context;
    private List<Tremp> drivesList;
    FloatingActionButton fab;

    public DrivesList(Activity context, List<Tremp> drivesList, FloatingActionButton fab1){
        super(context,R.layout.activity_profile,android.R.id.text1,drivesList);
        this.context = context;
        this.drivesList = drivesList;
        fab = fab1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisView = inflater.inflate(R.layout.list_contact_layout,null,true);

        TextView name = (TextView) lisView.findViewById(R.id.namePhone);
        TextView Phone = (TextView) lisView.findViewById(R.id.loc_contact);
        ImageView call = (ImageView) lisView.findViewById(R.id.imageCall);
        TextView timeStart = (TextView) lisView.findViewById(R.id.start);
        TextView timeEnd = (TextView) lisView.findViewById(R.id.end);


        final Tremp tremp = drivesList.get(position);
        name.setText(tremp.getName());
        Phone.setText(tremp.getLocationStart() + "-" + tremp.getLocationEnd());

        timeStart.setText(tremp.getTimeStart());
        timeEnd.setText(tremp.getTimeEnd());

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent makeAcall = new Intent(Intent.ACTION_DIAL);
                makeAcall.setData(Uri.parse("tel:0544586686"));
                context.startActivity(makeAcall);
            }
        });
        return lisView;



    }
}

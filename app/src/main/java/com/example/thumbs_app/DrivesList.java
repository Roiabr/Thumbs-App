package com.example.thumbs_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisView = inflater.inflate(R.layout.list_layout, null, true);

        ImageView diverImage = (ImageView) lisView.findViewById(R.id.image);
        TextView From_Where = (TextView) lisView.findViewById(R.id.Fw);
        TextView Daytv = (TextView) lisView.findViewById(R.id.Day);
        TextView Timetv = (TextView) lisView.findViewById(R.id.Time);


        //ImageView call = (ImageView) lisView.findViewById(R.id.imageCall);

        final Tremp tremp = drivesList.get(position);

        Daytv.setText("ביום :" +tremp.getDay());
        Timetv.setText("בשעה :" +tremp.getTimeStart());

        From_Where.setText("מ: " + tremp.getLocationStart() +" ל: " + tremp.getLocationEnd() );





        lisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tremp tremp = drivesList.get(position);
                if(true){
                    OpenDialog di= new OpenDialog(context,tremp);
                    di.gal(context,tremp);
                }
                else{

                }

            }
        });


        return lisView;


    }






}

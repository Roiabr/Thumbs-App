package com.example.thumbs_app;

import android.app.Activity;
import android.util.Log;
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
    static int callme;


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
        TextView TimeStart = (TextView) lisView.findViewById(R.id.Time_Start);
        TextView TimeEnd = (TextView) lisView.findViewById(R.id.Time_End);

        TextView DayDrive = (TextView) lisView.findViewById(R.id.Day_Drive);


        //ImageView call = (ImageView) lisView.findViewById(R.id.imageCall);

        final Tremp tremp = drivesList.get(position);

        DayDrive.setText("ביום :" +tremp.getDay());
        TimeStart.setText("בשעה :" +tremp.getTimeStart());
        TimeEnd.setText("עד שעה :" +tremp.getTimeEnd());

        From_Where.setText("מ: " + tremp.getLocationStart() +" ל: " + tremp.getLocationEnd() );





        lisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tremp tremp = drivesList.get(position);
                Log.d("check item",callme+"");
                if(callme==0){

                    OpenDialog dialog= new OpenDialog(context,tremp);
                    dialog.gal(context,tremp);
                }
                else{

                }

            }
        });


        return lisView;


    }






}

package com.example.thumbs_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisView = inflater.inflate(R.layout.list_layout, null, true);

        ImageView diverImage = (ImageView) lisView.findViewById(R.id.image);
        TextView name = (TextView) lisView.findViewById(R.id.driverName);
        TextView dayTime = (TextView) lisView.findViewById(R.id.timeDay);

        ImageView call = (ImageView) lisView.findViewById(R.id.imageCall);

        final Tremp tremp = drivesList.get(position);

        name.setText(tremp.getName());
        dayTime.setText("מ: " + tremp.getLocationStart() +" ל: " + tremp.getLocationEnd() +" ביום: " + tremp.getDay());





        lisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog builder = new Dialog(context);

                builder.setContentView(R.layout.activity_open_dialog);
                builder.setTitle("Choose an animal");
                builder.setCancelable(true);
                Tremp tremp = drivesList.get(position);

                install(builder,position);

               builder.show();
                Log.d("showNotification", tremp.getName() + position);



            }
        });


        return lisView;


    }

    public void install(Dialog g,int position )//install the dialog
    {

          final Tremp tremp = drivesList.get(position);
        TextView name= (TextView)g.findViewById(R.id.dialog_name);
        TextView email= (TextView)g.findViewById(R.id.dialog_email);
        TextView phone= (TextView)g.findViewById(R.id.dialog_phone);
        name.setText(tremp.getName());
        email.setText(tremp.getDay());
        phone.setText(tremp.getLocationStart());
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser currentFirebaseUser2 = FirebaseAuth.getInstance().getCurrentUser();
        //Log.d("showNotification", "showNotification: " + currentFirebaseUser.getUid());
        //Log.d("showNotification", "showNotification: " + currentFirebaseUser2.getUid());

       // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Drives");
        //databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentFirebaseUser.getUid());

        Button b=(Button)g.findViewById(R.id.dialog_invite);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentFirebaseUser2 = FirebaseAuth.getInstance().getCurrentUser();
                Log.d("showNotification2", "showNotification: " + tremp.getcp());
                Log.d("showNotification123", "showNotification: " + currentFirebaseUser2.getUid());
                gal();

            }
        });

    }

    public void gal()
    {
        Intent   intent = new Intent(context, MainActivity.class);
        int reqCode=1;
        showNotification(context, "מישהו רוצה טרמפ", "This is the message to display", intent, reqCode);

    }

    public void showNotification(Context context, String title, String message, Intent intent, int reqCode) {

        PendingIntent pendingIntent = PendingIntent.getActivity(context, reqCode, intent, PendingIntent.FLAG_ONE_SHOT);
        String CHANNEL_ID ="channel_name";// The id of the channel.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                //.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                .setContentIntent(pendingIntent)
                .setColor(5);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationManager.createNotificationChannel(mChannel);
            mChannel.setLightColor(Color.RED);
        }
        notificationManager.notify(reqCode, notificationBuilder.build()); // 0 is the request code, it should be unique id

        Log.d("showNotification", "showNotification: " + reqCode);
    }


}

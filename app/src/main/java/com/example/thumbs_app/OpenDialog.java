package com.example.thumbs_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URLEncoder;

public class OpenDialog extends AppCompatActivity {
    private Activity context;
    Tremp tr;
    TextView count;
    public OpenDialog(Activity co,Tremp t)
    {
    this.context=co;
    this.tr=t;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_open_dialog);
        super.onCreate(savedInstanceState);
    }
    public  void gal(Activity context,Tremp t)
    {
        Dialog builder = new Dialog(context);
        builder.setContentView(R.layout.activity_open_dialog);
        builder.setTitle("Choose an animal");
        builder.setCancelable(true);
        install(builder);
        builder.show();
    }

    public  void  install(Dialog g )//install the dialog
    {

        final Tremp tremp = tr;


        final TextView drivername =(TextView)g.findViewById(R.id.TV_driver_name);
        drivername.setText(tremp.getName());

        TextView starttime =(TextView)g.findViewById(R.id.TV_Time_Start);
        TextView endtime =(TextView)g.findViewById(R.id.TV_Time_End);
        starttime.setText(tremp.getTimeStart());
        endtime.setText(tremp.getTimeEnd());


        TextView From_Where =(TextView)g.findViewById(R.id.TV_From_Where);
        From_Where.setText(""+ tremp.getLocationStart() +" ל: " + tremp.getLocationEnd() );

        TextView DAY =(TextView)g.findViewById(R.id.TV_DAY);
        DAY.setText(tremp.getDay());


        What.number=tremp.phoneDriver;



        final Button btn = (Button)g.findViewById(R.id.whatsup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                What.state=1;
                Log.d("ssad",What.number+"");
                Intent sendIntent = new Intent(context,What.class);
                context.startActivity(sendIntent);


            }
        });

        Button b = (Button)g.findViewById(R.id.dialog_invite);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tremp.getCountofRriders()<=4)
                {
                    tremp.setCountofRriders();
                    Notifcaion_Tremp();

                }

            }
        });


        Button dialbutton =(Button)g.findViewById(R.id.button_dial);
        dialbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                What.state=2;
                Intent th = new Intent(context,What.class);
                context.startActivity(th);
            }       });





    }



    public void Notifcaion_Tremp()
    {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = new Intent(context, MainActivity.class);
        int reqCode=1;
        DatabaseReference databaseList = FirebaseDatabase.getInstance().getReference("Users").child(currentFirebaseUser.getUid());


        showNotification(context, "מישהו רוצה טרמפ",  databaseList.getKey()  +"רוצה לנסוע איתך" , intent, reqCode);
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

    }
}

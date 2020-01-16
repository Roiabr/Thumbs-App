package com.example.thumbs_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.VideoView;

public class What extends AppCompatActivity {
    static String number;
    static int state  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (state)
        {
            case 1:
                openWhatsApp();
                break;

            case 2:
                call();
                break;


        }


    }


    public void call() {
        Intent call =new Intent(Intent.ACTION_DIAL );

        call.setData(Uri.parse("tel:"+number));
        state=0;
        startActivity(call);

    }

    private void openWhatsApp() {
        Log.d("ssaasdsadd",number+"");

        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
        state=0;
        startActivity(i);



    }
    public void backto()
    {

        Intent i = new Intent(getBaseContext(),DrivesFragment.class);
        startActivity(i);

    }


}

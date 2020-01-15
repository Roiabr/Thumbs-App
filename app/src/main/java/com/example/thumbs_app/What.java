package com.example.thumbs_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class What extends AppCompatActivity {
    static String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        openWhatsApp();

    }
    private void openWhatsApp() {
        Log.d("ssaasdsadd",number+"");

        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));

        startActivity(i);
        //backto();

    }
    public void backto()
    {

        Intent i = new Intent(getBaseContext(),DrivesFragment.class);
        startActivity(i);

    }


}

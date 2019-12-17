package com.example.thumbs_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileActivity extends AppCompatActivity {


    Toolbar toolbar,toolTab;
    ViewPager viewPager;
    TabLayout layout;

    Button btn,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       toolbar = (Toolbar) findViewById(R.id.toolbar);
       toolTab = (Toolbar) findViewById(R.id.toolTab);
       layout = (TabLayout) findViewById(R.id.TabLyaout);
       btn = (Button) findViewById(R.id.suggBtn);
       btn1 = (Button) findViewById(R.id.askBtn);
       setSupportActionBar(toolbar);
       toolbar.inflateMenu(R.menu.main_menu);


       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(ProfileActivity.this,askDriver.class));
           }
       });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,askHitchhiker.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.LogOut:
                Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                break;

        }

        return true;
    }
}
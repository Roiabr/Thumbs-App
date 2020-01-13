package com.example.thumbs_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {


    DatabaseReference databaseList;
    Toolbar toolbar,toolTab;
    TabLayout layout;
    ViewPager viewPager;

    ListView listView;

    FloatingActionButton add;
    PageAdapter pageAdapter;
    List<Tremp> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseList = FirebaseDatabase.getInstance().getReference("Drives");

        list = new ArrayList<>();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolTab = (Toolbar) findViewById(R.id.toolTab);
        layout = (TabLayout) findViewById(R.id.TabLyaout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        layout.setupWithViewPager(viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new DrivesFragment(),"נהגים");
        pageAdapter.addFragment(new HichFragment(),"טרמפיסטים");
        viewPager.setAdapter(pageAdapter);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main_menu);

        add = (FloatingActionButton) findViewById(R.id.floatingAdd);

        listView = (ListView) findViewById(R.id.listViewTremp);





//       btn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               startActivity(new Intent(ProfileActivity.this,askDriver.class));
//           }
//       });

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ProfileActivity.this,askHitchhiker.class));
//            }
//        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,askDriver.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseList.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot trempData : dataSnapshot.getChildren()){
                    Tremp tremp = trempData.getValue(Tremp.class);
                    list.add(tremp);
                }
                final DrivesList adpter = new DrivesList(ProfileActivity.this,list);
                listView.setAdapter(adpter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

            case R.id.MYDrives:
                startActivity(new Intent(ProfileActivity.this, MyPrivateDrivers.class));
            case R.id.MYProfile:
               // startActivity(new Intent(ProfileActivity.this, OpenDialog.class));//NEED TO CREATE NEW CLASS




        }

        return true;
    }
}
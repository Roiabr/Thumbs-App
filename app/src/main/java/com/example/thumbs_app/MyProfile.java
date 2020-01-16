package com.example.thumbs_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {

    Toolbar toolbar;
    TextView name;
    TextView phone;
    TextView car;
    DatabaseReference databaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        name = (TextView) findViewById(R.id.driverName);
        phone = (TextView) findViewById(R.id.phoneDriver);
        car = (TextView) findViewById(R.id.car);


        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseList = FirebaseDatabase.getInstance().getReference("Users").child(currentFirebaseUser.getUid());

        databaseList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user= dataSnapshot.getValue(Users.class);
                String name1 = user.getName();
                String phone1 = user.getPhone();
                String car1 = user.getCar();

                name.setText(name1);
                phone.setText(phone1);
                car.setText(car1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

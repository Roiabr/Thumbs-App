package com.example.thumbs_app;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class askDriver extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Tremp tremp;
    TextView name;
    TextView phone;

    Spinner spinner,spinner2,spinner3;
    TextView Time;
    TextView Time2;

    Calendar calendar = Calendar.getInstance();
    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
    int currentMinute = calendar.get(Calendar.MINUTE);
    Button submit;
    String nameDriver;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_driver);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Drives");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentFirebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                Log.d("me:",user.getName());
                Log.d("me:",user.getPhone());
                nameDriver =  user.getName();
                String phoneDriver = user.getPhone();
                name =  findViewById(R.id.editTextDriver);
                name.setText(nameDriver);
                phone = findViewById(R.id.phoneText);
                phone.setText(phoneDriver);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });




        spinner = findViewById(R.id.spinnerLoc);
        spinner3 = findViewById(R.id.spinnerLoc2);
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.location,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.days,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);


        Time = findViewById(R.id.textViewTime1);
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(askDriver.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        Time.setText(String.format("%02d:%02d", hourOfDay, minutes)+" "+ amPm);
                    }
                }, currentHour, currentMinute, true);
                timePickerDialog.show();

            }
        });
        Time2 = findViewById(R.id.textViewTime2);
        Time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(askDriver.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        Time2.setText(String.format("%02d:%02d", hourOfDay, minutes)+" "+ amPm);
                    }
                }, currentHour, currentMinute, true);
                timePickerDialog.show();
            }
        });
        submit = (Button)findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDrive();
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
//        location = (TextView) findViewById (R.id.textViewLocation);
//        location.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void addDrive(){
        String Name = name.getText().toString().trim();
        String TimeStart = Time2.getText().toString().trim();
        String TimeEnd = Time.getText().toString().trim();
        String LocationStart = spinner.getSelectedItem().toString();
        String LocationEnd = spinner3.getSelectedItem().toString();
        String day = spinner2.getSelectedItem().toString();

        if(!TextUtils.isEmpty(Name)){

            String userid=null;
            try {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                userid = currentFirebaseUser.getUid();
            } finally {

            }
            String id =  databaseReference.push().getKey();
            Tremp drive = new Tremp(id,userid,Name,TimeStart,TimeEnd,LocationStart,LocationEnd,day);

            databaseReference.child("Drives").child(id).setValue(drive);
            databaseReference2.child(id).setValue(drive);
            Toast.makeText(this, "Drive Added", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
    }
}
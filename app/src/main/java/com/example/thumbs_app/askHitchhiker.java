package com.example.thumbs_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class askHitchhiker extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Tremp tremp;
    EditText name;
    EditText phone;

    Spinner spinner,spinner2,spinner3;
    TextView Time;
    TextView Time2;

    Calendar calendar = Calendar.getInstance();
    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
    int currentMinute = calendar.get(Calendar.MINUTE);

    Button submit;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_hitchhiker);

        databaseReference = FirebaseDatabase.getInstance().getReference("Hitchhiker");

        name = findViewById(R.id.editTextDriver);
        phone = findViewById(R.id.phoneText);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(askHitchhiker.this, new TimePickerDialog.OnTimeSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(askHitchhiker.this, new TimePickerDialog.OnTimeSetListener() {
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
                addHitchhiker();
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
    public void addHitchhiker(){
        String Name = name.getText().toString().trim();
        String TimeStart = Time2.getText().toString().trim();
        String TimeEnd = Time.getText().toString().trim();
        String LocationStart = spinner.getSelectedItem().toString();
        String LoctionEnd = spinner3.getSelectedItem().toString();
        String day = spinner2.getSelectedItem().toString();

        if(!TextUtils.isEmpty(Name)){
            String id =  databaseReference.push().getKey();
            Tremp hitchhiker = new Tremp(id,Name,TimeStart,TimeEnd,LocationStart,LoctionEnd,day);
            databaseReference.child(id).setValue(hitchhiker);
            Toast.makeText(this, "Hitchhiker Added", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
    }
}

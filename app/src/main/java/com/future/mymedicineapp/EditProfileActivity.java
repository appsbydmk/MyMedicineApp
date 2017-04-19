package com.future.mymedicineapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EditProfileActivity extends Activity implements View.OnClickListener{
    Calendar myCalendar = Calendar.getInstance();
    TextView userDob;
    MedicineDbHandler dbHandler;
    Button saveButton;
    User user;
    EditText userName, medicalInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        dbHandler = MedicineDbHandler.getInstance(this);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        userDob = (TextView)findViewById(R.id.userdob);
        userDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        userName = (EditText)findViewById(R.id.username);
        medicalInfo = (EditText)findViewById(R.id.medicalinfo);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        userDob.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        if(v == saveButton)
        {
            dbHandler.truncUser();
            user  = new User();
            user.setUserName(userName.getText().toString());
            user.setUserDob(userDob.getText().toString());
            user.setUserMedicalInfo(medicalInfo.getText().toString());
            dbHandler.addUser(user);
            this.finish();
        }
    }
}

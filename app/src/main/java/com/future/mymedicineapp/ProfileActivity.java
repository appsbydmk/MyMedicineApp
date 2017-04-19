package com.future.mymedicineapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileActivity extends Activity implements View.OnClickListener{

    TextView userName, userDob, medicalInfo;
    Button editButton;
    Intent intent;
    MedicineDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userDob = (TextView)findViewById(R.id.userDob);
        userName = (TextView)findViewById(R.id.userName);
        medicalInfo = (TextView)findViewById(R.id.userMedicalInfo);
        editButton = (Button)findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        dbHandler = MedicineDbHandler.getInstance(this);
        printUserInfo();
    }

    public void printUserInfo()
    {
        User user = dbHandler.printUser();
        if(user == null)
        {
            userName.setText("");
            userDob.setText("");
            medicalInfo.setText("");
        }
        else
        {
            userName.setText(user.getUserName());
            userDob.setText(user.getUserDob());
            medicalInfo.setText(user.getUserMedicalInfo());
        }
    }
    @Override
    public void onClick(View v)
    {
        if(v == editButton)
        {
            intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        userDob = (TextView)findViewById(R.id.userDob);
        userName = (TextView)findViewById(R.id.userName);
        medicalInfo = (TextView)findViewById(R.id.userMedicalInfo);
        editButton = (Button)findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        dbHandler = MedicineDbHandler.getInstance(this);
        printUserInfo();
    }

}

package com.future.mymedicineapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StatsActivity extends Activity implements View.OnClickListener{

    Button buttonBp, buttonSugar, buttonWeight, buttonOther;
    TextView txtBp, txtSugar, txtWeight, txtOther;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate = format.format(date);
    MedicineDbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        dbHandler = MedicineDbHandler.getInstance(this);
        buttonBp = (Button)findViewById(R.id.buttonBp);
        buttonBp.setOnClickListener(this);
        buttonSugar = (Button)findViewById(R.id.buttonSl);
        buttonSugar.setOnClickListener(this);
        buttonWeight = (Button)findViewById(R.id.buttonWt);
        buttonWeight.setOnClickListener(this);
        buttonOther = (Button)findViewById(R.id.buttonOther);
        buttonOther.setOnClickListener(this);
        txtBp = (TextView)findViewById(R.id.blood_pressure);
        txtBp.setOnClickListener(this);
        txtSugar = (TextView)findViewById(R.id.sugar_level);
        txtSugar.setOnClickListener(this);
        txtWeight = (TextView)findViewById(R.id.weight);
        txtWeight.setOnClickListener(this);
        txtOther = (TextView)findViewById(R.id.other);
        txtOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == buttonBp || v == txtBp)
        {
            final Dialog dialog = new Dialog(StatsActivity.this);
            dialog.setContentView(R.layout.blood_pressure_fragement);
            dialog.setTitle("Blood Pressure");
            dialog.setCancelable(false);
            dialog.show();
            Button saveButton;

            final EditText minBp, maxBp;
            minBp = (EditText)dialog.findViewById(R.id.min_bp);
            maxBp = (EditText)dialog.findViewById(R.id.max_bp);
            saveButton = (Button)dialog.findViewById(R.id.save_button);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BloodPressure bp = new BloodPressure();
                    bp.setStatDate(currentDate);
                    bp.setStatMinBp(minBp.getText().toString());
                    bp.setStatMaxBp(maxBp.getText().toString());
                    if(dbHandler.isBpRecorded())
                    {
                        Toast.makeText(getApplicationContext(), "You have recorded your blood pressure today."
                                , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        dbHandler.setBloodPressure(bp);
                        Toast.makeText(getApplicationContext(), "Saved!"
                                , Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
            Button cancelButton;
            cancelButton= (Button)dialog.findViewById(R.id.cancel_button);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if(v == buttonSugar || v == txtSugar)
        {
            final Dialog dialog = new Dialog(StatsActivity.this);
            dialog.setContentView(R.layout.sugar_level_fragment);
            dialog.setTitle("Sugar Level");
            dialog.setCancelable(false);
            dialog.show();
            Button saveButton;
            final EditText sugarLevel;
            sugarLevel = (EditText)dialog.findViewById(R.id.txt_sugar_level);
            saveButton = (Button)dialog.findViewById(R.id.save_button);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SugarLevel sl = new SugarLevel();
                    sl.setStatDate(currentDate);
                    sl.setStatSugarLevel(sugarLevel.getText().toString());
                    if(dbHandler.isSlRecorded())
                    {
                        Toast.makeText(getApplicationContext(), "You have recorded your sugar level today."
                                , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        dbHandler.setSugarLevel(sl);
                        Toast.makeText(getApplicationContext(), "Saved!"
                                , Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
            Button cancelButton;
            cancelButton= (Button)dialog.findViewById(R.id.cancel_button);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if(v == buttonWeight || v == txtWeight)
        {
            final Dialog dialog = new Dialog(StatsActivity.this);
            dialog.setContentView(R.layout.weight_fragment);
            dialog.setTitle("Weight");
            dialog.setCancelable(false);
            dialog.show();
            final EditText weight;
            Button saveButton;
            final Switch wtSwitch;
            wtSwitch = (Switch)dialog.findViewById(R.id.weight_unit);
            weight = (EditText)dialog.findViewById(R.id.txt_weight);
            saveButton= (Button)dialog.findViewById(R.id.save_button);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Weight wt = new Weight();
                    wt.setStatDate(currentDate);
                    wt.setStatWeight(weight.getText().toString());
                    if(dbHandler.isWtRecorded())
                    {
                        Toast.makeText(getApplicationContext(), "You have recorded your weight today."
                                , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        dbHandler.setWeight(wt);
                        Toast.makeText(getApplicationContext(), "Saved!"
                                , Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
            Button cancelButton;
            cancelButton= (Button)dialog.findViewById(R.id.cancel_button);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if(v == buttonOther || v == txtOther)
        {
            final Dialog dialog = new Dialog(StatsActivity.this);
            dialog.setContentView(R.layout.other_fragment);
            dialog.setTitle("Other");
            dialog.setCancelable(false);
            dialog.show();
            Button saveButton;
            final EditText other;
            other = (EditText)dialog.findViewById(R.id.other_stat);
            saveButton= (Button)dialog.findViewById(R.id.save_button);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Other ot = new Other();
                    ot.setStatDate(currentDate);
                    ot.setStatOther(other.getText().toString());
                    dbHandler.setOther(ot);
                    Toast.makeText(getApplicationContext(), "Saved!"
                            , Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            Button cancelButton;
            cancelButton= (Button)dialog.findViewById(R.id.cancel_button);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }
}

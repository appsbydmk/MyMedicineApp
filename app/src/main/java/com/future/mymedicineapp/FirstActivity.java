package com.future.mymedicineapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FirstActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    MedicineDbHandler dbHandler;
    ListView drugListView;
    Intent intent;
    Button drugsListButton, statButton;
    ArrayAdapter myArrayAdapter;
    TextView userName;

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate = format.format(date);

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        dbHandler = MedicineDbHandler.getInstance(this);
        drugsListButton = (Button)findViewById(R.id.drugsListButton);
        drugsListButton.setOnClickListener(this);
        statButton = (Button)findViewById(R.id.stats);
        statButton.setOnClickListener(this);
        drugListView = (ListView)findViewById(R.id.drugListView);
        drugListView.setOnItemClickListener(this);
        userName = (TextView)findViewById(R.id.userName);
        if(dbHandler.getUserName() == null)
        {
            userName.setText("Hello");
        }
        else
            userName.setText("Hello, " + dbHandler.getUserName());
        populateDrugList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        if(v == drugsListButton)
        {
            intent = new Intent(this, DrugsListActivity.class);
            startActivity(intent);
        }
        if(v == statButton)
        {
            intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
        }
    }

    private void populateDrugList()
    {
        ArrayList<String> myList = dbHandler.getCurrentDrugs();
        myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        drugListView.setAdapter(myArrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
    {
        final String drugName = drugListView.getItemAtPosition(position).toString();
        if(dbHandler.isComplianceRecorded(drugName))
        {
            Compliance cl = dbHandler.getCompliance(drugName);
            if(cl.getStatus().equals("yes"))
            {
                Toast.makeText(getApplicationContext(), "You took this medicine!"
                        , Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "You did not take this medicine!"
                        , Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            final Dialog dialog = new Dialog(FirstActivity.this);
            dialog.setContentView(R.layout.drug_compliance_dialog);
            final DrugsList dl = dbHandler.getDrug(drugName);
            dialog.setTitle(drugName);
            dialog.setCancelable(false);
            dialog.show();
            Button yesButton;
            yesButton= (Button)dialog.findViewById(R.id.yes_button);
            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Compliance cl = new Compliance();
                    cl.setDrugName(drugName);
                    cl.setDate(currentDate);
                    cl.setStatus("yes");
                    dbHandler.setCompliance(cl);
                    Toast.makeText(getApplicationContext(), "You took this medicine!"
                            , Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            Button noButton;
            noButton= (Button)dialog.findViewById(R.id.no_button);
            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Compliance cl = new Compliance();
                    cl.setDrugName(drugName);
                    cl.setDate(currentDate);
                    cl.setStatus("no");
                    dbHandler.setCompliance(cl);
                    Toast.makeText(getApplicationContext(), "You did not take this medicine!"
                            , Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
    }
}

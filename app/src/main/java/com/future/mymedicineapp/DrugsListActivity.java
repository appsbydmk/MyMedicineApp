package com.future.mymedicineapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class DrugsListActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    MedicineDbHandler dbHandler;
    ListView drugListView;
    Button addDrugButton;
    ArrayAdapter<String> myArrayAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        populateDrugList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_list);
        dbHandler = MedicineDbHandler.getInstance(this);
        drugListView = (ListView) findViewById(R.id.drugListView);
        populateDrugList();
        drugListView.setOnItemClickListener(this);
        addDrugButton = (Button) findViewById(R.id.buttonAdd);
        addDrugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DrugsListActivity.this);
                dialog.setContentView(R.layout.add_drug_dialog);
                dialog.setTitle("Add New Drug");
                dialog.setCancelable(false);
                dialog.show();
                final EditText drug_name = (EditText) dialog.findViewById(R.id.drug_name);
                final EditText drug_strength = (EditText) dialog.findViewById(R.id.drug_strength);
                final CheckBox curr_taking = (CheckBox) dialog.findViewById(R.id.curr_taking);
                Button saveButton;
                saveButton = (Button) dialog.findViewById(R.id.save_button);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DrugsList dl = new DrugsList();
                        dl.setDrugName(drug_name.getText().toString());
                        dl.setDrugStrength(drug_strength.getText().toString());
                        if (curr_taking.isChecked()) {
                            dl.setCurrentlyTaking("true");
                        } else {
                            dl.setCurrentlyTaking("false");
                        }
                        dbHandler.addDrug(dl);
                        myArrayAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                Button cancelButton;
                cancelButton = (Button) dialog.findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void populateDrugList() {
        ArrayList<String> myList = dbHandler.getAllDrugs();
        myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        drugListView.setAdapter(myArrayAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Dialog dialog = new Dialog(DrugsListActivity.this);
        dialog.setContentView(R.layout.add_drug_dialog);
        dialog.setTitle("Edit Drug");
        dialog.setCancelable(false);
        dialog.show();
        String drug = drugListView.getItemAtPosition(position).toString();
        DrugsList dl = dbHandler.getDrug(drug);
        final EditText drug_name = (EditText) dialog.findViewById(R.id.drug_name);
        drug_name.setText(dl.getDrugName());
        final EditText drug_strength = (EditText) dialog.findViewById(R.id.drug_strength);
        drug_strength.setText(dl.getDrugStrength());
        final CheckBox curr_taking = (CheckBox) dialog.findViewById(R.id.curr_taking);
        if (dl.isCurrentlyTaking().equals("true")) {
            curr_taking.setChecked(true);
        } else {
            curr_taking.setChecked(false);
        }
        Button saveButton;
        saveButton = (Button) dialog.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrugsList dl = new DrugsList();
                dl.setDrugName(drug_name.getText().toString());
                dl.setDrugStrength(drug_strength.getText().toString());
                if (curr_taking.isChecked()) {
                    dl.setCurrentlyTaking("true");
                } else {
                    dl.setCurrentlyTaking("false");
                }
                dbHandler.updateDrugStatus(dl);
                myArrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        Button cancelButton;
        cancelButton = (Button) dialog.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}

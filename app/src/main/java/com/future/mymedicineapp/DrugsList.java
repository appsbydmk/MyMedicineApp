package com.future.mymedicineapp;

public class DrugsList
{
    private int _ID;
    private String drugName;
    private String drugStrength;
    private String currentlyTaking;

    public DrugsList()
    {

    }

    public DrugsList(int _ID, String drugName, String drugStrength, String currentlyTaking) {
        this._ID = _ID;
        this.drugName = drugName;
        this.drugStrength = drugStrength;
        this.currentlyTaking = currentlyTaking;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugStrength() {
        return drugStrength;
    }

    public void setDrugStrength(String drugStrength) {
        this.drugStrength = drugStrength;
    }

    public String isCurrentlyTaking() {
        return currentlyTaking;
    }

    public void setCurrentlyTaking(String currentlyTaking) {
        this.currentlyTaking = currentlyTaking;
    }
}

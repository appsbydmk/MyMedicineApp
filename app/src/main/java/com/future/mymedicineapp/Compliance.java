package com.future.mymedicineapp;

public class Compliance
{
    private int _ID;
    private String drugName;
    private String date;
    private String status;

    public Compliance() {
    }

    public Compliance(int _ID, String drugName, String date, String status) {
        this._ID = _ID;
        this.drugName = drugName;
        this.date = date;
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

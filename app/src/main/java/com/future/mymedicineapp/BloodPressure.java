package com.future.mymedicineapp;

public class BloodPressure
{
    private String statDate;
    private String statMinBp;
    private String statMaxBp;

    public BloodPressure(String statDate, String statMaxBp, String statMinBp) {
        this.statDate = statDate;
        this.statMaxBp = statMaxBp;
        this.statMinBp = statMinBp;
    }

    public BloodPressure() {
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public String getStatMinBp() {
        return statMinBp;
    }

    public void setStatMinBp(String statMinBp) {
        this.statMinBp = statMinBp;
    }

    public String getStatMaxBp() {
        return statMaxBp;
    }

    public void setStatMaxBp(String statMaxBp) {
        this.statMaxBp = statMaxBp;
    }
}

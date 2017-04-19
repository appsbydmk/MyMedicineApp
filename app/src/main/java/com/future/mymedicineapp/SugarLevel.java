package com.future.mymedicineapp;

public class SugarLevel {
    private String statDate;
    private String statSugarLevel;

    public SugarLevel() {
    }

    public SugarLevel(String statDate, String statSugarLevel) {
        this.statDate = statDate;
        this.statSugarLevel = statSugarLevel;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public String getStatSugarLevel() {
        return statSugarLevel;
    }

    public void setStatSugarLevel(String statSugarLevel) {
        this.statSugarLevel = statSugarLevel;
    }
}

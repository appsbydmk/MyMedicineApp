package com.future.mymedicineapp;

public class Weight
{
    private String statDate;
    private String statWeight;

    public Weight(String statDate, String statWeight) {
        this.statDate = statDate;
        this.statWeight = statWeight;
    }

    public Weight() {
    }

    public String getStatWeight() {
        return statWeight;
    }

    public void setStatWeight(String statWeight) {
        this.statWeight = statWeight;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }
}

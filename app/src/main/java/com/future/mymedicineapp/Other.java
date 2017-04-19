package com.future.mymedicineapp;

public class Other
{
    private String statDate;
    private String statOther;

    public Other(String statDate, String statOther) {
        this.statDate = statDate;
        this.statOther = statOther;
    }

    public Other() {
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public String getStatOther() {
        return statOther;
    }

    public void setStatOther(String statOther) {
        this.statOther = statOther;
    }
}

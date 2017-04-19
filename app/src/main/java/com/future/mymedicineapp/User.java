package com.future.mymedicineapp;

public class User
{
    private int _ID;
    private String userName;
    private String userDob;
    private String userMedicalInfo;
    public User()
    {

    }

    public User(int _ID, String userName, String userDob, String userMedicalInfo) {
        this._ID = _ID;
        this.userName = userName;
        this.userDob = userDob;
        this.userMedicalInfo = userMedicalInfo;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getUserMedicalInfo() {
        return userMedicalInfo;
    }

    public void setUserMedicalInfo(String userMedicalInfo) {
        this.userMedicalInfo = userMedicalInfo;
    }
}

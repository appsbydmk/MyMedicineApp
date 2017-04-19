package com.future.mymedicineapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.sql.SQLDataException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineDbHandler extends SQLiteOpenHelper
{
    private static MedicineDbHandler ourInstance;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbmedicine.db";
    private static final String PACKAGE_NAME = "com.future.mymedicineapp";

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate = format.format(date);

    //User Table
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USERID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USERDOB = "userdob";
    private static final String COLUMN_USERMEDICALINFO = "usermedicalinfo";

    //DrugsList Table
    private static final String TABLE_DRUGSLIST = "drugslist";
    private static final String COLUMN_DRUGID = "_id";
    private static final String COLUMN_DRUGNAME = "drugname";
    private static final String COLUMN_DRUGSTRENGTH = "drugstrength";
    private static final String COLUMN_CURRTAKING = "currtaking";

    //BloodPresssure Table
    private static final String TABLE_BLOODPRESSURE = "bloodpressure";
    private static final String COLUMN_BP_DATE = "date";
    private static final String COLUMN_MIN_BP = "statminbp";
    private static final String COLUMN_MAX_BP = "statmaxbp";

    //SugalLevel Table
    private static final String TABLE_SUGARLEVEL = "sugarlevel";
    private static final String COLUMN_SL_DATE= "date";
    private static final String COLUMN_SL = "statsugar";

    //Weight Table
    private static final String TABLE_WEIGHT = "weight";
    private static final String COLUMN_WT_DATE= "date";
    private static final String COLUMN_WT = "statweight";

    //Other Table
    private static final String TABLE_OTHER = "other";
    private static final String COLUMN_OT_DATE= "date";
    private static final String COLUMN_OT = "statother";

    //Compliance Table
    private static final String TABLE_COMPLIANCE = "compliance";
    private static final String COLUMN_CID = "_id";
    private static final String COLUMN_CDRUGNAME = "drugname";
    private static final String COLUMN_CDATE = "cdate";
    private static final String COLUMN_CSTATUS = "cstatus";

    //Create table statements
    final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_USERDOB + " TEXT, " +
            COLUMN_USERMEDICALINFO + " TEXT " +
            ");";

    final String CREATE_TABLE_DRUGS_LIST = "CREATE TABLE " + TABLE_DRUGSLIST + "(" +
            COLUMN_DRUGID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DRUGNAME + " TEXT, " +
            COLUMN_DRUGSTRENGTH + " TEXT, " +
            COLUMN_CURRTAKING + " TEXT " +
            ");";

    final String CREATE_TABLE_BLOOD_PRESSURE = "CREATE TABLE " + TABLE_BLOODPRESSURE + "(" +
            COLUMN_BP_DATE + " TEXT PRIMARY KEY, " +
            COLUMN_MIN_BP + " TEXT, " +
            COLUMN_MAX_BP + " TEXT " +
            ")";

    final String CREATE_TABLE_SUGAR_LEVEL = "CREATE TABLE " + TABLE_SUGARLEVEL + "(" +
            COLUMN_SL_DATE + " TEXT PRIMARY KEY, " +
            COLUMN_SL + " TEXT " +
            ")";

    final String CREATE_TABLE_WEIGHT = "CREATE TABLE " + TABLE_WEIGHT + "(" +
            COLUMN_WT_DATE + " TEXT PRIMARY KEY, " +
            COLUMN_WT + " TEXT " +
            ")";

    final String CREATE_TABLE_OTHER = "CREATE TABLE " + TABLE_OTHER + "(" +
            COLUMN_OT_DATE + " TEXT PRIMARY KEY, " +
            COLUMN_OT + " TEXT " +
            ")";

    final String CREATE_TABLE_COMPLIANCE = " CREATE TABLE " + TABLE_COMPLIANCE + "(" +
            COLUMN_CID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CDRUGNAME + " INTEGER, " +
            COLUMN_CDATE + " TEXT, " +
            COLUMN_CSTATUS + " TEXT " +
            ")";

    public static synchronized MedicineDbHandler getInstance(Context context)
    {
        if(ourInstance == null)
        {
            ourInstance = new MedicineDbHandler(context.getApplicationContext());
        }
        return ourInstance;
    }

    private MedicineDbHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_DRUGS_LIST);
        db.execSQL(CREATE_TABLE_BLOOD_PRESSURE);
        db.execSQL(CREATE_TABLE_SUGAR_LEVEL);
        db.execSQL(CREATE_TABLE_WEIGHT);
        db.execSQL(CREATE_TABLE_OTHER);
        db.execSQL(CREATE_TABLE_COMPLIANCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUGSLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOODPRESSURE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUGARLEVEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLIANCE);
        onCreate(db);
    }

    //Add a new row to User Table
    public void addUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUserName());
        values.put(COLUMN_USERDOB, user.getUserDob());
        values.put(COLUMN_USERMEDICALINFO, user.getUserMedicalInfo());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    //Truncate User Table
    public void truncUser()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER + ";");
    }

    //Print the user
    public User printUser()
    {
        User user = new User();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;
        Cursor c = db.rawQuery(query, null);
        if(c == null)
        {
            return null;
        }
        else
        {
            c.moveToFirst();
            if(c.isFirst())
            {
                user.set_ID(c.getInt(0));
                user.setUserName(c.getString(1));
                user.setUserDob(c.getString(2));
                user.setUserMedicalInfo(c.getString(3));
            }
            return user;
        }
    }

    //Add a new drug
    public void addDrug(DrugsList dl)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DRUGNAME, dl.getDrugName());
        values.put(COLUMN_DRUGSTRENGTH, dl.getDrugStrength());
        values.put(COLUMN_CURRTAKING, dl.isCurrentlyTaking());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DRUGSLIST, null, values);
        db.close();
    }

    //Update drug status
    public void updateDrugStatus(DrugsList dl)
    {
        String drugName = dl.getDrugName();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DRUGNAME, dl.getDrugName());
        values.put(COLUMN_DRUGSTRENGTH, dl.getDrugStrength());
        values.put(COLUMN_CURRTAKING, dl.isCurrentlyTaking());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_DRUGSLIST, values, COLUMN_DRUGNAME + " = '" + drugName + "'", null);
        db.close();
    }

    //Return all drugs in DrugsList
    public ArrayList<String> getAllDrugs()
    {
        ArrayList<String> myList = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DRUGSLIST, null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            myList.add(c.getString(c.getColumnIndex(COLUMN_DRUGNAME)));
            c.moveToNext();
        }
        db.close();
        return myList;
    }

    public ArrayList<String> getCurrentDrugs()
    {
        ArrayList<String> myList = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DRUGSLIST + " WHERE " + COLUMN_CURRTAKING + " = " + "\"true\"", null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            myList.add(c.getString(c.getColumnIndex(COLUMN_DRUGNAME)));
            c.moveToNext();
        }
        db.close();
        return myList;
    }

    public void setBloodPressure(BloodPressure bp)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_BP_DATE, bp.getStatDate());
        values.put(COLUMN_MIN_BP, bp.getStatMinBp());
        values.put(COLUMN_MAX_BP, bp.getStatMaxBp());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_BLOODPRESSURE, null, values);
        db.close();

    }

    public void setSugarLevel(SugarLevel sl)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SL_DATE, sl.getStatDate());
        values.put(COLUMN_SL, sl.getStatSugarLevel());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SUGARLEVEL, null, values);
        db.close();
    }

    public void setWeight(Weight wt)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WT_DATE, wt.getStatDate());
        values.put(COLUMN_WT, wt.getStatWeight());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WEIGHT, null, values);
        db.close();
    }

    public void setOther(Other ot)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_OT_DATE, ot.getStatDate());
        values.put(COLUMN_OT, ot.getStatOther());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_OTHER, null, values);
        db.close();
    }

    public DrugsList getDrug(String drugname)
    {
        DrugsList dl = new DrugsList();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM drugslist WHERE drugname = ?", new String[]{drugname});
        c.moveToFirst();
        if(!c.isAfterLast())
        {
            dl.set_ID(c.getInt(0));
            dl.setDrugName(c.getString(1));
            dl.setDrugStrength(c.getString(2));
            dl.setCurrentlyTaking(c.getString(3));
        }
        return dl;
    }

    public String getUserName()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user", null);
        c.moveToFirst();
        if(!c.isAfterLast())
        {
            return c.getString(1);
        }
        else
        {
            return null;
        }
    }

    public boolean isComplianceRecorded(String drugName) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM compliance WHERE drugname = ? AND cdate = ?", new String[] {drugName, currentDate});
        if(c != null && c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isBpRecorded()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bloodpressure WHERE date = ?" , new String[]{currentDate});
        if(c != null && c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isSlRecorded()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM sugarlevel WHERE date = ?", new String[]{currentDate});
        if(c != null && c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isWtRecorded()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM weight WHERE date = ?", new String[]{currentDate});
        if(c != null && c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setCompliance(Compliance cl)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CDRUGNAME, cl.getDrugName());
        values.put(COLUMN_CDATE, currentDate);
        values.put(COLUMN_CSTATUS, cl.getStatus());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_COMPLIANCE, null, values);
        db.close();
    }

    public Compliance getCompliance(String drugName)
    {
        Compliance cl = new Compliance();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM compliance WHERE drugname = ? AND cdate = ?", new String[]{drugName, currentDate});
        c.moveToFirst();
        if(!c.isAfterLast())
        {
            cl.set_ID(c.getInt(0));
            cl.setDrugName(c.getString(1));
            cl.setDate(c.getString(2));
            cl.setStatus(c.getString(3));
            c.moveToNext();
        }
        return cl;
    }

    public Cursor getComplianceRecords()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_COMPLIANCE, null);
        return c;
    }

    public Cursor getBloodPressure()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_BLOODPRESSURE, null);
        return c;
    }

    public Cursor getSugarLevel()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SUGARLEVEL, null);
        return c;
    }

    public Cursor getWeight()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_WEIGHT, null);
        return c;
    }

    public Cursor getOther()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_OTHER, null);
        return c;
    }

    public Cursor getDrugsList()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DRUGSLIST, null);
        return c;
    }

}

package com.future.mymedicineapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class AboutActivity extends Activity implements View.OnClickListener{

    private ImageView appLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toast.makeText(this, "Tap On Logo To Know More", Toast.LENGTH_LONG).show();

        appLogo = (ImageView)findViewById(R.id.app_logo);
        appLogo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(appLogo == v)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(AboutActivity.this).create();
            alertDialog.setTitle(R.string.about);
            alertDialog.setMessage("My Medicine App is a health companion app for people. Thanks for using it!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}

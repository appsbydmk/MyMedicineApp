package com.future.mymedicineapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class HelpActivity extends Activity {

    TextView htProfile, htDrugsList, htStats, htCompliance;
    FragmentManager fm;
    FragmentTransaction ft;
    HtProfileFragment hft;
    HtDrugsListFragment hdt;
    HtStatsFragment hst;
    HtComplianceFragment hct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        htProfile = (TextView)findViewById(R.id.ht_profile);
        htProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                hft = (HtProfileFragment)fm.findFragmentByTag("hft");

                if(hft == null)
                {
                    hft = new HtProfileFragment();
                    ft.add(R.id.ht_profile_fragment, hft, "hft");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                }
                else
                {
                    ft.remove(hft);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }

                ft.commit();
            }
        });
        htDrugsList = (TextView)findViewById(R.id.ht_drugs_list);
        htDrugsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                hdt = (HtDrugsListFragment)fm.findFragmentByTag("hdt");

                if(hdt == null)
                {
                    hdt = new HtDrugsListFragment();
                    ft.add(R.id.ht_drugs_list_fragment, hdt, "hdt");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                }
                else
                {
                    ft.remove(hdt);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }

                ft.commit();
            }
        });
        htStats = (TextView)findViewById(R.id.ht_stats);
        htStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                hst = (HtStatsFragment)fm.findFragmentByTag("hst");

                if(hst == null)
                {
                    hst = new HtStatsFragment();
                    ft.add(R.id.ht_stats_fragment, hst, "hst");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                }
                else
                {
                    ft.remove(hst);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }

                ft.commit();
            }
        });
        htCompliance = (TextView)findViewById(R.id.ht_compliance);
        htCompliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                hct = (HtComplianceFragment)fm.findFragmentByTag("hct");

                if(hct == null)
                {
                    hct = new HtComplianceFragment();
                    ft.add(R.id.ht_compliance_fragment, hct, "hct");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                }
                else
                {
                    ft.remove(hct);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }

                ft.commit();
            }
        });
    }
}

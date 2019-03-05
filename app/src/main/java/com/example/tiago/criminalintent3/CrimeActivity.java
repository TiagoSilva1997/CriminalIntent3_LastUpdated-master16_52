package com.example.tiago.criminalintent3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String Extra_Crime_ID = "com.bignerdranch.android.criminalintent.extra_crime_id";

    public static Intent newIntent(Context packageContext,UUID crimeID){
        Intent intent = new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(Extra_Crime_ID,crimeID);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID crimeID = (UUID) getIntent().getSerializableExtra(Extra_Crime_ID);
        return  CrimeFragment.newInstance(crimeID);
    }

}

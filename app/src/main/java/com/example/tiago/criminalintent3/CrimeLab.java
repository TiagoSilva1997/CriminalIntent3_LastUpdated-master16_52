package com.example.tiago.criminalintent3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;


import com.example.tiago.criminalintent3.database.CrimeBaseHelper;
import com.example.tiago.criminalintent3.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.tiago.criminalintent3.database.CrimeDbSchema.*;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDataBase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }


    private CrimeLab(Context context) {

        mContext = context.getApplicationContext();
        mDataBase = new CrimeBaseHelper(mContext).getWritableDatabase();
   //     for (int i = 1; i <= 100; i++) {
    //        Crime crime = new Crime();
    //        crime.setMtitle("Crime #" + i);
    //        crime.setmSolved(i % 2 == 0);
   //         crime.setCallPolice(crime.isCallPoliceSolved());
   //         crime.setmDate(new Date());

    //    }

    }


    public List<Crime> getCrimes() {
        return new ArrayList<>();

    }

    public Crime getCrime(UUID id) {

        return null;
    }
    public void addCrime(Crime c){

    }

    public static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID,crime.getmID().toString());
        values.put(CrimeTable.Cols.TITLE,crime.getMtitle().toString());
        values.put(CrimeTable.Cols.DATE,crime.getmDate().toString());
        values.put(CrimeTable.Cols.SOLVED,crime.getmSolved().toString());
        return values;
    }

}
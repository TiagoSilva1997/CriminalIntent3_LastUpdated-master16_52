package com.example.tiago.criminalintent3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Crime mcrime;
    private EditText mTitleField;
    private EditText mSeverityField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mConfirmButton;
    private static final String ARG_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

    public static CrimeFragment newInstance(UUID crimeID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeID);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mcrime = CrimeLab.get(getActivity()).getCrime(crimeID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime_layout,container,false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);

        mTitleField.setText(mcrime.getMtitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
                public void beforeTextChanged( CharSequence s, int start, int count, int after) {

            }
            @Override
                public void onTextChanged( CharSequence s, int start, int before, int count) {
                mcrime.setMtitle(s.toString());
            }
            @Override
                public void afterTextChanged(Editable s) {

                }
        });


    //    mSeverityField = (EditText) v.findViewById(R.id.severity_lvl);
     //   mSeverityField.setText(mcrime.getSeverityLvl()); ---------------------------------------------------PROBLEMA ESTA AQUI!
        mSolvedCheckBox = (CheckBox)  v.findViewById(R.id.crime_solved);

        mSolvedCheckBox.setChecked(mcrime.getmSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mcrime.setmSolved(isChecked);
            }
        });

      mConfirmButton = (Button) v.findViewById(R.id.confirm_button);
      mConfirmButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mcrime.setMtitle(mTitleField.getText().toString());
            mcrime.setmSolved(mSolvedCheckBox.isChecked());
//            mcrime.setSeverityLvl(Integer.parseInt(mSeverityField.getText().toString())); ---------------------------------PROBLEMA AQUI
            Intent intent = new Intent(v.getContext(),CrimeListActivity.class);
            startActivity(intent);
        }
    });
    mDateButton = (Button) v.findViewById(R.id.crime_date);
    mDateButton.setText(mcrime.getmDate().toString());
    mDateButton.setEnabled(false);

    return v;

    }
}

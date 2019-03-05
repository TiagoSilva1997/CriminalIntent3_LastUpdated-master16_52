package com.example.tiago.criminalintent3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private ImageView mSolvedImageView;
    private Button mCallPolice;
    public static final String Crime_Clicked = "com.bignerdranch.android.criminalintent.crime_clicked";
    private int CrimePosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState!=null){
           CrimePosition = savedInstanceState.getInt(Crime_Clicked);
        }
        updateUI();
        return view;
    }
    public void refreshBlockOverlay(int position) { //CHALLENGE CAP 10
        mAdapter.notifyItemChanged(position);       //CHALLENGE CAP 10
    }
    @Override
    public void onResume(){
        super.onResume();

        updateUI();
    }
    private void updateUI(){                     //CHALLENGE CAP 10
        CrimeLab crimelab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimelab.getCrimes();
        if(mAdapter==null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
          //  mAdapter.notifyDataSetChanged();
          refreshBlockOverlay(CrimePosition); // CHALLENGE CAP 10

       }


    }
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;


        public CrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
            mCallPolice = (Button) itemView.findViewById(R.id.call_police);
        }

        public void bind(Crime crime) {

            mCrime = crime;
            mTitleTextView.setText(mCrime.getMtitle());
            String datetime = DateFormat.getDateInstance(DateFormat.FULL).format(mCrime.getmDate());  // Challenge CAP 9
            mDateTextView.setText(datetime); // Challenge CAP 9
            mSolvedImageView.setVisibility(crime.getmSolved()? View.VISIBLE : View.GONE);
            mCallPolice.setVisibility(crime.isCallPoliceSolved() ? View.VISIBLE : View.GONE);


        }


        @Override
        public void onClick(View view) {
         //   CrimePosition = getAdapterPosition();   // CHALLENGE CAP 10
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getmID());
            Log.d("crime", ""+ mCrime.getMtitle());

            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrime;
        public CrimeAdapter(List<Crime> crime){
            mCrime=crime;
        }

        @Override
        public int getItemViewType(int position){
            return position;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutinflater,parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrime.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrime.size();
        }
    }
}

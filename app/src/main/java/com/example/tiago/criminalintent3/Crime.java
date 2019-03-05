package com.example.tiago.criminalintent3;

import java.util.Date;
import java.util.UUID;
import java.util.Random;
public class Crime {

    private UUID mID;
    private String mtitle;
    private Date mDate;
    private Boolean mSolved;
    private int severityLvl;
    private boolean callPolice; // TEMPORARIO


    public Crime(UUID mID, Date mDate) {
        this.mID = mID;
        this.mDate = mDate;
    }
    public Crime(){
        mID = UUID.randomUUID();
        mDate = new Date();
    //    severityLvl = new Random().nextInt((10 - 1) + 1) + 1;

    }

    public UUID getmID() {
        return mID;
    }

    public int getSeverityLvl() {
        return severityLvl;
    }

    public void setSeverityLvl(int severityLvl) {
        this.severityLvl = severityLvl;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Boolean getmSolved() {
        return mSolved;
    }

    public void setmSolved(Boolean mSolved) {
        this.mSolved = mSolved;
    }

    public boolean isCallPoliceSolved() {
        if(getmSolved()){
            return false;
        }else{
            return true;
        }
    }
    public boolean isCallPolice(){
        return callPolice;
    }

    public void setCallPolice(boolean callPolice) {
        this.callPolice = callPolice;
    }
}

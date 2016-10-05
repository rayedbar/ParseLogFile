package com.therap.rayed;

/**
 * Created by Rayed Bin Wahed on 10/4/16.
 * Parse Log File
 */

public class LogEntry {

    private int mHour;
    private int mGETRequest;
    private int mPOSTRequest;
    private int mServerTime;

    public LogEntry(int mHour, int mGETRequest, int mPOSTRequest, int mServerTime) {
        this.mHour = mHour;
        this.mGETRequest = mGETRequest;
        this.mPOSTRequest = mPOSTRequest;
        this.mServerTime = mServerTime;
    }

    public int getmHour() { return mHour; }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public int getmGETRequest() {
        return mGETRequest;
    }

    public void setmGETRequest(int mGETRequest) {
        this.mGETRequest = mGETRequest;
    }

    public int getmPOSTRequest() {
        return mPOSTRequest;
    }

    public void setmPOSTRequest(int mPOSTRequest) {
        this.mPOSTRequest = mPOSTRequest;
    }

    public int getmServerTime() {
        return mServerTime;
    }

    public void setmServerTime(int mServerTime) {
        this.mServerTime = mServerTime;
    }

}

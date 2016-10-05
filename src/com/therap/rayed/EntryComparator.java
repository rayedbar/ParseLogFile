package com.therap.rayed;

import java.util.Comparator;

/**
 * Created by Rayed Bin Wahed on 10/4/16.
 * Parse Log File
 */

public class EntryComparator implements Comparator<LogEntry> {
    @Override
    public int compare(LogEntry logEntry, LogEntry logEntry2) {
        if (logEntry.getmServerTime() > logEntry2.getmServerTime()){
            return -1;
        } else if (logEntry.getmServerTime() < logEntry2.getmServerTime()){
            return 1;
        } else {
            if (logEntry.getmGETRequest() > logEntry2.getmGETRequest()){
                return -1;
            } else if(logEntry.getmGETRequest() < logEntry2.getmGETRequest()){
                return 1;
            } else {
                if (logEntry.getmPOSTRequest() > logEntry2.getmPOSTRequest()){
                    return -1;
                } else if (logEntry.getmPOSTRequest() < logEntry2.getmPOSTRequest()){
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}

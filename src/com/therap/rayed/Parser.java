package com.therap.rayed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rayed Bin Wahed on 10/4/16.
 * Parse Log File
 */

public class Parser {

    public static final int SIZE = 24;
    public List<LogEntry> logEntryList;

    public Parser() {
        logEntryList = new ArrayList<LogEntry>(SIZE);
        initializeList();
    }

    private void initializeList() {
        for (int i = 0; i < SIZE; ++i){
            logEntryList.add(new LogEntry(i, 0, 0, 0)) ;
        }
    }

    public void parse(String uri) {
        BufferedReader reader;
        boolean flag = false;
        try {
            reader = new BufferedReader(new FileReader("sample.log"));
            String line;
            while ((line = reader.readLine()) != null){
                if (containsUri(uri, line)){
                    int hour = getHour(line);
                    int getRequest = checkIfGET(line);
                    int serverTime = getServerTime(line);
                    LogEntry entry = logEntryList.get(hour);
                    if (getRequest == 1){
                        entry.setmGETRequest(entry.getmGETRequest() + 1);
                    } else {
                        entry.setmPOSTRequest(entry.getmPOSTRequest() + 1);
                    }
                    entry.setmServerTime(entry.getmServerTime() + serverTime);
                    flag = true;
                }
            }
            if (!flag){
                System.out.println("URI DOESN'T MATCH. PLEASE TRY AGAIN. Exiting!!!");
                System.exit(0);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getServerTime(String line) {
        String regex = "\\d+ms";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        int time = 0;
        if (matcher.find()){
            String s = matcher.group();
            String si = s.trim().replace("ms", "");
            time = Integer.parseInt(si.trim());
        }
        return time;
    }

    private int checkIfGET(String line) {
        String regex = "\\sG,\\s";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        if (m.find()){
            return 1; // Returns 1 if GET
        }
        return 0; // Returns 0 if POST
    }

    private int getHour(String line) {
        String regex = "\\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        int hour = -1;
        if (matcher.find()){
            String [] time = matcher.group().trim().split(":");
            hour = Integer.parseInt(time[0]);
        }
        return hour;
    }

    private boolean containsUri(String uri, String line) {
        String regex = ".*\\[" + uri + "\\].*";
        return Pattern.matches(regex, line);
    }
}

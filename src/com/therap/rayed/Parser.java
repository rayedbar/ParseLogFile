package com.therap.rayed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rayed on 10/4/16.
 */
public class Parser {

    List<LogEntry> entries;

    public Parser() {
        entries  = new LinkedList<LogEntry>();
    }

    public void parse(String uri) {
        BufferedReader reader;
        boolean flag = false;
        try {
            reader = new BufferedReader(new FileReader("sample.log"));
            String line;
            while ((line = reader.readLine()) != null){
                if (containsUri(uri, line)){
                    //parseLine(line);
                    int hour = getHour(line);
                    int getRequest = getGETRequest(line);
                    int postRequest = getPOSTRequest(line);
                    int serverTime = getServerTime(line);
                    LogEntry entry = new LogEntry(hour, getRequest, postRequest, serverTime);
                    entries.add(entry);
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
            String si = s.replace("ms", "");
            time = Integer.parseInt(si.trim());
        }
        return time;
    }

    private int getPOSTRequest(String line) {
        String regex = "\\sP,\\s";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        if (m.find()){
            //String s = m.group().trim();
            return 1;
        }
        return 0;
    }

    private int getGETRequest(String line) {
        String regex = "\\sG,\\s";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        if (m.find()){
            //String s = m.group().trim();
            return 1;
        }
        return 0;
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

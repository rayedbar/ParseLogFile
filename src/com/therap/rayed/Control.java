package com.therap.rayed;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Rayed Bin Wahed on 10/4/16.
 * Parse Log File
 */

public class Control {
    private String uri;
    private Parser parser;

    public Control() {
        parser = new Parser();
    }

    public void parseLogFile() {
        getUserInput();
        parser.parse(uri);
    }

    private void getUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter uri:");
        uri = sc.nextLine().trim();
    }

    public void showResults() {
        System.out.println("See out.log");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("out.log"));
            writer.write("Showing results for: " + uri);
            writer.newLine();
            writer.newLine();
            writer.write("     TIME          GET            POST             SERVER TIME");
            writer.newLine();
            String amPm = "am";
            for (LogEntry entry : parser.logEntryList){
                int hour = entry.getmHour();
                if (hour > 11){
                    amPm = "pm";
                }
                int length = String.valueOf(hour).length();
                String zero = "";
                if (length == 1){
                    zero = "0";
                }
                writer.write(zero + hour + ":00 " + amPm + " -> " + entry.getmGETRequest() + " GET Requests, " +
                        entry.getmPOSTRequest() + " POST Requests, " + "Total Server Time = " +
                        entry.getmServerTime() + "ms");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        Collections.sort(parser.logEntryList, new EntryComparator());
    }
}

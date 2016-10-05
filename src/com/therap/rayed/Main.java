package com.therap.rayed;

/**
 * Created by Rayed Bin Wahed on 10/4/16.
 * Parse Log File
 */

public class Main {

    public static void main(String[] args) {
        Control control = new Control();
        control.parseLogFile();
        //control.showResults();
        control.sort();
        control.showResults();
    }

}

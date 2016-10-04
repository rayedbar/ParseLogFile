package com.therap.rayed;

public class Main {

    public static void main(String[] args) {
        Control control = new Control();
        control.parseLogFile();
        control.showResults();
        control.sort();
        control.showResults();
    }
}

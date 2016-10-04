package com.therap.rayed;

import java.util.Scanner;

/**
 * Created by rayed on 10/4/16.
 */
public class Control {
    public String uri;
    public Parser parser;

    public Control() {
        parser = new Parser();
    }

    public void parseLogFile() {
        getUserInput();
        parser.parse(uri);
    }

    public void getUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter uri:");
        uri = sc.nextLine().trim();
    }

    public void showResults() {

    }

    public void sort() {

    }
}

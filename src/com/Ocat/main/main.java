package com.Ocat.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class main {
    
    public static String readLine() throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        return sc.readLine();
    }
    
    public static void main(String[] args) throws Exception {
        URL.setURLStreamHandlerFactory(new gopherHandlerFactory());
        
        String line = readLine();
        String url = "gopher://gopher.quux.org";
        if (line.startsWith("gopher://")) {
            url = line;
        }
        else {
            String line2 = "gopher://" + line;
            url = line2;
        }
        System.out.println("Selctor?");
        gopher n;
        String selector = readLine();
        if (selector.equals("")) {
            n = new gopher(url);
        }
        else if (selector.charAt(0)=='/') {
            n = new gopher(url, selector);
        }
        else {
            n = new gopher(url);
        }
        
        n.getPage();
    }
}

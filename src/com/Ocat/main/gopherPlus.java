package com.Ocat.main;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.nio.*;
import java.nio.charset.*;
import java.util.Arrays;

public class gopherPlus extends gopher {
    
    public gopherPlus(String url) {
        super(url);
    }
    
    public gopherPlus(String url, String selector) {
        super(url, selector);
    }
    
    private static void getRequest(URLConnection g) throws IOException {
        g.setDoOutput(true);
        String request = "\t+\r\n";
        g.getOutputStream().write(request.getBytes(charset()));
    }
    
    private static void getRequest(URLConnection g, String selctor) throws IOException {
        g.setDoOutput(true);
        String request = selctor + "\t+\r\n";
        g.getOutputStream().write(request.getBytes(charset()));
    }
    
    @Override
    public void getPage() throws Exception {
        
        URL newURL;
        newURL = new URL(getUrl());
        URLConnection g = newURL.openConnection();
        //output before input
        if (getSelector() != null) {
            getRequest(g, getSelector());
        }
        else {
            getRequest(g);
        }
        
        //input
        BufferedReader in = new BufferedReader(new InputStreamReader(g.getInputStream(), charset()));
        
        printOut(in);
        in.close();
        
    }
}

package com.Ocat.main;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.nio.*;
import java.nio.charset.*;
import java.util.Arrays;

public class gopher {
    
    private final static Charset ascii = Charset.forName("US-ASCII");
    private String url;
    private String selector;
    
    public gopher(String url) {
        this.url = url;
    }
    public gopher(String url, String select) {
        this.url = url;
        this.selector = select;
    }
    
    protected static Byte[] toObject(byte[] b) {
        Byte[] b2 = new Byte[b.length];
        for (int i = 0; i < b.length; i++) {
            b2[i] = b[i];
        }
        return b2;
    }
    
    protected static byte[] toPrimitive(Byte[] b) {
        byte[] b2 = new byte[b.length];
        for (int i = 0; i < b.length; i++) {
            b2[i] = b[i];
        }
        return b2;
    }
    
    protected String getUrl() {
        return url;
    }
    
    protected String getSelector() {
        return selector;
    }
    
    private static void getRequest(URLConnection g) throws IOException {
        g.setDoOutput(true);
        String request = "\r\n";
        g.getOutputStream().write(request.getBytes(charset()));
    }
    
    private static void getRequest(URLConnection g, String selctor) throws IOException {
        g.setDoOutput(true);
        String request = selctor + "\r\n";
        g.getOutputStream().write(request.getBytes(charset()));
    }
    
    protected static void printOut(BufferedReader in) {
        ArrayList<Byte[]> page = new ArrayList();
        boolean go = true;
        int i = 0;
        while (go) {
            //add this line to the array list
            try {
                page.add(i, toObject(in.readLine().getBytes(charset())));
            //look for the end of a gopher page
            } catch (Exception e) {
                break;
            }
            i++; 
        }
        int q = 0;
        while (q<i) {
            String H = new String(toPrimitive(page.get(q)), charset());
            System.out.println(trim(H));
            q++;
        }
    }
    
    protected static String trim(String H) {
        String string = H;
        int tab = 0;
        if (!string.equals(".")) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab = i;
                    break;
                }
            }
        }
        else {
            return "";
        }
        return string.substring(1, tab);
    }
    
    protected static Charset charset() {
        return ascii;
    }
    
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

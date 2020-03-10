package com.Ocat.cso;

import com.Ocat.main.gopherGraphical;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JTextPane;

public class cso {
    
    private final static Charset ascii = Charset.forName("US-ASCII");
    private gopherGraphical back;
    private URLConnection ccso;
    private JTextPane pane;
    
    public cso(String url, gopherGraphical back) throws MalformedURLException, IOException {
        URL newURL;
        newURL = new URL(url);
        ccso = newURL.openConnection();
        ccso.setDoOutput(true);
        this.back = back;
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
    
    private void write(String write) throws IOException {
        String send = write + "\r\n";
        ccso.getOutputStream().write(send.getBytes(ascii));
    }
    
    private void print() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(ccso.getInputStream()));
        ArrayList<Byte[]> page = new ArrayList();
        boolean go = true;
        int i = 0;
        while (go) {
            //add this line to the array list
            try {
                page.add(i, toObject(in.readLine().getBytes(ascii)));
            //look for the end of a gopher page
            } catch (Exception e) {
                break;
            }
            i++; 
        }
        int q = 0;
        while (q<i) {
            String H = new String(toPrimitive(page.get(q)), ascii);
            System.out.println(H);
            q++;
        }
    }
    
    public void start(JTextPane pane) throws IOException {
        this.pane = pane;
        write("status");
        print();
    }
    
    private void query() {
        write();
    }
    
}

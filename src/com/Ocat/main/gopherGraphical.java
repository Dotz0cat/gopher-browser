package com.Ocat.main;

import com.Ocat.graphics.gui;
import com.Ocat.graphics.jClickThing;
import static com.Ocat.main.gopher.charset;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class gopherGraphical extends gopher implements Cloneable {
    
    public gopherGraphical(String url) {
        super(url);
    }
    
    public gopherGraphical(String url, String Selector) {
        super(url, Selector);
    }
    
    protected void printOut(BufferedReader in, JTextPane pane) {
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
            //curently just prints string
            //System.out.println(H);
            //how to make print jlabel
            JLabel F = new jClickThing(getType(H), trim(H), getSelect(H), geturl(H));
            //should output a jlabel with a bit more
            //F.setOpaque(true);
            F.addMouseListener(new MouseAdapter() {
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    jClickThing L = (jClickThing) e.getSource();
                    switch (L.getType()) {
                        case ('1'):
                            gopherGraphical n;
                            String line = L.getUrl();
                            String url;
                            if (line.startsWith("gopher://")) {
                                url = line;
                            }
                            else {
                                url = "gopher://" + line;
                            }
                            n = new gopherGraphical(url, L.getSelector());
                            pane.setText("");
                            try {
                                n.getPage(pane);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            try {
                                back(pane);
                            } catch (CloneNotSupportedException ex) {
                                ex.printStackTrace();
                            }
                        break;
                        case ('0'):
                            line = L.getUrl();
                            if (line.startsWith("gopher://")) {
                                url = line;
                            }
                            else {
                                url = "gopher://" + line;
                            }
                            n = new gopherGraphical(url, L.getSelector());
                            pane.setText("");
                            try {
                                n.getText();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            try {
                                back(pane);
                            } catch (CloneNotSupportedException ex) {
                                ex.printStackTrace();
                            }
                        break;
                        case ('I'):
                            // gopher code n stuff
                            line = L.getUrl();
                            if (line.startsWith("gopher://")) {
                                url = line;
                            }
                            else {
                                url = "gopher://" + line;
                            }
                            n = new gopherGraphical(url, L.getSelector());
                            pane.setText("");
                            try {
                                n.getImage();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            try {
                                back(pane);
                            } catch (CloneNotSupportedException ex) {
                                ex.printStackTrace();
                            }
                        break;
                        case('8'):
                            pane.setText("");
                            System.err.println("this is telnet. it will not be implemented for a long time");
                            try {
                                back(pane);
                            } catch (CloneNotSupportedException ex) {
                                ex.printStackTrace();
                            }
                        break;
                        case ('i'):
                        break;
                        default:
                            pane.setText("");
                            System.err.println("sorry handling for this is not implemented yet");
                            try {
                                back(pane);
                            } catch (CloneNotSupportedException ex) {
                                ex.printStackTrace();
                            }
                        break;
                    }
                }
            });
            pane.insertComponent(F);
            System.out.print("\r\n");
            //System.out.println(F);
            //System.out.println("I am in the gopherGraphical class");
            
            q++;
        }
    }
    
    private void back(JTextPane pane) throws CloneNotSupportedException {
        jClickThing j = new jClickThing("Back", (gopherGraphical)this.clone());
        j.addMouseListener(new MouseAdapter() {
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    jClickThing L = (jClickThing) e.getSource();
                    gopherGraphical n;
                    n = L.getGopher();
                    pane.setText("");
                    try {
                        n.getPage(pane);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                       
                }
            });
        
        pane.insertComponent(j);
        System.out.print("\r\n");
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private static char getType(String H) {
        return H.charAt(0);
    }
    
    protected static String getSelect(String H) {
        String string = H;
        int tab = 0;
        int tab2 = 0;
        if (!string.equals(".")) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab = i;
                    break;
                }
            }
            for (int q = tab+1; q < string.length(); q++) {
                if (string.charAt(q) == (char) 9) {
                    tab2 = q;
                    break;
                }
            }
        }
        else {
            return "";
        }
        return string.substring(tab+1, tab2);
    }
    
    protected static String geturl(String H) {
        String string = H;
        int tab = 0;
        int tab2 = 0;
        int tab3 = 0;
        if (!string.equals(".")) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab = i;
                    break;
                }
            }
            for (int q = tab+1; q < string.length(); q++) {
                if (string.charAt(q) == (char) 9) {
                    tab2 = q;
                    break;
                }
            }
            for (int c = tab2+1; c < string.length(); c++) {
                if (string.charAt(c) == (char) 9) {
                    tab3 = c;
                    break;
                }
            }
            
        }
        else {
            return "";
        }
        return string.substring(tab2+1, tab3)+":"+getPort(string);
    }
    
    protected static String getPort(String H) {
        String string = H;
        int tab = 0;
        int tab2 = 0;
        int tab3 = 0;
        int CRLF = 0;
        if (!string.equals(".")) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab = i;
                    break;
                }
            }
            for (int q = tab+1; q < string.length(); q++) {
                if (string.charAt(q) == (char) 9) {
                    tab2 = q;
                    break;
                }
            }
            for (int c = tab2+1; c < string.length(); c++) {
                if (string.charAt(c) == (char) 9) {
                    tab3 = c;
                    break;
                }
            }
            OUTER:
            for (int w = tab3+1; w < string.length(); w++) {
                switch (string.charAt(w)) {
                    case (char) 13:
                        //CR
                        CRLF = w;
                        break OUTER;
                    case (char) 9:
                        //tab
                        CRLF = w;
                        break OUTER;
                    case (char) 43:
                        //plus
                        CRLF = w - 1;
                        break OUTER;
                    default:
                        break;
                }
            }
        }
        else {
            return "";
        }
        if (CRLF==0) {
            return string.substring(tab3+1);
        }
        else {
            return string.substring(tab3+1, CRLF);
        }
    }
    
    public void getPage(JTextPane pane) throws Exception {
        //make a clone and put in the arraylist in gui
        com.Ocat.graphics.gui.history.add((gopherGraphical) this.clone());
        if (gui.historyNum == gui.history.size()) {
            
        }
        com.Ocat.graphics.gui.updateNum();
        
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
        
        printOut(in, pane);
        in.close();
        
    }
    
    public void getText() throws Exception {
        URL newURL;
        newURL = new URL(getUrl());
        URLConnection g = newURL.openConnection();
        //output before input
        getRequest(g, getSelector());
        
        //input
        BufferedReader in = new BufferedReader(new InputStreamReader(g.getInputStream(), charset()));
        
        printText(in);
        in.close();
    }

    private void printText(BufferedReader in) {
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
            System.out.println(H);
            
            System.out.print("\r\n");
            q++;
        }
    }
    
    public void getImage() throws Exception {
        //temp files yay!!
        
        
        Path img = Files.createTempFile("gopher", ".png");
        
        //download image
        URL newURL;
        newURL = new URL(getUrl());
        URLConnection g = newURL.openConnection();
        //output before input
        getRequest(g, getSelector());
        
        
        //input
        BufferedReader in = new BufferedReader(new InputStreamReader(g.getInputStream(), charset()));
        
        FileWriter F = new FileWriter(img.toFile());
        while (true) {
            try {
                F.write(in.read());
            } catch (IOException ex) {
                break;
            }
        }
        
        //desktop stuff
        Desktop dt = Desktop.getDesktop();
        dt.open(img.toFile());
    }
}

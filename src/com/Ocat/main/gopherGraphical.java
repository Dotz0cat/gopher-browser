package com.Ocat.main;

import com.Ocat.graphics.jClickThing;
import static com.Ocat.main.gopher.charset;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class gopherGraphical extends gopher {
    
    public gopherGraphical(String url) {
        super(url);
    }
    
    public gopherGraphical(String url, String Selector) {
        super(url, Selector);
    }
    
    protected static void printOut(BufferedReader in, JTextPane pane) {
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
                       
                }
            });
            pane.insertComponent(F);
            System.out.println("");
            //System.out.println(F);
            //System.out.println("I am in the gopherGraphical class");
            
            q++;
            
        }
    }
    
    private static char getType(String H) {
        return H.charAt(1);
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
            for (int i = tab; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab2 = i;
                    break;
                }
            }
        }
        else {
            return "";
        }
        return string.substring(tab, tab2);
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
            for (int i = tab; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab2 = i;
                    break;
                }
            }
            for (int i = tab2; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab3 = i;
                    break;
                }
            }
            
        }
        else {
            return "";
        }
        return string.substring(tab2, tab3)+":"+getPort(string);
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
            for (int i = tab; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab2 = i;
                    break;
                }
            }
            for (int i = tab2; i < string.length(); i++) {
                if (string.charAt(i) == (char) 9) {
                    tab3 = i;
                    break;
                }
            }
            OUTER:
            for (int i = tab3; i < string.length(); i++) {
                switch (string.charAt(i)) {
                    case (char) 13:
                        CRLF = i;
                        break OUTER;
                    case (char) 9:
                        CRLF = i;
                        break OUTER;
                    case (char) 43:
                        CRLF = i - 1;
                        break OUTER;
                    default:
                        break;
                }
            }
        }
        else {
            return "";
        }
        return string.substring(tab3, CRLF);
    }
    
    public void getPage(JTextPane pane) throws Exception {
        
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
}

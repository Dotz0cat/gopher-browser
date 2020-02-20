package com.Ocat.main;

import com.Ocat.graphics.jClickThing;
import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.JLabel;

public class gopherGraphical extends gopher {
    
    public gopherGraphical(String url) {
        super(url);
    }
    
    public gopherGraphical(String url, String Selector) {
        super(url, Selector);
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
            //curently just prints string
            System.out.println(H);
            //how to make print jlabel
            JLabel F = new jClickThing(getType(H), trim(H), getSelect(H), geturl(H));
            //should output a jlabel with a bit more
            System.out.println(F);
            
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
                    tab = i;
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
            for (int i = tab3; i < string.length(); i++) {
                if (string.charAt(i) == (char) 13) {
                    CRLF = i;
                    break;
                }
                else if (string.charAt(i) == (char) 43) {
                    CRLF = i;
                    break;
                }
            }
        }
        else {
            return "";
        }
        return string.substring(tab3, CRLF);
    }
}

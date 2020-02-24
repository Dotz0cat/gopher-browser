package com.Ocat.graphics;

import com.Ocat.main.gopherGraphical;
import javax.swing.JLabel;

public class jClickThing extends JLabel {
    private char type;
    private String selector;
    private String url;
    private gopherGraphical n;
    
    public jClickThing(char type, String text, String selctor, String url) {
        super(text);
        this.type = type;
        this.selector = selctor;
        this.url = url;
    }
    
    public jClickThing(String text, gopherGraphical gopher) {
        super(text);
        this.n = gopher;
    }
    
    public char getType() {
        return type;
    }
    
    public String getSelector() {
        return selector;
    }
    
    public String getUrl() {
        return url;
    }
    
    public gopherGraphical getGopher() {
        return n;
    }
}

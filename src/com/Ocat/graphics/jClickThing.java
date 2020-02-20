package com.Ocat.graphics;

import javax.swing.JLabel;

public class jClickThing extends JLabel {
    private char type;
    private String selector;
    private String url;
    
    public jClickThing(char type, String text, String selctor, String url) {
        super(text);
        this.type = type;
        this.selector = selctor;
        this.url = url;
    }
}

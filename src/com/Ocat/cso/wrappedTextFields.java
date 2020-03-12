package com.Ocat.cso;

import javax.swing.JTextField;

public class wrappedTextFields extends JTextField {

    private String field;
    
    public wrappedTextFields(String text, String field) {
        super(text);
        this.field = field;
    }
    
    public wrappedTextFields(String field) {
        this.field = field;
    }
    
    public String getField() {
        return this.field;
    }
}

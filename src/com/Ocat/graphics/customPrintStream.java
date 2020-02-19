package com.Ocat.graphics;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

public class customPrintStream extends OutputStream {
    private JTextArea Jtext;
    
    public customPrintStream(JTextArea textArea) {
        this.Jtext = textArea;
    }
    
    
    @Override
    public void write(int i) throws IOException {
        Jtext.append(String.valueOf((char)i));
        Jtext.setCaretPosition(Jtext.getDocument().getLength());
    }
    
}

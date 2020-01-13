package com.cxd.html.editorkit;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/7/22 20:28
 **/
public class Html2TextHTMLEditorKit extends HTMLEditorKit.ParserCallback {
    StringBuilder s;

    public Html2TextHTMLEditorKit() {
    }

    public void parse(Reader in) throws IOException {
        s = new StringBuilder();
        ParserDelegator delegator = new ParserDelegator();
        // the third parameter is TRUE to ignore charset directive
        delegator.parse(in, this, Boolean.TRUE);
    }

    @Override
    public void handleText(char[] text, int pos) {
        char[] textFilterWhitespace = new char[text.length];
        int count = 0;
        for (char c : text) {
            if (!Character.isWhitespace(c)) {
                textFilterWhitespace[count++] = c;
            }
        }

        s.append(textFilterWhitespace);
    }

    public String getText() {
        return s.toString();
    }

    public static void main(String[] args) throws Exception {
        try {
            // the HTML to convert
            FileReader in = new FileReader("/Users/childe/Desktop/case-pro.html");


            long start = System.currentTimeMillis();

            Html2TextHTMLEditorKit parser = new Html2TextHTMLEditorKit();
            parser.parse(in);
            in.close();

            System.out.println(System.currentTimeMillis() - start);
            System.out.println(parser.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
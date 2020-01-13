package com.cxd.html.htmlparser;

import org.apache.commons.lang.StringEscapeUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.TextExtractingVisitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/7/22 22:42
 **/
public class HtmlparserTest {


    public String extractText(String inputHtml) throws Exception {

        Parser parser = Parser.createParser(new String(inputHtml.getBytes(),
                StandardCharsets.UTF_8.name()), StandardCharsets.UTF_8.name());

//        NodeList nodeList = parser.extractAllNodesThatMatch(node -> true);
//
//        for (int i = 0; i < nodeList.size(); i++) {
//            Node node = nodeList.elementAt(i);
//            if (node instanceof TextNode) {
//
//                System.out.println(node.toPlainTextString());
//            }
//        }
//        System.out.println("-->>>>>>>>>>>>>>>>>>>>>>>>>>>>>---------");
        // 遍历所有的节点
        TextExtractingVisitor visitor = new TextExtractingVisitor();
        parser.visitAllNodesWith(visitor);
        return visitor.getExtractedText();
    }

    public static void main(String[] args) throws Exception {
        String testText = null;

        FileReader in = new FileReader("/Users/childe/Desktop/case-pro.html");
        BufferedReader bufferedReader = new BufferedReader(in);

        testText = bufferedReader.readLine();

        //for (int i = 0; i<20;i++) {
            long start = System.currentTimeMillis();
            HtmlparserTest t = new HtmlparserTest();

        //}
        System.out.println(t.extractText(testText));

    }
}

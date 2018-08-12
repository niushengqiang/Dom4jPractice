package com.yurang.test;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

/**
 * 书写一个xml文件
 */
public class writeXml {

    public static void main(String args[]) {
        //writeAxml("D:\\临时文件夹/text.xml");
        modifyXMLFile("D:\\临时文件夹/text.xml","D:\\临时文件夹/textmodified.xml");
       // modifyXMLFile2("D:\\临时文件夹/text.xml","D:\\临时文件夹/textmodified.xml");
    }

    //输出一个xml文件
    public static void writeAxml(String path){
        String fileName=path;
        Document document = DocumentHelper.createDocument();// 建立document对象，用来操作xml文件

        Element booksElement = document.addElement("books");// 建立根节点

        booksElement.addComment("This is a test for dom4j ");// 加入一行注释

        Element bookElement = booksElement.addElement("book");// 添加一个book节点

        bookElement.addAttribute("show", "yes");// 添加属性内容

        Element titleElement = bookElement.addElement("title");// 添加文本节点

        titleElement.setText("ajax in action");// 添加文本内容

        try {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(fileName)));
            writer.write(document);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 该方法存在bug就是document转化xml
     * @param oldPath
     * @param newPath
     */
    public static void modifyXMLFile(String oldPath,String newPath) {
        String oldStr = oldPath;
        String newStr =newPath;
        Document document = null;
        //修改节点的属性
        try {
            SAXReader saxReader = new SAXReader(); // 用来读取xml文档
            document = saxReader.read(new File(oldStr));
            List list = document.selectNodes("/books/book/@show");// 用xpath查找节点book的属性
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                if (attribute.getValue().equals("yes"))
                    attribute.setValue("no");
            }
            //修改节点的内容
            List list2 = document.selectNodes("/books/book/title");// 用xpath查找节点book的内容
            Iterator iter2 = list.iterator();
            while (iter2.hasNext()) {
                Object next = iter2.next();
                //Element element = (Element)
                //      element.setText("xxx");// 设置相应的内容
                System.out.println(next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(newStr)));
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void modifyXMLFile2(String oldPath,String newPath) {
        String oldStr = oldPath;
        String newStr =newPath;
        Document document = null;
        //修改节点的属性
        try {
            SAXReader saxReader = new SAXReader(); // 用来读取xml文档
            document = saxReader.read(new File(oldStr));
            XPath xpath = DocumentHelper.createXPath("/books/book/@show");
            List list = xpath.selectNodes(document);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                if (attribute.getValue().equals("yes"))
                    attribute.setValue("no");
            }
            //修改节点的内容
            XPath xpath2 = DocumentHelper.createXPath("/books/book/title");
            List list1 = xpath2.selectNodes(document);
            Iterator iter2 = list1.iterator();
            while (iter2.hasNext()) {
                Element element =(Element)iter2.next();
                element.setText("xxx");// 设置相应的内容
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(newStr)));
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

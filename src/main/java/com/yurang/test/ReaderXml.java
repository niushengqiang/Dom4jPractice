package com.yurang.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URL;
import java.util.Iterator;

public class ReaderXml {
    
    public static void main(String[] args){
        //加载xml 文件的文档
        Document document = load("D:\\临时文件夹\\xxx.xml");
        System.out.println(document);
        //获取xml的root节点
        Element rootElement = document.getRootElement();
        //对节点进行遍历
        //iteratory(rootElement);
        //将某个节点转化为 xml文件
        String xml = rootElement.asXML();
        System.out.println(xml);
        System.out.println(document.asXML());
        

    }

    /**
     *  进行xml节点遍历 获取每一个元素并转化为 Element类型。
     *  这里的element当中elemnt 只是为了获取每个元素的节点
     *  element当中的getText();只是获取当前节点的文本 eg: <a>里面的东西</a> 获取的结果为 "里面的东西"
     */
    public  static  void  iteratory(Element element){
        System.out.println("rootElement获取的节点信息为"+element.getText());
        Iterator iterator = element.elementIterator();
        while(iterator.hasNext()){
            Element next =(Element) iterator.next();
            String text = next.getText();
            System.out.println("获取当前的文本为"+text);;
        }
    }

    //加载一个xml的信息
    public static Document load(String filepath) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(new File(filepath)); // 读取XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    //根据url 加载一个xml的信息
    public static Document load(URL url) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(url); // 读取XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }
}

package com.example.document;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @program: spring_test1
 * @description: xml文件
 * @author: XX
 * @create: 2022-11-21 10:07
 **/
public class getXmlTest {



    public static void main(String[] args) {
        Document document = getXmlTest.getXmlFile();


    }
    /**
     * @Author XX
     * @Description //TODO 写入xml文件
     * @Date 10:18 2022/11/21
 * @return org.dom4j.Document
     **/
    public static Document getXmlFile() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("DATAFILE");
        Element headElement = root.addElement("DATAHEAD");

        Element filesElement = headElement.addElement("FIELDS");
        Element fiedsElementTotal = headElement.addElement("EXT_PARAMETERS");
        return document;
    }
}




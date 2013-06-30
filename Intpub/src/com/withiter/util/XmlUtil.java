/*
 * Created on Apr 19, 2012
 *
 * XmlUtil.java
 *
 * Copyright (C) 2012 by Citicorp Software & Technology Services (Shanghai) Limited.
 * All rights reserved. Citicorp Software & Technology Services (Shanghai) Limited 
 * claims copyright in this computer program as an unpublished work. Claim of copyright 
 * does not imply waiver of other rights.
 *
 * NOTICE OF PROPRIETARY RIGHTS
 *
 * This program is a confidential trade secret and the property of 
 * Citicorp Software & Technology Services (Shanghai) Limited. Use, examination, 
 * reproduction, disassembly, decompiling, transfer and/or disclosure to others of 
 * all or any part of this software program are strictly prohibited except by express 
 * written agreement with Citicorp Software & Technology Services (Shanghai) Limited.
 */
/*
 * ---------------------------------------------------------------------------------
 * Modification History
 * Date               Author                     Version     Description
 * Apr 19, 2012       gl65293                    1.0         New
 * ---------------------------------------------------------------------------------
 */
/**
 * 
 */
package com.withiter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 * @author gl65293
 *
 */
public class XmlUtil {

    private static final Log log = LogFactory.getLog(XmlUtil.class);
    
    /**
     * @param xmlPath the path of XML file
     * @return the content of the XML file
     * @throws IOException
     */
    public static String getContent(String xmlPath) {
        if(log.isDebugEnabled()){
            log.debug("the xml path is : " + xmlPath);
        }
        File file = new File(xmlPath);
        String line;
        StringBuffer readLine = new StringBuffer();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader in = new BufferedReader(read);
            while ((line = in.readLine()) != null) {
                readLine.append(line+"\r\n");
            }
            read.close();
        } catch (UnsupportedEncodingException e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage());
            }
        } catch (IOException e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage());
            }
        }
        if(log.isDebugEnabled()){
            log.debug("the xml content is : " + readLine.toString());
        }
        return readLine.toString();
    }
    
    /**
     * @param xmlPath the path of XML file
     * @param nodeName the node name of this node 
     * @param attrName the attribute name of this node
     * @param attrValue the attribute value of this node
     * @return boolean the node is exist or not
     */
    public static boolean nodeIsExist(String xmlPath, String nodeName, String attrName, String attrValue){
        if(log.isDebugEnabled()){
            log.debug("the xml path is : " + xmlPath);
            log.debug("the node name is : " + nodeName);
            log.debug("the attribute name is : " + nodeName);
            log.debug("the attribute value is : " + nodeName);
        }
        boolean flag = false;
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(xmlPath);
            Element root = document.getRootElement();
            XPath xpath = XPath.newInstance("//" + nodeName + "[@" + attrName + "='" + attrValue + "']");
            Element e = (Element) xpath.selectSingleNode(root);
            if(e != null){
                flag = true;
            }
        } catch (JDOMException e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage());
            }
        } catch (IOException e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage());
            }
        }
        return flag;
    }
    
    /**
     * 
     * @param xmlPath the path of XML file
     * @param nodeName the node name of this node 
     * @param attrName the attribute name of this node
     * @param attrValue the attribute value of this node
     * @param nodeText the text of this node
     * @return boolean the node is exist or not
     */
    @SuppressWarnings("unchecked")
    public static boolean nodeIsExist(String xmlPath, String nodeName, String attrName, String attrValue, String nodeText){
        boolean flag = false;
        
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(xmlPath);
            Element root = document.getRootElement();
            XPath xpath = XPath.newInstance("//" + nodeName + "[@" + attrName + "='" + attrValue + "']");
            List<Element> elist = (List<Element>) xpath.selectNodes(root);
            
            for(Element e : elist){
                if(e.getText().equals(nodeText)){
                    flag = true;
                    break;
                }
            }
        } catch (JDOMException e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage());
            }
        } catch (IOException e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage());
            }
        }
        return flag;
    }  
    
    public static void main(String[] args) {
        System.out.println(XmlUtil.getContent("C:/CPSolution/XmlDB/UserDB.xml"));
    }
}

/*
 * Created on Feb 28, 2012
 *
 * InitServlet.java
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
 * Feb 28, 2012       gl65293                    1.0         New
 * ---------------------------------------------------------------------------------
 */
/**
 * 
 */
package com.withiter.util;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gl65293
 */
public class InitServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(InitServlet.class);
    
    public void init() {

        if(log.isDebugEnabled()){
            log.debug("init servlet is starting...");
        }
        if(log.isDebugEnabled()){
            log.debug("init servlet end");
        }
    }
}

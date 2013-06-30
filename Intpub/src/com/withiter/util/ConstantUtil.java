/*
 * Created on 2012-5-9
 *
 * ConstantUtil.java
 *
 * Copyright (C) 2012 by Withiter Software & Technology Services (Shanghai) Limited.
 * All rights reserved. Withiter Software & Technology Services (Shanghai) Limited 
 * claims copyright in this computer program as an unpublished work. Claim of copyright 
 * does not imply waiver of other rights.
 *
 * NOTICE OF PROPRIETARY RIGHTS
 *
 * This program is a confidential trade secret and the property of 
 * Withiter Software & Technology Services (Shanghai) Limited. Use, examination, 
 * reproduction, disassembly, decompiling, transfer and/or disclosure to others of 
 * all or any part of this software program are strictly prohibited except by express 
 * written agreement with Withiter Software & Technology Services (Shanghai) Limited.
 */
/*
 * ---------------------------------------------------------------------------------
 * Modification History
 * Date               Author                     Version     Description
 * 2012-5-9       CrossLee                    1.0         New
 * ---------------------------------------------------------------------------------
 */
/**
 * 
 */
package com.withiter.util;

/**
 * @author CrossLee
 * 
 */
public interface ConstantUtil {

	interface Status {
		int STATE_DISPLAY = 1;
		int STATE_HIDDEN = 2;
		int STATE_DELETED = 3;
	}
	
	
	interface Info_Status {
		int STATE_SAVE = 1;
		int STATE_PUBLISH = 2;
		int STATE_DELETED = 3;
	}
	/**
	 * 
	 * the sequence name of table
	 * 
	 * @author jazze
	 * 
	 */
	interface SequenceName {
		String MOUDLE_SEQUENCE = "intpub_moudle";
		String MENU_SEQUENCE = "intpub_menu";
		String INFO_SEQUENCE = "intpub_info";
		String MOUDLE_PART_SEQUENCE = "intpub_moudle_part";
		String PART_SEQUENCE = "intpub_part";
		String ROLE_SEQUENCE = "intpub_role";
		String USER_SEQUENCE = "intpub_user";
		String MENU_ROLE_SEQUENCE = "intpub_menu_role";
	}

}

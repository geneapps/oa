
package com.giro.common.util;

import java.util.UUID;


public class UUIDUtil {

	/**
	 * @return
	 */
	public static String getUUID() {

		return UUID.randomUUID().toString().replace("-", "").toUpperCase();

	}

}

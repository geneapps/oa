package com.giro.common.util;

import java.util.Collection;

import org.springframework.util.ObjectUtils;


public class BooleanUtil extends ObjectUtils {

	/**
	 * @param obj
	 * @param objects
	 * @return
	 */
	public static boolean isEmpty(Object obj, Object... objects) {

		if (null == obj)
			return true;
		if (obj instanceof Collection<?> && 0 == ((Collection<?>) obj).size())
			return true;
		if (obj instanceof String && ((String) obj).trim().equals(""))
			return true;
		if (objects.length > 0)
			for (Object object : objects) {
				if (null == object)
					return true;
				if (object instanceof Collection<?> && 0 == ((Collection<?>) object).size())
					return true;
				if (object instanceof String && ((String) object).trim().equals(""))
					return true;
			}
		return false;
	}
}

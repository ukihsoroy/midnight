package org.ko.analysis.core.help;

import org.springframework.lang.Nullable;

/**
 * 对象工具类
 */
public class ObjectHelper extends org.springframework.util.ObjectUtils {

	/**
	 * 判断元素不为空
	 * @param obj object
	 * @return boolean
	 */
	public static boolean isNotEmpty(@Nullable Object obj) {
		return !ObjectHelper.isEmpty(obj);
	}

}

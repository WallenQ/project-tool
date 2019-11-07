package com.wallen.tool.util;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 正则表达式校验
 *
 * @author Wallen
 * 2019/11/07 14:50
 */
public class RegularExpression {

	/**
	 * 身份证号码正则校验规则-简单版
	 * <p>
	 * 地址码判定不够精确。例：我国并不存在16，26开头的地区，却可通过验证
	 * 日期判定不够精确。例:19490231也可通过验证，而2月并不存在31日
	 * 校验码是由17位本体码计算得出，并未校验此码
	 */
	public static final String IDCARD_REGULAR_SIMPLE = "^[1-9]\\d{5}(19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

	/**
	 * 校验身份证号码
	 *
	 * @param idcardNumber 身份证号码
	 * @return
	 */
	public static boolean checkIdcard(String idcardNumber) {
		if (StringUtils.isNotBlank(idcardNumber)) {
			return false;
		}
		return Pattern.matches(IDCARD_REGULAR_SIMPLE, idcardNumber);
	}
}

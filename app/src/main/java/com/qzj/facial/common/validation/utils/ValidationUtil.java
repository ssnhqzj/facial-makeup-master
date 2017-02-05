/**
 *  Copyright 2014 ken.cai (http://www.shangpuyun.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 *
 */
package com.qzj.facial.common.validation.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * 校验工具类
 */
@SuppressWarnings("unused")
public class ValidationUtil {
	/**
	 * 规范内容长度
	 * @param s 输入的字符
	 */
	protected int getWordCountRegex(String s) {
		s = s.replaceAll("[^\\x00-\\xff]", "**");
		return s.length();
	}
	
	/**
	 * 校验整数
	 */
	public boolean isNumeric(String text) {
		return TextUtils.isDigitsOnly(text);
	}

	public boolean isAlphaNumeric(String text) {
		return matches(text, "[a-zA-Z0-9 \\./-]*");
	}

	public boolean isDomain(String text) {
		return matches(text, Build.VERSION.SDK_INT>=8?Patterns.DOMAIN_NAME:Pattern.compile(".*"));
	}

	public boolean isEmail(String text) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return matches(text, regex);
	}

	public boolean isIpAddress(String text) {
		return matches(text, Build.VERSION.SDK_INT>=8?Patterns.DOMAIN_NAME:Pattern.compile(
	            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
	            + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
	            + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
	            + "|[1-9][0-9]|[0-9]))"));
	}

	public boolean isWebUrl(String text) {
		//TODO: Fix the pattern for api level < 8
		return matches(text, Build.VERSION.SDK_INT>=8?Patterns.WEB_URL:Pattern.compile(".*"));
	}

	/**
	 * 判断是否为手机号码
	 */
	public boolean isPhoneNumber(String phoneNumber){
		return matches(phoneNumber,"^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	}

	/**
	 * 电话号码验证
	 */
	public boolean isTelNumber(String number){
		return matches(number,"(^(\\d{3,4}-)?\\d{7,8})$");
	}

	/**
	 * 电话号码或
	 */
	public boolean isTelOrPhone(String number){
		boolean isTel = isTelNumber(number);
		boolean isPhone = isPhoneNumber(number);
		return isTel || isPhone;
	}

	/**
	 * 是否为邮政编码
	 */
	public boolean isPostCode(String code){
		return matches(code,"^[1-9][0-9]{5}$");
	}
	
	protected boolean find(String text,String regex) {
		return Pattern.compile(regex).matcher(text).find();
	}
	
	protected boolean matches(String text,String regex) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(text).matches();
	}
	
	protected boolean matches(String text,Pattern pattern) {
		return pattern.matcher(text).matches();
	}

	protected boolean isTextEmpty(EditText editText) {
		return editText==null || TextUtils.isEmpty(editText.getText());
	}
}

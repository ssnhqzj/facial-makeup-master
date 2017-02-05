package com.qzj.facial.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * xml值保存和获取
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unused")
public class PreferenceUtils {
	private static final String TAG = "PreferenceUtils";

	private static PreferenceUtils pUtils = null;
	private static SharedPreferences sp = null;

	private PreferenceUtils(Context context, String spName) {
		sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
	}

	public static void init(Context context, String spName){
		if (pUtils == null){
			synchronized (PreferenceUtils.class){
				if (pUtils == null) {
					pUtils = new PreferenceUtils(context,spName);
				}
			}
		}
	}

	public static PreferenceUtils getInstance(){
		if (pUtils == null){
			Log.e(TAG, "");
			return null;
		}

		return pUtils;
	}

	public PreferenceUtils remove(String key) {
		sp.edit().remove(key).apply();
		return this;
	}

	/**
	 *   ---清空该xml所有值
	 */
	public PreferenceUtils clear() {
		Editor editor = sp.edit();
		editor.clear();
		editor.apply();

		return this;
	}

	public Editor getEditor() {
		return sp.edit();
	}

	/**
	 */
	public boolean getBoolean(String key, boolean defaultValue) {
		return sp.getBoolean(key, defaultValue);
	}

	/**
	 */
	public float getFloat(String key, float defaultValue) {
		return sp.getFloat(key, defaultValue);
	}

	/**
	 */
	public int getInt(String key,int defaultValue) {
		return sp.getInt(key, defaultValue);
	}

	/**
	 *   ---判断是否已保存该值
	 */
	public long getLong(String key, long defaultValue) {
		return sp.getLong(key, defaultValue);
	}
	/**
	 */
	public String getString(String key, String defaultValue) {
		return sp.getString(key, defaultValue);
	}

	public boolean hasKey(String key) {
		return sp.contains(key);
	}
	
	/**
	 */
	public PreferenceUtils setBoolean(String key, boolean value) {
		sp.edit().putBoolean(key, value).apply();
		return this;
	}

	public PreferenceUtils setFloat(String key, float value) {
		sp.edit().putFloat(key, value).apply();
		return this;
	}
	
	public PreferenceUtils setInt(String key, int value) {
		sp.edit().putInt(key, value).apply();
		return this;
	}

	public PreferenceUtils setString(String key, String value) {
		sp.edit().putString(key, value).apply();
		return this;
	}

	public PreferenceUtils setLong(String key, long value) {
		sp.edit().putLong(key, value).apply();
		return this;
	}
	
}

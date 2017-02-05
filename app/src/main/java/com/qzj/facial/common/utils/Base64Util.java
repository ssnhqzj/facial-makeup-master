package com.qzj.facial.common.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/4/22.
 *
 */
public class Base64Util {

    /**
     * 加密
     */
    public static String encode(String str){
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 解密
     */
    public static String decode(String str){
        try {
            return new String(Base64.decode(str.getBytes("UTF-8"), Base64.DEFAULT),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}

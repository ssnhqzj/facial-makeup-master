package com.qzj.facial.common.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 获取屏幕信息类
 * @author qzj
 *
 */
@SuppressWarnings("unused")
public class DisplayUtils {

	/**
	 * 获取屏幕分辨率
     */
	public static Point getScreenResolution(Context context) {
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point screenResolution = new Point();
		if(Build.VERSION.SDK_INT >= 13) {
			display.getSize(screenResolution);
		} else {
			DisplayMetrics dm = new DisplayMetrics();
			display.getMetrics(dm);
			screenResolution.x = dm.widthPixels;
			screenResolution.y = dm.heightPixels;
		}

		return screenResolution;
	}

	/**
	 * 获取屏幕方向
     */
	public static int getScreenOrientation(Context context) {
		Point resolution = getScreenResolution(context);
		byte orientation;
		if(resolution.x == resolution.y) {
			orientation = 3;
		} else if(resolution.x < resolution.y) {
			orientation = 1;
		} else {
			orientation = 2;
		}

		return orientation;
	}
}

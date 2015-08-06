 
package cn.yesway.mapshifting.common.utils;

import java.math.BigDecimal;

/**
 *  坐标转换工具类
 *
 * @version : Ver 1.0
 * @since	: 2015-8-4 下午2:20:43 
 */
public class GPSTransfUtils {

	private final static double PI = 3.14159265358979323;	// 数据圆周率

	private final static double RADIS = 6378245.0;			// 地球半径？
	
	private final static double EE = 0.00669342162296594323;// 偏心率？
	
	/**
	 * 转换纬度
	 *
	 * @param x 经度
	 * @param y 纬度
	 * @return
	 */
	public static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	/**
	 * 转换经度
	 *
	 * @param x 经度
	 * @param y 纬度
	 * @return
	 */
	public static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;
		return ret;
	}

	/**
	 * 转换
	 *
	 * @param wgLat  纬度
	 * @param wgLon  经度
	 * @return
	 */
	public static Double[] transform(double wgLat, double wgLon) {
		 
		double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
		double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
		double radLat = wgLat / 180.0 * PI;
		double magic = Math.sin(radLat);
		magic = 1 - EE * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((RADIS * (1 - EE)) / (magic * sqrtMagic) * PI);
		dLon = (dLon * 180.0) / (RADIS / sqrtMagic * Math.cos(radLat) * PI);
		double mgLat = wgLat + dLat;
		double mgLon = wgLon + dLon;

		return new Double[] { numberFmt(6,mgLat), numberFmt(6,mgLon) };
	}

	public static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}
	
	/**
	 * double取精度
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-8-4 下午6:47:10
	 * @param scale   几位
	 * @param value   
	 * @return
	 */
	public static double numberFmt(int scale,double value){
		return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	
	public static void main(String[] args) {
		// 116.331393,39.949293
		double x = 116.331393;// 测试经度
		double y = 39.949293;// 测试纬度
		Double[] newXy = transform(x, y);
		System.out.println("转换后的经度：" + newXy[0]);
		System.out.println("转换后的纬度：" + newXy[1]);

		// 两纬度间距离是111KM，在赤道上，两经度的距离也是111KM
		double du = 40 / 180.0 * PI;
		System.out.println("北京经度一度的距离大约是：" + 111000 * Math.cos(du));
		System.out.println("北京经度一秒的距离大约是：" + 111000 * Math.cos(du) / 3600);
	}
	
}

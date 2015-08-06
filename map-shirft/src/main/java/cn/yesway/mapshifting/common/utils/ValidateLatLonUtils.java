 
package cn.yesway.mapshifting.common.utils;


/**
 *  验证经度纬度是否合法
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:358911056@qq.com">hubo</a>
 * @date	: 2015-8-4 下午5:01:09 
 */
public class ValidateLatLonUtils {

	// 经度
	private final static String REG_lON = "(-|\\+)?(180\\.0{1,}|(\\d{1,2}|1([0-7]\\d))\\.\\d+)";

	//纬度
	private final static String REG_LAT =  "(-|\\+)?(90\\.0{1,}|(\\d|[1-8]\\d)\\.\\d+)";
	
	/**
	 * 校验纬度
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-8-4 下午5:03:54
	 * @param lat
	 * @return
	 */
	public static boolean validateLat(String lat){
		return lat.matches(REG_LAT);
	}
	
	/**
	 * 校验经度
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-8-4 下午5:03:54
	 * @param lat
	 * @return
	 */
	public static boolean validateLon(String lon){
		return lon.matches(REG_lON);
	}
	
	public static void main(String[] args) {
		boolean flag = ValidateLatLonUtils.validateLon("1116.3569");
		
		System.out.println(flag);
	}
}

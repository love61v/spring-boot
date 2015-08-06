 
package cn.yesway.mapshifting.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

/**
 *  读取属性配置文件属性值
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:358911056@qq.com">hubo</a>
 * @date	: 2015-8-4 下午3:03:37 
 */
public class PropertiesHolderUtils {

	/** 配置文件*/
	private final static String CONFIG = "system.config.properties";
	
	/** map缓存*/
	private final static Map<String, String> cacheProperty = new ConcurrentHashMap<String, String>();
	
	/**
	 * 从缓存中取属性值
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-8-4 下午7:05:48
	 * @param key
	 * @return
	 */
	public static String get(String key){

		String value = cacheProperty.get(key);  //缓存中不存在
		
		if(StringUtils.isBlank(value)){
			value = getPropertity(key);
			cacheProperty.put(key, value); //添加到缓存
		}
		return value;
	}
	
	/*
	 * 清空缓存
	 */
	public static void clear(){
		cacheProperty.clear();
	}
	
	/**
	 * 根据key获取value
	 * @param key
	 * @return  值
	 */
	public static String getPropertity(String key) {
		String value = "";
		if (null == key) {
			return value;
		}

		InputStream inputStream = null;
		try {
			inputStream  = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CONFIG);
			Properties prop = new Properties();
			prop.load(inputStream);
			 
			value = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != inputStream){
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return value;
	}
	
}

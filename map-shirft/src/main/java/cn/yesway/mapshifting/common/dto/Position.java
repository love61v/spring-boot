package cn.yesway.mapshifting.common.dto;

import java.io.Serializable;

/**
 * 位置
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:358911056@qq.com">hubo</a>
 * @date : 2015-8-4 下午2:34:17
 */
public class Position implements Serializable{ 

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private double lat; 	// 纬度

	private double lon; 	// 经度
	
	private boolean success; //状态

	public Position() {
		super();
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

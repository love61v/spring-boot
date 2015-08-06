package cn.yesway.mapshifting.common.dto;

import java.io.Serializable;

/**
 * 健康诊断状态信息
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:358911056@qq.com">hubo</a>
 * @date : 2015-8-4 下午2:42:11
 */
public class MonitorStatus implements Serializable{

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private String healthy; // 健康状态

	private String message; // 详情

	public MonitorStatus() {
	}

	public String getHealthy() {
		return healthy;
	}

	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

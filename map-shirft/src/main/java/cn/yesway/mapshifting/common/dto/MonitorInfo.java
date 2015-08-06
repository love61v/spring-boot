package cn.yesway.mapshifting.common.dto;

import java.io.Serializable;

/**
 * 监测试信息
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:358911056@qq.com">hubo</a>
 * @date : 2015-8-4 下午2:37:27
 */
public class MonitorInfo implements Serializable{

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private String name; // 名称

	private String description; // 备注

	private String version; // 版本信息

	public MonitorInfo() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}

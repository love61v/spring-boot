 
package cn.yesway.mapshifting.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yesway.mapshifting.common.dto.MonitorInfo;
import cn.yesway.mapshifting.common.dto.MonitorStatus;
import cn.yesway.mapshifting.common.dto.Position;
import cn.yesway.mapshifting.common.enums.HealthyEnum;
import cn.yesway.mapshifting.common.utils.GPSTransfUtils;
import cn.yesway.mapshifting.common.utils.PropertiesHolderUtils;
import cn.yesway.mapshifting.common.utils.ValidateJsonUtils;
import cn.yesway.mapshifting.common.utils.ValidateLatLonUtils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

/**
 *  地图坐标转换服务
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:358911056@qq.com">hubo</a>
 * @since	: 2015-8-4 下午2:17:54 
 */
@Controller
public class MapShiftingController {
	private final static Logger log = Logger.getLogger(MapShiftingController.class);
	
	
	/**
	 * 信息服务
	 * 
	 * @author : <a href="mailto:358911056@qq.com">hubo</a>
	 * @since : 2015-8-4 下午3:11:25
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public MonitorInfo info() {
		MonitorInfo monitorInfo = new MonitorInfo();
		monitorInfo.setName(PropertiesHolderUtils.get("name"));
		monitorInfo.setDescription(PropertiesHolderUtils.get("description"));
		monitorInfo.setVersion(PropertiesHolderUtils.get("version"));

		return monitorInfo;
	}

	/**
	 * 状态服务
	 * 
	 * @author : <a href="mailto:358911056@qq.com">hubo</a>
	 * @since : 2015-8-4 下午3:11:25
	 * @return
	 */
	@RequestMapping("/status")
	@ResponseBody
	public Map<String, Object> status() {
		Map<String, Object> map = new HashMap<String, Object>();
		MonitorStatus status = new MonitorStatus();
		status.setHealthy(HealthyEnum.OK.toString());

		map.put("healthy", status.getHealthy());

		return map;
	}
	
	
	/**
	 * 清空缓存
	 * 
	 * @author : <a href="mailto:358911056@qq.com">hubo</a>
	 * @since : 2015-8-4 下午3:11:25
	 * @return
	 */
	@RequestMapping("/clear")
	@ResponseBody
	public String clear() {
		PropertiesHolderUtils.clear(); // 清空缓存
		return "已清空缓存";
	}
 
}

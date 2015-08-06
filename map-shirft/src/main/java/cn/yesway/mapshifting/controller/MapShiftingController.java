 
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
	
	private final static String REQUEST_KEY = "wgs84Positions"; //请求json数据的key
	
	private final static String RESPONSE_KEY = "gcj02Positions";//响应json数据的key


	/**
	 * 坐标转换
	 * 参数格式： {"wgs84Positions": {"pos1": {"lat":  40,"lon":  23.456},"pos2": {"lat":  47.88,"lon":  123.456}}}
	 * @author : <a href="mailto:358911056@qq.com">hubo</a>
	 * @since : 2015-8-4 下午2:56:02
	 * @return
	 */
	@RequestMapping(value="/shifting",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tranform(HttpServletResponse res,HttpServletRequest req, @RequestBody String params) {
		log.debug("请求参数: " + ValidateJsonUtils.replaceChar(params));
		
		if (StringUtils.isBlank(params)) {// 空串
			log.debug("ERROR * [ request param is NULL ]");
			
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} else if (!ValidateJsonUtils.validate(params)) {// 不合法json格式
			log.debug("ERROR * [ request param is not well-formed]");
			
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} else if (!ValidateJsonUtils.replaceChar(params).contains(REQUEST_KEY + "\"")) {// 不包含约定的请求key
			log.debug("ERROR * [ request param do not contains key which named " + REQUEST_KEY + " ]");
			
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}


		return transformPositions(params);
	}

	/**
	 * 解析请求的JSON数据且转换坐标
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-8-4 下午6:57:53
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> transformPositions(String params) {
		Map<String, Object> resultMap = new LinkedTreeMap<String, Object>();
		Map<String, Object> map = new TreeMap<String, Object>();
		resultMap.put(RESPONSE_KEY, map); // 响应结果
		
		Gson gson = new Gson();
		LinkedTreeMap<String, Object> maps = gson.fromJson(params,LinkedTreeMap.class);
		if (!maps.isEmpty()) {
			resultMap = new LinkedTreeMap<String, Object>(); // 响应结果map
			LinkedTreeMap<String, Object> result = new LinkedTreeMap<String, Object>();
			resultMap.put(RESPONSE_KEY, result);

			// wgs84位置经度纬度
			LinkedTreeMap<String, Object> obj = (LinkedTreeMap<String, Object>) maps.get(REQUEST_KEY);
			for (Entry<String, Object> entry : obj.entrySet()) {
				LinkedTreeMap<String, Object> temp = (LinkedTreeMap<String, Object>) entry.getValue(); // 经纬度值
				String key = entry.getKey();
				String lat = String.valueOf(temp.get("lat")).trim();
				String lon = String.valueOf(temp.get("lon")).trim();
				boolean isLat = ValidateLatLonUtils.validateLat(lat); // 纬度数字是否合法
				boolean isLon = ValidateLatLonUtils.validateLon(lon); // 经度数字是否合法
 
				if ((!isLat && isLon) || (isLat && !isLon)) {// 经纬度数字任一不合法则状态返回不成功
					log.debug("ERROR * [ request param latitude or longitude is not right ]");
					
					Map<String, Object> tempMap = new HashMap<String, Object>();
					tempMap.put("success", false);
					result.put(key, tempMap); 
				}else {//转换
					Double[] latlons = GPSTransfUtils.transform(Double.valueOf(lat), Double.valueOf(lon));
					Position position = new Position();
 					position.setLat(latlons[0]);
					position.setLon(latlons[1]);
					position.setSuccess(true);
					
					result.put(key, position);
				}
			}
		}
		
		return resultMap;
	}

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

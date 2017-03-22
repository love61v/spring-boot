 
package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.yesway.mapshifting.Application;
import cn.yesway.mapshifting.dao.AreaMapper;
import cn.yesway.mapshifting.model.Area;
import cn.yesway.mapshifting.model.AreaExample;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MybatisTest {

	@Autowired
	private AreaMapper areaMapper;
	
	 
	
	@Test
	public void findPage(){
		PageHelper.startPage(2, 1);
		List<Area> list = areaMapper.selectByExample(new AreaExample());
		print(list);
		
		PageInfo<Area> pager = new PageInfo<Area>(list);
		print(pager);
		
	}
	
	private void print(Object obj){
		System.out.println("结果: " + JSON.toJSONString(obj));
	}
}

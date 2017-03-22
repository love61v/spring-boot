/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package cn.yesway.mapshifting.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yesway.mapshifting.model.Country;
import cn.yesway.mapshifting.service.CountryService;

@Controller
@RequestMapping("/countries")
public class CountryController {

	@Autowired    
	private CountryService countryService;
	
	/**
	 * 查询列表
	 *
	 * @author 	: <a href="mailto:hubo@daojia.com">hubo</a>  
	 * @date	:2017-3-20 上午12:26:44
	 */
	@RequestMapping(value="/showcountry",method=RequestMethod.GET)
	@ApiOperation(value="获取城市", notes="根据country列表")
	public String showCountry(Model model){
		List<Country> countryList = countryService.findList(null);
		model.addAttribute("countryList", countryList);
		
		return "index";
	}
	
	/**
	 * 根据id取城市
	 * @author 	: <a href="mailto:hubo@daojia.com">hubo</a>  
	 * @date	:2017-3-20 上午12:30:45
	 * @param id 城市id
	 */
	@RequestMapping(value="/country/{id}",method=RequestMethod.GET)
	@ApiOperation(value="根据id取城市",notes="id不能为空呐")
	@ApiImplicitParam(name = "id", value = "根据id取城市", required = true, dataType = "int")
	@ResponseBody
	public Country getCountry(@PathVariable int id){
		return countryService.getById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiImplicitParam(name = "country", value = "城市详细实体Country", required = true, dataType = "Country")
	public ModelAndView save(Country country) {
		ModelAndView result = new ModelAndView("view");
		String msg = country.getId() == null ? "新增成功!" : "更新成功!";

		countryService.save(country);

		result.addObject("country", country);
		result.addObject("msg", msg);
		return result;
	}
}

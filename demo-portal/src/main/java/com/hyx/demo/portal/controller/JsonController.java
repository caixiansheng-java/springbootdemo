package com.hyx.demo.portal.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hyx.demo.portal.constants.PortalConstants;
import com.hyx.demo.portal.enums.PortalErrorCode;
import com.hyx.demo.portal.utils.JsonFormatUtils;
import com.hyx.demo.sdk.vo.BaseResultResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
 * @ClassName:JsonController <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月14日 下午3:00:42 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@RestController
@RequestMapping("/json")
@Api(value = "数据转换入口", tags = "数据转换入口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JsonController {
	private Logger logger = LoggerFactory.getLogger(JsonController.class);
	
	@RequestMapping("formatJson")
	@ApiOperation(value = "JSON数据格式化", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseResultResp<Object> formatJson(@RequestParam("reqData") String reqData,
			@RequestParam(name = "dataTyp",defaultValue = PortalConstants.DATA_TPYE_JSON) String dataTyp){
		BaseResultResp<Object> resp = new BaseResultResp<Object>(PortalErrorCode.SUCC);
		try {
			if(StringUtils.isEmpty(reqData)) {
				resp = new BaseResultResp<Object>(PortalErrorCode.DATA_IS_EMPTY);
				return resp;
			}
			String data = JsonFormatUtils.formatJson(reqData.trim());
			resp.setData(data);
			return resp;
		} catch (Exception e) {
			resp = new BaseResultResp<Object>(PortalErrorCode.FAIL);
			return resp;
		}
	}
	
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("name", "张三");
		json.put("sex", "男");
		json.put("age", 21);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 90);
		map.put("数学", 80);
		map.put("英语", 75);
		json.put("grade", map);
		System.out.println(json.toJSONString());
	}
	
}
 
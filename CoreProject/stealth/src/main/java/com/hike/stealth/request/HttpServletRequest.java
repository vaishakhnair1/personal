package com.hike.stealth.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {

	private static final Map<String, Object> parameters = new HashMap<String, Object>();
	
	public void setParam(String paramName, Object paramValue){
		parameters.put(paramName, paramValue);
	}
	
	public Object getValueByParamName(String paramName){
		return parameters.get(paramName);
	}
	
	public Map<String, Object> getParamMap(){
		return Collections.unmodifiableMap(parameters);
	}
}

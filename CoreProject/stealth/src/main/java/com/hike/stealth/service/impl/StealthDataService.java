package com.hike.stealth.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hike.stealth.pojo.ThreadInfo;
import com.hike.stealth.service.IStealthDataService;

public class StealthDataService implements IStealthDataService{

	private static final StealthDataService stealthDataService = new StealthDataService();
	private static final Map<Integer, ThreadInfo> connectionDataMap = new ConcurrentHashMap<Integer, ThreadInfo>();
    
	private StealthDataService(){}
	
	public static IStealthDataService getInstance(){
		return stealthDataService;
	}
	
	@Override
	public void saveData(Integer connectionId, ThreadInfo threadInfo){
		connectionDataMap.put(connectionId, threadInfo);
	}
	
	@Override
	public void removeData(Integer connectionId){
		connectionDataMap.remove(connectionId);
	}
	
	@Override
	public ThreadInfo getDataByConnectionId(Integer connectionId){
		return connectionDataMap.get(connectionId);
	}
	
	@Override
	public Map<Integer, ThreadInfo> getAllConnectionData(){
		return connectionDataMap;
	}
}

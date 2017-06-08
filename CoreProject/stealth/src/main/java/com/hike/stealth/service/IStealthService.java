package com.hike.stealth.service;

public interface IStealthService {

public String sleepAndGet(Integer timeout, Integer connectionId);
	
	public String getServerStatus();
	
	public String kill(Integer connectionId);
}

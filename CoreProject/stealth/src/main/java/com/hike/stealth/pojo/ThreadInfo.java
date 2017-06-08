package com.hike.stealth.pojo;

public class ThreadInfo {

	//Current Time entered is in seconds
	private Long currentTime;
	private Thread thread;
	private Integer sleepingTime;
	
	public ThreadInfo(Long currentTime, Thread thread, Integer sleepingTime){
		this.currentTime = currentTime;
		this.thread = thread;
		this.sleepingTime = sleepingTime;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public Thread getThread() {
		return thread;
	}

	public Integer getSleepingTime() {
		return sleepingTime;
	}

	public void setSleepingTime(Integer sleepingTime) {
		this.sleepingTime = sleepingTime;
	}
	
}


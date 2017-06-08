package com.hike.stealth.core;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpConnectionPool {

	private static final Integer NO_OF_THREADS = 100;
	private static final ExecutorService executor = Executors.newFixedThreadPool(NO_OF_THREADS);
	
	private HttpConnectionPool(){}
	
	public static void submitConnection(Socket socket){
		executor.submit(new HttpRequestProcessor(socket));
	}
}

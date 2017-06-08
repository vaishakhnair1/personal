package com.hike.stealth.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpSocket {

	private static final Integer PORT_NUMBER = 8080;
	
	public void openSocketForAcceptingRequest(){
	
		ServerSocket myService = null;
	    try {
	       myService = new ServerSocket(PORT_NUMBER);
	       while(true){
		    	try {
					final Socket client = myService.accept();
					HttpConnectionPool.submitConnection(client);
				} catch (IOException e) {
					System.out.println(e);
				}
		   }
	    }catch (Exception e) {
	       System.out.println(e);
	    }
	}
	
	public static void main(String[] args){
		new HttpSocket().openSocketForAcceptingRequest();
	}
}

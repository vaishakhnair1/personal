package com.hike.stealth.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

import com.hike.stealth.controller.HttpRequestController;
import com.hike.stealth.request.HttpServletRequest;

public class HttpRequestProcessor implements Runnable{

	private Socket socket;
	
	public HttpRequestProcessor(Socket socket){
		this.socket = socket;
	}
	
	public void run() {
		InputStreamReader isr = null;;
		BufferedReader reader = null;
		try {
			isr = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(isr); 
			String result = parseAndReturnResult(reader);
			Date today = new Date(); 
			String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today + "\r\n\r\n"; 
			httpResponse = httpResponse + "Content-Type: application/json\r\n\r\n";
			httpResponse = httpResponse + "Content-Length: " + result.length() + "\r\n\r\n";
			httpResponse = httpResponse + result;
			socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
			
		} catch (IOException e) {
			System.out.println(e);
		}  finally{
			try {
				reader.close();
				socket.close();
			} catch (IOException e) {
				System.out.println(e);
			}
			
		}
	}
	
	private String parseAndReturnResult(BufferedReader reader){
		String line;
		boolean firstLine = true;
		boolean postRequest = false;
		Integer contentLength = 0;
		try {
			line = reader.readLine();
			while (!line.isEmpty()) { 
				System.out.println(line); 
				if(firstLine){
					if(line.contains("GET")){
						firstLine = false;
						if(line.contains("server-status")){
							return HttpRequestController.getInstance().getServerStatus();
						}else if(line.contains("sleep")){
							String[] uriSplit = line.split(" ");
							if(!uriSplit[1].contains("?")){
								return "{\"stat\":\"No Params In Request\"}";
							}
							String queryParam = uriSplit[1].replace("/sleep?", "");
							HttpServletRequest request = new HttpServletRequest();
							String[] queryParams = queryParam.split("&");
							for(String param : queryParams){
								String[] params = param.split("=");
								if(params.length!=2){
									return "{\"stat\":\"Invalid Param format in Request\"}";
								}
								request.setParam(params[0], params[1]);
							}
							return HttpRequestController.getInstance().sleepAndGetStatus(request);
						}else{
							return "{\"stat\":\"Invalid URL\"}";
						}
					}else if(line.contains("POST")){
						firstLine = false;
						if(line.contains("looper")){
							postRequest = true;
						}else{
							return "{\"stat\":\"Invalid URL\"}";
						}
					}else{
						return "{\"stat\":\"Invalid Http Method\"}";
					}	
				}
				if(postRequest && line.contains("Content-Length")){
					contentLength = Integer.parseInt(line.substring("Content-Length: ".length()));
				}
				line = reader.readLine();	
			}
			if(postRequest){
				StringBuilder builder = new StringBuilder();
				int c = 0;
				for (int i = 0; i < contentLength; i++) {
		            c = reader.read();
		            builder.append((char)c);
				}
				System.out.println("BodyData is " + builder.toString());
				HttpServletRequest request = new HttpServletRequest();
				String[] queryParams = builder.toString().split("&");
				for(String param : queryParams){
					String[] params = param.split("=");
					if(params.length!=2){
						return "{\"stat\":\"Invalid Param format in Request\"}";
					}
					request.setParam(params[0], params[1]);
				}
				return HttpRequestController.getInstance().killConnection(request);
		
			}
		} catch (Exception e) {
			System.out.println(e);
			return "{\"stat\":\"Invalid Content In Request\"}";
		}

		return "EMPTY";
	}

}

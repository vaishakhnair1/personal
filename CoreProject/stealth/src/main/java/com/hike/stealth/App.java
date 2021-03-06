package com.hike.stealth;

import com.hike.stealth.service.impl.StealthService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        final Integer connectionId = 1;
        
        final StealthService stealthService = new StealthService();
        Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(stealthService.kill(connectionId));
			}
		   
		});
        th.start();
        
        Thread th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i =0;
				while(i<5){
					System.out.println(stealthService.getServerStatus());
					i++;
					try {
						Thread.sleep(2*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		   
		});
        th1.start();
        
        System.out.println(stealthService.sleepAndGet(10, connectionId));
    }
}

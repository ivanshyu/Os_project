package project;

import java.util.Random;

public class newFarmer extends Thread{
	 public void run(Bridge bridge) { // override Thread's run()
		 	int i=0;
		 	int j=0;
		 	//Bridge bridge = new Bridge(3); 
		 	//System.out.println("罪之先");
	        while(true){ // infinite loop to print message
	        	int whichcar=0;
	        	Random ran = new Random();
	        	whichcar=(ran.nextInt(1000))%2;
	        	if(whichcar==0) {
	        	Farmer k= new Farmer("N_Farmer"+(i+1),"North",bridge);
	        	k.start();
	        	try { 
	                // 等待隨機時間
	                Thread.sleep((int) (Math.exp(i+j))); 
	            } 
	            catch(InterruptedException e) { 
	                e.printStackTrace(); 
	            } 
	        	//System.out.println("make car!!!!!!!!!!!");
	        	i=i+1;
	        	}
	        	if(whichcar==0) {
		        	Farmer k= new Farmer("S_Farmer"+(i+1),"South",bridge);
		        	k.start();
		        	try { 
		                // 等待隨機時間
		                Thread.sleep((int) (Math.exp(i+j))); 
		            } 
		            catch(InterruptedException e) { 
		                e.printStackTrace(); 
		            } 
		        	//System.out.println("make car!!!!!!!!!!!");
		        	j=j+1;
		        	}
	        }
	    }
}

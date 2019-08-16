package project;

public class newFarmer2 {
	 public void run(Bridge bridge) { // override Thread's run()
		 	int i=0;
		 	//Bridge bridge = new Bridge(3); 
		 	//System.out.println("罪之先");
	        while(true){ // infinite loop to print message
	        	Farmer k= new Farmer("S_Farmer"+(i+1),"South",bridge);
	        	k.start();
	        	try { 
	                // 等待隨機時間
	                Thread.sleep((int) (Math.random() * 1)); 
	            } 
	            catch(InterruptedException e) { 
	                e.printStackTrace(); 
	            }
	        	//System.out.println("make car!!!!!!!!!!!!!!!!!!!!!!!!");
	        	i=i+1;
	        }
	    }
}

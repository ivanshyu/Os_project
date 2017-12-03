package project;
import java.util.concurrent.Semaphore;

public class Bridge {
    private int crossed;    //Count the number of crossings
    private  int /*Semaphore*/ bridgeSem , direction; //semaphore to only allow 1 crossing at a time
    private int northWaiting, southWaiting;
    private int exited;
    private String CarWay;
    private int MaxHold;
    private int RemainHold;
    //Constructor
    public Bridge(int max) {
        crossed=0;
        bridgeSem = max /*new Semaphore(max)*/;   //one bridge resource, mutual exclusivity
        northWaiting = southWaiting = 0;
        exited = 0;
        MaxHold=max;
        System.out.println(getUse()+" "+getMax());
    }

    //Getters
    public int getCrossed() {
        return crossed;
    }
    //Methods
    public int getUse(){
        return MaxHold-bridgeSem;
    }
    public int getMax(){
        return MaxHold;
    }
    public synchronized void upCross(Farmer f){    //statistic the crossed sum
        if (f.getLocation()=="North") northWaiting--;
        else southWaiting--; 
        crossed++;     
        System.out.println(f.getID()+": Across the Bridge."); 
        System.out.println(" Crossed sum = "+getCrossed());    
        bridgeSem+=1;
        if(southWaiting==0||northWaiting==0)
            notifyAll();
        else notify();

    }
    public synchronized void upThis(Farmer f) {      //put into queue
        if(f.getRepeat()==0){
            if (f.getID().startsWith("N")) northWaiting++;
            else southWaiting++;
            f.counted();
            System.out.println(f.getID()+" is queued to cross"+f.getLocation()+":"+getWaiting(f));  
        }
        f.addRepeat();
    }
    public synchronized int MutualWaiting(Farmer f){
        if(f.getLocation()=="North") return southWaiting;
        else return northWaiting;
    }
    public /*synchronized*/ int getWaiting(Farmer f){
        if(f.getLocation()=="North") return northWaiting;
        else return southWaiting;
    } 
    public synchronized int getNorth() {
        return northWaiting;
    }
    public synchronized int getSouth() {
        return southWaiting;
    }
    public /*synchronized*/ String getCarWay(){
        return CarWay;
    }
    public synchronized String setCarWay(Farmer f){ 
        String hold=CarWay;
        CarWay=f.getLocation();
        return (f.getID()+" changes the CarWay from "+hold+" to "+CarWay);

    }
    public boolean cross(Farmer f) { 
        upThis(f);
        synchronized(this){
            //  try{
            int mutualWaiting;
            if(f.getLocation()=="North") mutualWaiting=southWaiting;
            else mutualWaiting=northWaiting;
            while(f.getLocation()!=CarWay && mutualWaiting>=1 && MaxHold-bridgeSem>=1/*!=getMax()*/ ){
                //Thread.yield();
                try{
                    System.out.println(f.getID()+" stop because there are cars in different way");
                    wait();
                    return false;
                }catch(InterruptedException e){}
            }
            //  }catch(InterruptedException e){}
            while(f.getLocation()==CarWay && MaxHold-bridgeSem>=1/*!=getMax()*/ && mutualWaiting>=1){
                //Thread.yield();
                try{
                    System.out.println(f.getID()+" stop although cars in same way, but there are mutual wating cars");
                    wait();
                    return false;
                }catch(InterruptedException e){}
            }

            while(true){
                if(bridgeSem>=1){
                    bridgeSem-=1;
                    System.out.println(f.getID()+" go on bridge and "+"Now bridgeSem = "+bridgeSem);
                    break;
                }
                else{
                    //Thread.yield();
                    try{
                        System.out.println(f.getID()+"stop becuase bridgeSem=0");
                        wait();
                        return false;
                    }catch(InterruptedException e){}

                }                }
            System.out.println(f.getID()+" N:"+getNorth()+" S:"+ getSouth());
            System.out.println(setCarWay(f)+" "+(/*getMax()-*getUse()*/MaxHold-bridgeSem)+" cars on bridge(including this car.");
        }

        System.out.println(f.getID()+": Crossing bridge Step 5. N:"+getNorth()+" S:"+ getSouth()+" Way: "+getCarWay()+" "+f.getLocation());
        System.out.println(f.getID()+": Crossing bridge Step 10.");
        System.out.println(f.getID()+": Crossing bridge Step 15.");

        try {
            Thread.sleep((long)(Math.random()*1000));
        } catch (InterruptedException e) {} //No interrupts implemented, so thread shouldn't be interrupted?
        upCross(f);  //increment NEON counter, synchronized to avoid print conflicts
        return true;
    }


}

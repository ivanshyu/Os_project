package project;
//import java.util.concurrent.Semaphore;
import java.util.*;
public class Bridge {
    private int crossed;    //Count the number of crossings
    private  int  bridgeSem , direction; //semaphore to only allow 1 crossing at a time
    private int northWaiting, southWaiting;
    private int exited;
    private String CarWay;
    private int MaxHold;
    private int RemainHold;
    private Queue<String> q;
    //Constructor
    public Bridge(int max) {
        crossed=0;
        bridgeSem = max ;   //one bridge resource, mutual exclusivity
        northWaiting = southWaiting = 0;
        exited = 0;
        MaxHold=max;
        System.out.println(getUse()+" "+getMax());
        q= new LinkedList<String>();
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
        System.out.println(q.poll()+" crossed the Bridge."); 
        System.out.println(" Crossed sum = "+getCrossed());    
        //q.poll();
        bridgeSem+=1;
        //if(getSouth()==0||getNorth()==0)
            notifyAll();
        //else notify();

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
    public synchronized int getWaiting(Farmer f){
        if(f.getLocation()=="North") return northWaiting;
        else return southWaiting;
    } 
    public synchronized int getNorth() {
        return northWaiting;
    }
    public synchronized int getSouth() {
        return southWaiting;
    }
    public synchronized String getCarWay(){
        return CarWay;
    }
    public synchronized String setCarWay(Farmer f){ 
        String hold=CarWay;
        CarWay=f.getLocation();
        return (f.getID()+" changes the CarWay from "+hold+" to "+CarWay);

    }
    public boolean cross(Farmer f) { 
        upThis(f);
        //go into mutex
        synchronized(this){
            int mutualWaiting;
            if(f.getLocation()=="North") mutualWaiting=getSouth();
            else mutualWaiting=getNorth();
            //if waiting cars direction and cars on the bridge are different, waiting cars can't go. 
            while(f.getLocation()!=CarWay && mutualWaiting>=1 && MaxHold-bridgeSem>=1){
                try{
                    System.out.println(f.getID()+" stop because there are cars in different way");
                    wait();
                    return false;
                }catch(InterruptedException e){}
            }
            //if waiting cars direction is equal to cars on the bridge,but it has mutual-wating cars,so it can't go.
            while(f.getLocation()==CarWay && MaxHold-bridgeSem>=1 && mutualWaiting>=1){
                try{
                    System.out.println(f.getID()+" stop although cars in same way, but there are mutual wating cars");
                    wait();
                    return false;
                }catch(InterruptedException e){}
            }
            while(true){
                //go  on the bridge 
                if(bridgeSem>=1){
                    bridgeSem-=1;
                    q.offer(f.getID());
                    System.out.println(f.getID()+" go on bridge and "+"Now the bridge still can afford "+bridgeSem);
                    break;
                }
                //if bridge is full of cars, it can't go.
                else{
                    try{
                        System.out.println(f.getID()+" stop becuase bridgeSem=0");
                        wait();
                        return false;
                    }catch(InterruptedException e){}

                }                
            }
            //System.out.println(f.getID()+" N:"+getNorth()+" S:"+ getSouth());
            System.out.println(setCarWay(f)+" "+(getUse())+" cars on bridge(including this car.");
        }
        //help to debug and know the status.
        System.out.println(f.getID()+" is crossing bridge.  Waiting cars  N:"+getNorth()+" S:"+ getSouth());
        //System.out.println(f.getID()+": Crossing bridge Step 10.");
        //System.out.println(f.getID()+": Crossing bridge Step 15.");

        try {
            Thread.sleep(f.getCrossTime());
        } catch (InterruptedException e) {}         
        synchronized(this){
            while(f.getID()!=q.peek()){
                try{
                    wait();
                }catch(InterruptedException e){}
            }
        }
        upCross(f);
        return true;
    }


}

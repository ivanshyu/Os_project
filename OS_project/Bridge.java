package project;
import java.util.concurrent.Semaphore;

public class Bridge {
    private int crossed;    //Count the number of crossings
    private static Semaphore bridgeSem; //semaphore to only allow 1 crossing at a time
    private int northWaiting, southWaiting;
    private int exited;
    private String CarWay;
    private int MaxHold;
    private int RemainHold;
    //Constructor
    public Bridge(int max) {
        crossed=0;
        bridgeSem = new Semaphore(max);   //one bridge resource, mutual exclusivity
        northWaiting = southWaiting = 0;
        exited = 0;
        MaxHold=RemainHold=max;
    }

    //Getters
    public int getCrossed() {
        return crossed;
    }
    //Methods
    public synchronized void upCross(Farmer f){    //statistic the crossed sum
        if (f.getID().startsWith("N")) northWaiting--;
        else southWaiting--;
        crossed++;
        System.out.println("Crossed sum = "+getCrossed());
    }
    public synchronized void upThis(Farmer f) {      //put into queue
        if (f.getID().startsWith("N")) northWaiting++;
        else southWaiting++;
        f.counted();
        System.out.println(f.getID()+" is queued to cross");  //DEBUG 
    }
    public synchronized void upExited() {
        exited++;
   }
    public synchronized int getNorth() {
        return northWaiting;
    }
    public synchronized int getSouth() {
        return southWaiting;
    }
    public synchronized int getExited() {
        return exited;
    }
    public synchronized String getCarWay(){
        return CarWay;
    }
    public synchronized void setCarWay(String way){
        CarWay=way;
    }
    public synchronized void resetExited() {
        exited=0;
    }
    public synchronized void BridgeHold(int value){
        RemainHold+=value;
    }

    public void cross(Farmer f) { 
        //Semaphore acquire
        try {   
            if( RemainHold==MaxHold || f.getLocation()==getCarWay()){
                setCarWay(f.getLocation());
                BridgeHold(-1);
                bridgeSem.acquire();
            }
            System.out.println(f.getID()+": Crossing bridge Step 5.");
            System.out.println(f.getID()+": Crossing bridge Step 10.");
            System.out.println(f.getID()+": Crossing bridge Step 15.");
          
            //Sleep for 200 units ,improves readability (else output is too fast) 
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {} //No interrupts implemented, so thread shouldn't be interrupted?

            System.out.println(f.getID()+": Across the Bridge.");
            upCross(f);  //increment NEON counter, synchronized to avoid print conflicts
            //Sleep for 200 units ,improves readability (else output is too fast) 
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {} //No interrupts implemented, so thread shouldn't be interrupted?
        }
        catch (InterruptedException e) {} 
    }

    public void exit() {
        //Semaphore release
        upExited();
        BridgeHold(1);
        bridgeSem.release();

    }
}

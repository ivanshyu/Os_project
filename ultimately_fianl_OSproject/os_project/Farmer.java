package os_project;

public class Farmer extends Thread{
    private String location;    //current location
    private String id;          //name      
    private Bridge bridge;      //bridge being used
    private int repeat=0;
    private boolean finished=false;
    private boolean counted = false;
    private long crossTime;

    //constructor
    public Farmer(String id, String location, Bridge bridge) {
        this.id=id;
        this.location=location;
        this.bridge = bridge;
        crossTime=(long)(Math.random()*1000);
        System.out.println(id+": Waiting for bridge.");  //print initial waiting for bridge

    }
    public long getCrossTime(){
        return crossTime;
    }
    public int getRepeat(){
        return repeat;
    }
    public void addRepeat(){
        repeat+=1;
    }
    //getters
    public String getLocation() {
        return location;
    }
    public String getID() {
        return id;
    }
    public boolean isCounted() {
        return counted;
    }
    //setter
    public void setFinished(boolean finished) {
        this.finished=finished;
    }
    public void counted() {
        counted=true;
    }

    //Overrides the Thread toString() method. Called with Thread.getCurrent().toString()
    @Override   
    public String toString() {
        return id;
    }
    @Override   //initiatied when the Farmer Thread .start() method is called
        public void run() {
            //if ready to cross

            while (!finished) {
                while(bridge.cross(this)==false);
                setFinished(true);
            }
        }//end run  

}//end class

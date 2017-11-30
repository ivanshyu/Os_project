package project;

public class Farmer extends Thread{
    private String location;    //current location
  //  private String destination; //Opposite location, destination, set in the constructor
    private String id;          //name      
    private Bridge bridge;      //bridge being used
    private boolean finished=false;
    private boolean counted = false;

    //constructor
    public Farmer(String id, String location, Bridge bridge) {
        this.id=id;
        this.location=location;
     //   if (location=="North") destination="South"; //Island objects are not necessary for this particular implementation, as our options are merely North or South
    //    else destination="North";
        this.bridge = bridge;
        System.out.println(id+": Waiting for bridge.");  //print initial waiting for bridge

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
              /*  try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
*/
                    bridge.upThis(this);    //increments the appropriate north/south counter in a thread safe method, also marks this thread as counted=true
                    
                    bridge.cross(this);
                    bridge.exit();
                    setFinished(true);
            }
        }//end run  

}//end class

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collections;

import project.Bridge;
import project.Farmer;


public class Main {

    public static void main(String[] args) {
        
        System.out.println("Input North_cars, South_cars and Bridge_max");
        int N=0,S=0,max;
        
        Scanner in = new Scanner(System.in);
		N=in.nextInt();
		S=in.nextInt();
        max=in.nextInt();

        Bridge bridge = new Bridge(max);   //create our bridge
        Farmer[] f = new Farmer[N+S];   //array of Farmers
        //create North farmers
        for (int i=0; i<N; i++) {
            f[i] = new Farmer("N_Farmer"+(i+1),"North",bridge);
        }
        //create South farmers
        for (int i=N; i<S+N; i++) {
            f[i]= new Farmer("S_Farmer"+(i-N+1),"South",bridge);
        }
        
        //start all farmers
        for (int i=0;i<S+N;i++) {
            f[i].start();   //start Farmer Threads. Farmers can run start, as Farmer extends thread
        }

    }

}

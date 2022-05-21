package edu.umb.cs681.hw18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableThread implements Runnable{

public void run() {
	
    	// Setting up coordinates of Aircraft
	
    	Aircraft aircraftInstance = new Aircraft(new Position(35.08,-106.62,75));
        System.out.println("Aircraft's current coordinates are	\t\t:"+ aircraftInstance.getPosition());
        
        // Changing Latitude value
        
        aircraftInstance.setPosition(aircraftInstance.getPosition().updateLat(37.18));
        System.out.println("Aircraft's original Latitude changed to	\t:"+ aircraftInstance.getPosition());
        
        // Changing Longitude value
        
        aircraftInstance.setPosition(aircraftInstance.getPosition().updateLon(-101.06));
        System.out.println("Aircraft's original Longitude changed to	\t:"+ aircraftInstance.getPosition());
        
        // Changing Altitude value
        
        aircraftInstance.setPosition(aircraftInstance.getPosition().updateAlt(40));
        System.out.println("Aircraft's original Altitude changed to	\t:"+ aircraftInstance.getPosition());
        
        // Changing coordinates
        
        aircraftInstance.setPosition(new Position(37.77, -122.41, 145));
        System.out.println("Aircraft's new coordinates are set to	\t:"+ aircraftInstance.getPosition());
        
        // Calculating distance between two positions
        
        System.out.println("Aircraft's Distance is	\t:"+ aircraftInstance.getPosition().distanceTo(new Position(45.11, -104.04,50 )));
}
    public static void main(String[] args) {
    	
    	RunnableThread rt = new RunnableThread();
        ExecutorService executor = Executors.newFixedThreadPool(7);
        executor.execute(rt);
        executor.shutdown();
    }
}



package edu.umb.cs681.hw10;

import java.util.ArrayList;
import java.util.List;

public final class Position {
	private final double latitude, longitude, altitude;
	public Position(double latitude, double longitude, double altitude){ 
		this.latitude = latitude; 
		this.longitude = longitude; 
		this.altitude = altitude; 
	}
	
	public double getlatitude(){ 
		return latitude;
	} 
	
	public double getlongitude(){ 
		return longitude; 
	} 
	
	public double getaltitude(){ 
		return altitude; 
	} 
	
	public String toString() {
        return this.latitude + "," + this.longitude + "," + this.altitude;
    }

    public boolean equals(Position comparePos) {
        if (!this.toString().equals(comparePos.toString())) {
            return false;
        } else {
            return true;
        }
    }

    public List<Double> getCoordinate(){
    	List<Double> list = new ArrayList<Double>();
    	list.add(this.latitude);
    	list.add(this.longitude);
    	list.add(this.altitude);
    	return list;
    }
    
    public Position updateLat(double newLat) {
    	return new Position(newLat, this.longitude, this.altitude);
    }
    
    public Position  updateLon(double newLon) {
    	return new Position(this.latitude, newLon, this.altitude);
    }
    
    public Position updateAlt(double newAlt) {
    	return new Position(this.latitude, this.longitude, newAlt);
    }
    
    public Position distanceTo(Position changedPosition) {
    	return new Position((this.latitude-changedPosition.latitude),(this.longitude-changedPosition.longitude),(this.altitude-changedPosition.altitude));
    }
}

package utils;

import vehicles.Vehicle;

import java.util.Vector;

public class Memento {
    private final Vector<Vehicle> vehicleList;
    public Memento(Vector<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    public Vector<Vehicle> getState(){
        return this.vehicleList;
    }
}

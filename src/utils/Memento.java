package utils;

import vehicles.Ivehicle;
import vehicles.Vehicle;

import java.util.Vector;

public class Memento {
    private final Vector<Ivehicle> vehicleList;
    public Memento(Vector<Ivehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    public Vector<Ivehicle> getState(){
        return this.vehicleList;
    }
}

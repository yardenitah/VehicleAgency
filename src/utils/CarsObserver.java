package utils;

import vehicles.Vehicle;

import java.util.Vector;

public interface CarsObserver {
    public void update(Vehicle vehicle, Vector<Vehicle> VehicleList, int index);
}

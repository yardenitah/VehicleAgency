package utils;

import vehicless.Vehicle;

import java.util.List;
import java.util.Vector;

public interface CarsObserver {
    public void update(Vehicle vehicle, Vector<Vehicle> VehicleList, int index);
}

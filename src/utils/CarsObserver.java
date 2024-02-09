package utils;

import vehicles.Ivehicle;
import vehicles.Vehicle;

import java.util.Vector;

public interface CarsObserver {
    public void update(Ivehicle vehicle, Vector<Ivehicle> VehicleList, int index);
}

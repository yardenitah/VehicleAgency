package utils;

import Vehicless.Vehicles;

import java.util.List;

public interface CarsObserver {
    public void update(Vehicles vehicle, List<Vehicles> list, int index);
}

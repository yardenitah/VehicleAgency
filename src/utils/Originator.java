package utils;

import vehicles.Vehicle;
import java.util.Vector;

public class Originator {
    private  Vector<Vehicle> vehicleList;

    public void setState(Vector<Vehicle> state){
        this.vehicleList = state;
    }

    public Memento createNewMemento(){
        return new Memento(this.vehicleList);
    }
    public void setMemento(Memento memento){
        this.vehicleList = memento.getState();
    }
}

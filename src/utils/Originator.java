package utils;

import vehicles.Ivehicle;
import java.util.Vector;

public class Originator {
    private  Vector<Ivehicle> vehicleList;

    public void setState(Vector<Ivehicle> state){
        this.vehicleList = state;
    }

    public Memento createNewMemento(){
        return new Memento(this.vehicleList);
    }
    public void setMemento(Memento memento){
        this.vehicleList = memento.getState();
    }
}

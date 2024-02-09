package vehicles;

import utils.CarsObserver;
import utils.globalKilometerObserver;

import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle  implements Ivehicle {
    public Vehicle() {
        kilometers = 0;
        maxKilometers = 0;
        maxOfPassengers = 0;
        maxSpeed = 0;
        model = null;
    }
    public Vehicle(String model, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed) {
        this.kilometers = kilometers;
        this.maxSpeed = maxSpeed;
        this.maxOfPassengers = maxOfPassengers;
        this.maxKilometers = maxKilometers;
        this.model = model;
        this.vehiclesImage = new ImageIcon();
        this.carsObserverList = new ArrayList<>();
        this.imageSelected = false;
        this.onBuyingProcess = false;
        this.onTestDrive = false;
    }

    private List<CarsObserver> carsObserverList;
    private static final List<globalKilometerObserver> kmObservers = new ArrayList<>();
    private static int globalKilometer = 0;
    protected int kilometers;
    protected int maxKilometers;
    protected int maxOfPassengers;
    protected int maxSpeed;
    protected String model;
    protected boolean onTestDrive;
    private boolean imageSelected;
    protected ImageIcon vehiclesImage;
    protected boolean onBuyingProcess;
    


    static class Spoon{

    }
    public static void registerObserver(globalKilometerObserver observer) {
        kmObservers.add(observer);
    }

    private static  void notifyKmObservers() {  // Notify all registered observers
        for (globalKilometerObserver kmObserver : kmObservers) {
            kmObserver.update(globalKilometer);
        }
    }

    public static int getGlobalKilometer() {
        return globalKilometer;
    }

    public static void addToGlobalKilometer(int kilometer) {
        globalKilometer += kilometer;
        notifyKmObservers();
    }

    public static void setGlobalKilometer(int kilometer) {
        globalKilometer = kilometer;
        notifyKmObservers();
    }

    public Boolean getOnTestDrive() {
        return this.onTestDrive;
    }

    public void setOnTestDrive(boolean onTestDrive) {
        this.onTestDrive = onTestDrive;
    }

    @Override
    public Vehicle makeVehicle(){
        return this;
    }

    public List<CarsObserver> getCarsObserverList() {
        return this.carsObserverList;
    }

    public void setVehiclesImage(ImageIcon vehiclesImage) {
        this.vehiclesImage.setImage(vehiclesImage.getImage());
    }

    public ImageIcon getVehiclesImage() {
        return this.vehiclesImage;
    }

    public void setKm(int km) {
        this.kilometers = km;
    }

    public String getModel() {
        return this.model;
    }

    public int getMaxOfPassengers() {
        return this.maxOfPassengers;
    }

    public int getKm() {
        return this.kilometers;
    }

    public int getMaxKml() {
        return this.maxKilometers;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void addToKm(int km) {
        this.kilometers += km;
    }

    public void move(String model, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed) {
        this.model = model;
        this.kilometers += kilometers;
        this.maxKilometers = maxKilometers;
        this.maxOfPassengers = maxOfPassengers;
        this.maxSpeed = maxSpeed;
    }


    public abstract boolean equals(Object anObj);

    public abstract String toString();
}



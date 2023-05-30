package Vehicless;

import utils.CarsObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Vehicles {
    public Vehicles() {
        kilometers = 0;
        maxKilometers = 0;
        maxOfPassengers = 0;
        maxSpeed = 0;
        model = null;
    }

    public Vehicles(String model, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed) {
        this.kilometers = kilometers;
        this.maxSpeed = maxSpeed;
        this.maxOfPassengers = maxOfPassengers;
        this.maxKilometers = maxKilometers;
        this.model = model;
        this.vehiclesImage = new ImageIcon();
        this.carsObserverList = new ArrayList<>();
    }
    private List<CarsObserver> carsObserverList;
    protected int kilometers;
    protected int maxKilometers;
    protected int maxOfPassengers;
    protected int maxSpeed;
    protected String model;
    private boolean onTestDrive;
    public Boolean getOnTestDrive(){
        return this.onTestDrive;
    }
    public void setOnTestDrive(boolean onTestDrive){
        this.onTestDrive = onTestDrive;
    }
    protected ImageIcon vehiclesImage;
    public Vehicles radioButton;

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

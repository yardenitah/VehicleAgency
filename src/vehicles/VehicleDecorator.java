package vehicles;

public abstract class VehicleDecorator extends Vehicle implements Ivehicle {
    protected Vehicle decoratedVehicle;

    public VehicleDecorator(Vehicle vehicle){
        super(vehicle.model, vehicle.kilometers, vehicle.maxKilometers, vehicle.maxOfPassengers, vehicle.maxSpeed);
        this.decoratedVehicle = vehicle;
    }
    @Override
    public Vehicle makeVehicle() {
        return decoratedVehicle.makeVehicle();
    }

    public Vehicle getDecoratedVehicle() {
        return decoratedVehicle;
    }
}

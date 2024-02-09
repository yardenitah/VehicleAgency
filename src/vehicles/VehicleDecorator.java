package vehicles;

public abstract class VehicleDecorator implements Ivehicle {
    protected Ivehicle decoratedVehicle;

    public VehicleDecorator(Ivehicle vehicle){
        this.decoratedVehicle = vehicle;
    }
    @Override
    public Ivehicle makeVehicle() {
        return decoratedVehicle.makeVehicle();
    }

}

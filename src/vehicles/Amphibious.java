package vehicles;

public class Amphibious extends WaterVehicle implements iLandVehicle, iWaterVehicle,MotorVehicles {

    private final iLandVehicle landVehicle;
    public Amphibious(String model, String subModel, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, boolean withTheWind, String countryFlag, int numOfWheels) {
        super(model, kilometers, maxKilometers, maxOfPassengers, maxSpeed, withTheWind, countryFlag);
        landVehicle = new LandVehicle(model, subModel, kilometers, maxKilometers, maxOfPassengers, maxSpeed, false, true, numOfWheels);
    }


    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof Amphibious) {
           return super.equals(anObj) && this.landVehicle.equals(anObj);
        }
        return false;
    }

    @Override
    public String toString() {
       String str1 = "Amphibious: ";
        return str1+"\n"+super.toString()+"\n"+landVehicle.toString();
    }

    @Override
    public void AverageConsumption(int km, int amountoFgas) {
        if (km != 0)
        this.AverageConsumption = amountoFgas / km;
    }

    @Override
    public String roadType() {
       return landVehicle.roadType();
    }
}
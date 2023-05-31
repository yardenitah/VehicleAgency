package vehicless;

public class HybridPlane extends AirVehicle implements iLandVehicle, iWaterVehicle, MotorVehicles {
    private int AverageConsumption;
    private final iLandVehicle landVehicle;
    private final iWaterVehicle waterVehicle;

    public HybridPlane(String model, String subModel, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, boolean civilian, boolean military, float averageLifeSpan, int numOfWheels, boolean withTheWind, String countryFlag) {
        super(model, kilometers, maxKilometers, maxOfPassengers, maxSpeed, civilian, military, averageLifeSpan);
        landVehicle = new LandVehicle(model, subModel, kilometers, maxKilometers, maxOfPassengers, maxSpeed, false, true, numOfWheels);
        waterVehicle = new WaterVehicle(model, kilometers, maxKilometers, maxOfPassengers, maxSpeed, withTheWind, countryFlag);
    }


    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof HybridPlane) {
            return super.equals(anObj) && this.landVehicle.equals(anObj) && waterVehicle.equals(anObj);
        }
        return false;
    }

    @Override
    public String toString() {
        String str1 = "Hybrid Plane: ";
        return str1 + "\n" + super.toString() + "\n" + landVehicle.toString() + "\n" + waterVehicle.toString();
    }

    @Override
    public void AverageConsumption(int km, int amountoFgas) {
        if (km != 0)
            AverageConsumption = amountoFgas / km;
    }

    @Override
    public String roadType() {
        return landVehicle.roadType();
    }

    @Override
    public void setCountryFlag(String countryFlag) {
        waterVehicle.setCountryFlag(countryFlag);
    }
}

package vehicles;

public class CruiseShip extends WaterVehicle implements MotorVehicles, Commercial{
    public CruiseShip(String model, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, String countryFlag, int lifeTime) {
        super(model, kilometers, maxKilometers, maxOfPassengers, maxSpeed, true, countryFlag);
        this.license = "Unlimited";
        this.lifeTime = lifeTime;
    }
    private String license;
    private final int lifeTime;
    private int averageConsumption;

    @Override
    public void AverageConsumption(int km, int amountoFgas) {
        if (km != 0)
            this.averageConsumption = amountoFgas / km;
    }

    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof CruiseShip) {
                CruiseShip cp = (CruiseShip) anObj;
                return cp.license.equals(this.license) && super.equals(anObj);
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "\n life time: "+this.lifeTime;
        return super.toString() +"\n"+ str;
    }

    @Override
    public void licenseType() {
        this.license = "Unlimited";
    }
}

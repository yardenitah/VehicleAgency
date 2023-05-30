package Vehicless;


public  class Jeep extends LandVehicle implements iLandVehicle, MotorVehicles, Commercial {
    private  float AverageConsumption;
    private final float averageLifeSpan;
    private String license;

    public Jeep(String model, String subModel, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, float AverageConsumption, float averageLifeSpan) {
        super(model, subModel, kilometers, maxKilometers, maxOfPassengers, maxSpeed, true, true, 4);
        this.AverageConsumption = AverageConsumption;
        this.averageLifeSpan = averageLifeSpan;
        this.license = "Mini";
    }

    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof Jeep) {
            Jeep jeep = (Jeep) anObj;
            return jeep.model.equals(this.model) && jeep.subModel.equals(this.subModel) && this.averageLifeSpan == jeep.averageLifeSpan && jeep.kilometers == this.kilometers && jeep.maxKilometers == this.maxKilometers && jeep.maxOfPassengers == this.maxOfPassengers && jeep.maxSpeed == this.maxSpeed;
        }
        return false;
    }

    @Override
    public String toString() {
        String str1 = "Jeep";
        String str2 = ("\n average life span: " + this.averageLifeSpan + "\n Average Consumption: " + this.AverageConsumption + "\n license: " + this.license);
        return str1 + "\n" + super.toString() + "\n" + str2;
    }

    @Override
    public void AverageConsumption(int km, int amountoFgas) {
        if (km != 0)
        this.AverageConsumption =  amountoFgas / km;
    }

    @Override
    public void licenseType() {
        this.license = "Mini";
    }
}

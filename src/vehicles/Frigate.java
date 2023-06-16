package vehicles;

public class Frigate extends WaterVehicle implements iWaterVehicle{

    public Frigate(String model, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, boolean withTheWind) {
        super(model, kilometers, maxKilometers, maxOfPassengers, maxSpeed, withTheWind,"Israel");
        this.lifeTime = 4;
        this.AverageConsumption = 500;
    }
    private final int lifeTime;

    @Override
    public boolean equals(Object anObj) {
       if (anObj instanceof Frigate){
           Frigate frigate = (Frigate) anObj;
           return this.kilometers == frigate.kilometers && this.maxKilometers == frigate.maxKilometers && this.maxSpeed == frigate.maxSpeed && this.AverageConsumption == frigate.AverageConsumption && this.countryFlag.equals(frigate.countryFlag);
       }
       return false;
    }

    @Override
    public String toString() {
        String str2;
        String str = ("Frigate:\n Model:"+this.model+"\n traveled:"+this.kilometers+"\n Average Consumption: "+"\n Max speed:"+this.maxSpeed+" Mph"+"\n can carry max of people:"+this.maxOfPassengers+"\n Country Flag: "+this.countryFlag+"\n life time: "+this.lifeTime+"\n engine: "+this.AverageConsumption+"L");
        if (this.withTheWind)  str2 = "with the wind";
        else  str2 = "against the wind";
        String res = str + "\n " + str2;
        return res;
    }
}
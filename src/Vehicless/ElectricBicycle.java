package Vehicless;

public class ElectricBicycle extends Bicycle implements iLandVehicle, MotorVehicles{
    public ElectricBicycle(String model, String subModel) {
        super(model, subModel, 0, 0,1, 0, true,true,2);
        this.averageConsumption = 20;
    }
    private  float averageConsumption;

    @Override
    public String toString() {
        String str = ("Electric Bicycle:\n model: "+this.model+"\n sub model: "+this.subModel+"\n road type:" +this.roadType());
        return str;
    }


    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof ElectricBicycle){
            ElectricBicycle bicycle = (ElectricBicycle) anObj;
            return super.equals(anObj) && this.averageConsumption == bicycle.averageConsumption;
        }
        return false;
    }

    @Override
    public void AverageConsumption(int km, int amountoFgas) {
        return;
    }
}

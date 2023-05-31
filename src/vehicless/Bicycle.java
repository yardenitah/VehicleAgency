package vehicless;

public class Bicycle extends LandVehicle{
    public Bicycle(String model, String subModel, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, boolean dirt, boolean road, int numOfWheels) {
        super(model, subModel, 0, 0,1, 0, true,true,2);
    }

    @Override
    public String toString() {
        String str = ("Bicycle:\n model: "+this.model+"\n sub model: "+this.subModel+"\n road type:" +this.roadType());
        return str;
    }

    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof Bicycle){
            Bicycle bicycle = (NormalBicycle) anObj;
            return this.model.equals(bicycle.model) && this.subModel.equals(bicycle.subModel);
        }
        return false;
    }
}

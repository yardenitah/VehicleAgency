package vehicles;

public class LandVehicle extends Vehicle implements iLandVehicle {
    protected final int numOfWheels;
    protected final String subModel;
    protected boolean dirt;
    protected boolean road;

    public LandVehicle(String model, String subModel, int kilometers,  int maxKilometers, int maxOfPassengers,  int maxSpeed, boolean dirt,  boolean road, int numOfWheels){
        super(model, kilometers, maxKilometers, maxOfPassengers,  maxSpeed);
        this.dirt = dirt;
        this.road = road;
        this.numOfWheels = numOfWheels;
        this.subModel = subModel;
    }
    public  boolean equals(Object anObj) {
        if (anObj instanceof LandVehicle) {
            Jeep jeep = (Jeep) anObj;
            return jeep.model.equals(this.model) && jeep.subModel.equals(this.subModel) && jeep.kilometers == this.kilometers && jeep.maxKilometers == this.maxKilometers && jeep.maxOfPassengers == this.maxOfPassengers && jeep.maxSpeed == this.maxSpeed;
        }
        return false;
    }
    public  String toString() {
        String str = ("\n Model:"+this.model+"\n Sub Model:"+this.subModel+"\n traveled:"+this.kilometers+" km "+"\n max kilometer:" +this.maxKilometers+"\n Max speed:"+this.maxSpeed+" Mph"+"\n can carry max of people:"+this.maxOfPassengers+"\n road type: "+this.roadType());
        return str+"\n";
    }

    public String roadType() {
        String str;
        if (this.dirt && this.road)
            str = "road and dirt";
        else if (this.dirt)
            str = "dirt";
        else str = "road";
        return str;
    }

}

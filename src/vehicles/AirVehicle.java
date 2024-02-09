package vehicles;

public class AirVehicle extends Vehicle implements iAirVehicle {

    public AirVehicle(String model, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, boolean civilian, boolean military, float averageLifeSpan) {
        super(model, kilometers, maxKilometers, maxOfPassengers, maxSpeed);
        this.civilian = civilian;
        this.military = military;
        this.averageLifeSpan = averageLifeSpan;
    }

    public  boolean equals(Object anObj) {
        return false;
    }
    public  String toString() {
        String str = ("traveled:"+this.kilometers+"\n average life span: "+this.averageLifeSpan+"\n Max speed:"+this.maxSpeed+" Mph"+"\n can carry max of people:"+this.maxOfPassengers);
        return str+"\n";
    }

    protected float averageLifeSpan;
    protected boolean military;
    protected boolean civilian;

    public  String militaryOrCivilian(boolean military, boolean civilian) {
        if (military && civilian)
            return "military and civilian";
        else if (military)
            return "military";

        else return "civilian";
    }
}

package vehicless;

import javax.swing.*;


public  class WaterVehicle extends Vehicle implements iWaterVehicle {
    public WaterVehicle(String model, int kilometers, int maxKilometers, int maxOfPassengers, int maxSpeed, boolean withTheWind, String countryFlag) {
        super(model, kilometers, maxKilometers, maxOfPassengers, maxSpeed);
        this.withTheWind = withTheWind;
        this.countryFlag = countryFlag;
    }

    protected float AverageConsumption;
    protected boolean withTheWind;
    protected String countryFlag;
    private ImageIcon countryFlagImage;
    public static boolean waterVehicleBusy = false;
    public ImageIcon getCountryFlagImage() {
        return this.countryFlagImage;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public boolean equals(Object anObj) {
        if (anObj instanceof WaterVehicle) {
            WaterVehicle waterVehicle = (CruiseShip) anObj;
            return waterVehicle.countryFlag.equals(this.countryFlag)  && waterVehicle.model.equals(this.model) && waterVehicle.kilometers == this.kilometers && waterVehicle.maxKilometers == this.maxKilometers && waterVehicle.maxSpeed == this.maxSpeed && waterVehicle.maxOfPassengers == this.maxOfPassengers;
        }
        return false;
    }

    public  String toString(){
        String str2;
        String str = ("\n Model:"+this.model+"\n traveled:"+this.kilometers+"\n Average Consumption: "+"\n Max speed:"+this.maxSpeed+" Mph"+"\n can carry max of people:"+this.maxOfPassengers+"\n Country Flag: "+this.countryFlag+"\n engine: "+this.AverageConsumption+"L");
        if (this.withTheWind)  str2 = "with the wind";
        else  str2 = "against the wind";
        String res = str + "\n " + str2;
//        String str3 = super.currentStatus();
//        return res+"\n"+str3;
        return res+"\n";
    }


}

package vehicless;

public class NormalBicycle extends Bicycle implements NonMotorizedVehicles { // Cloneable ?

    public NormalBicycle(String model, String subModel){
        super(model, subModel, 0, 0,1, 0, true,true,2);
    }

    @Override
    public String toString() {
        return super.toString()+"\nenergy score: "+this.energyScore();
    }

    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof NormalBicycle){
            NormalBicycle bicycle = (NormalBicycle) anObj;
            return super.equals(anObj) && this.energyScore() == bicycle.energyScore();
        }
        return false;
    }
    @Override
    public char energyScore() {
        return 'A';
    }

//    @Override
//    public NormalBicycle clone() {
//    }
}

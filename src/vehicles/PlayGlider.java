package vehicles;

public class PlayGlider extends AirVehicle implements NonMotorizedVehicles{

    public PlayGlider(int kilometers, int maxKilometers, float averageLifeSpan) {
        super("toy", kilometers, maxKilometers ,0, 10, true, false, averageLifeSpan);
    }

    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof PlayGlider){
            PlayGlider glider = (PlayGlider) anObj;
            return   glider.maxSpeed == this.maxSpeed && glider.kilometers == this.kilometers && glider.energyScore() == this.energyScore() && glider.maxOfPassengers == this.maxOfPassengers && this.model.equals(glider.model);

        }
        return false;
    }

    @Override
    public String toString() {
        String str1 = "\nPlay Glider\nmodel: Play Glider";
        String str2 = "\n energyScore: "+ energyScore();
        return str1+"\n"+super.toString()+"\n"+str2;
    }

    @Override
    public char energyScore() {
        return 'A';
    }
}

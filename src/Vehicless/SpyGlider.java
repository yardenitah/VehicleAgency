package Vehicless;

public class SpyGlider extends AirVehicle implements NonMotorizedVehicles {
    public SpyGlider(int kilometers, int maxKilometers, float averageLifeSpan) {
        super("XXX", kilometers, maxKilometers, 1, 50, true, false, averageLifeSpan);
    }

    @Override
    public boolean equals(Object anObj) {
        if (anObj instanceof SpyGlider) {
            SpyGlider spyGlider = (SpyGlider) anObj;
            return this.kilometers == spyGlider.kilometers && this.maxKilometers == spyGlider.maxKilometers && this.model.equals(spyGlider.model);
        }
        return false;
    }
    @Override
    public String toString() {
        String str1 = "\nSpy Glider";
        String str2 = "\n energyScore: "+ energyScore();
        return str1+"\n"+super.toString()+"\n"+str2;
    }

    @Override
    public char energyScore() {
        return 'C';
    }
}

package graphic;

import utils.Tuple;
import vehicless.HybridPlane;
import vehicless.PlayGlider;
import vehicless.SpyGlider;
import vehicless.Vehicle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirVehiclesFactory implements AbstractFactory{
    @Override
    public Vehicle create(int type, String... args) {
        Vehicle vehicle = null;
        switch (type) {
            case 0 -> { // Play glider
               vehicle = new PlayGlider(Integer.parseInt(args[0]),Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            }
            case 1 -> { // Spay glider
                vehicle = new SpyGlider(Integer.parseInt(args[0]),Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            }
            case 2 -> { // hybridPlain
                vehicle = new HybridPlane(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Boolean.parseBoolean(args[6]), Boolean.parseBoolean(args[7]), Integer.parseInt(args[8]), Integer.parseInt(args[9]), Boolean.parseBoolean(args[10]), args[11]);
            }
        }
        return vehicle;
    }
}

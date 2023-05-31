package graphic;

import utils.Triple;
import utils.Tuple;
import vehicless.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterVehiclesFactory implements AbstractFactory{
    @Override
    public Vehicle create(int type, String... args) {
        Vehicle vehicle = null;
        switch (type) {
            case 0 -> { // Frigate
                vehicle = new Frigate(args[0],  Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),  Integer.parseInt(args[4]), Boolean.parseBoolean(args[5]));
            }
            case 1 -> { // Cruise ship
                vehicle = new CruiseShip(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5], Integer.parseInt(args[6]));
            }
            case 2 -> { // amphibious
                vehicle = new Amphibious(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Boolean.parseBoolean(args[6]), args[7], Integer.parseInt(args[8]));
            }
            case 3 -> { // HybridPlain
                vehicle = new HybridPlane(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Boolean.parseBoolean(args[6]), Boolean.parseBoolean(args[7]), Integer.parseInt(args[8]), Integer.parseInt(args[9]), Boolean.parseBoolean(args[10]), args[11]);
            }
        }
        return vehicle;
    }
}

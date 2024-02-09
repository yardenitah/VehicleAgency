package addVehicle;

import vehicles.*;

public class LandVehiclesFactory implements AbstractFactory {
    @Override
    public  Vehicle create(int type, String... args) {
        Vehicle vehicle = null;
        switch (type) {
            case 0 -> { // Jeep
              vehicle = new Jeep(args[0], args[1],  Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
            }
            case 1 -> { //Bicycle
                vehicle =  new NormalBicycle(args[0], args[1]);
            }
            case 2 -> {  // amphibious
                vehicle = new Amphibious(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Boolean.parseBoolean(args[6]), args[7], Integer.parseInt(args[8]));
            }
            case 3 -> {  // HybridPlain
                vehicle = new HybridPlane(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Boolean.parseBoolean(args[6]), Boolean.parseBoolean(args[7]), Integer.parseInt(args[8]), Integer.parseInt(args[9]), Boolean.parseBoolean(args[10]), args[11]);
            }
            case 4 -> { // ElectricBicycle
                vehicle = new ElectricBicycle(args[0], args[1]);
            }
        }
        return vehicle;
    }
}



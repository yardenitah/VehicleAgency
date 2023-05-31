package graphic;

public class FactoryProvider {
    public static AbstractFactory getFactory(int type){
        AbstractFactory factory = null;
        switch (type) {
            case 0 -> {
                factory =  new LandVehiclesFactory();
            }
            case 1 -> {
                factory =  new WaterVehiclesFactory();
            }
            case 2 -> {
                factory =  new AirVehiclesFactory();
            }
        }
        return factory;
    }
}

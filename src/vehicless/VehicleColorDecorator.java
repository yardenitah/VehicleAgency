package vehicless;

import java.awt.*;

public class VehicleColorDecorator extends VehicleDecorator{
    protected Color color;
    public VehicleColorDecorator(Vehicle vehicle, Color color) {
        super(vehicle);
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object anObj) {
        return getDecoratedVehicle().equals(anObj);
    }

    @Override
    public String toString() {
        return null;
    }
}

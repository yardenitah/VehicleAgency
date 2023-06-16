package vehicles;
import utils.VehicleStatusObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static vehicles.VehicleColorAndStatusDecorator.currentStatus.IN_STOCK;
public class VehicleColorAndStatusDecorator extends VehicleDecorator implements VehicleStatusObserver {
    protected Color color;

    public static enum currentStatus{
        ON_TEST_DRIVE, IN_STOCK, ON_BUYING_PROCESS
    }
    currentStatus status;

    private List<JLabel> tooltipObservable;

    public void setTooltipObservable(JLabel tooltipObservable) {
        this.tooltipObservable.add(tooltipObservable);
    }

    public VehicleColorAndStatusDecorator(Vehicle vehicle, Color color) {
        super(vehicle);
        this.color = color;
        status = IN_STOCK;
        tooltipObservable = new ArrayList<>();
    }

    public currentStatus getStatus() {
        return status;
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
        return getDecoratedVehicle().toString() +"\n Current status at the agency:" +status.name();
    }

    @Override
    public synchronized void updateStatus(currentStatus status) {
        this.status = status;
        tooltipObservable.forEach(lable -> lable.setToolTipText(this.toString()));
        notifyAll();
    }


}

package vehicles;
import utils.Tuple;
import utils.VehicleStatusObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static vehicles.VehicleColorAndStatusDecorator.currentStatus.IN_STOCK;
public class VehicleColorAndStatusDecorator extends VehicleDecorator implements VehicleStatusObserver {
    protected Color color;
    private currentStatus status;

    private final List<JLabel> tooltipObservable;

    public enum currentStatus{
        ON_TEST_DRIVE, IN_STOCK, ON_BUYING_PROCESS
    }


    public VehicleColorAndStatusDecorator(Ivehicle decoratedVehicle, Color color) {
        super(decoratedVehicle);
        this.color = color;
        status = IN_STOCK;
        tooltipObservable = new ArrayList<>();
    }
    public void setTooltipObservable(JLabel tooltipObservable) {
        this.tooltipObservable.add(tooltipObservable);
    }
    public Tuple<Ivehicle, Color> makeVehicleDecorated() {
        return new Tuple<>(this.decoratedVehicle.makeVehicle(), this.color);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return decoratedVehicle.toString() +"\n Current status at the agency:" +status.name();
    }

    @Override
    public ImageIcon getVehiclesImage() {
        return decoratedVehicle.makeVehicle().getVehiclesImage();
    }

    @Override
    public synchronized void updateStatus(currentStatus status) {
        this.status = status;
        tooltipObservable.forEach(lable -> lable.setToolTipText(this.toString()));
        notifyAll();
    }
}

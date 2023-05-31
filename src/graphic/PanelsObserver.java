package graphic;

import vehicless.Vehicle;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public interface PanelsObserver {
    public void update(Vector<Vehicle> VehicleList, List<JPanel> buyPanelList, List<JPanel> inventoryPanelList, List<JPanel> testDrivePanelList);
}

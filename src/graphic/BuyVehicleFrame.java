package graphic;

import vehicles.Ivehicle;
import vehicles.Vehicle;
import utils.CarsObserver;

import javax.swing.*;
import java.util.Vector;

public class BuyVehicleFrame extends JFrame implements CarsObserver {
    @Override
    public void update(Ivehicle vehicle, Vector<Ivehicle> VehicleList, int index) {
        synchronized (getLockObject(vehicle)) {
            System.out.println("The car is ready now");
            int option = JOptionPane.showConfirmDialog(null, "Vehicle is back, Are you sure you want to buy?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                VehicleList.remove(index); // Remove the vehicle from the array
                JOptionPane.showMessageDialog(null, "Vehicle bought successfully"); // Show a message to the user
            } else {
                JOptionPane.showMessageDialog(null, "Please select a vehicle");
            }
        }
    }

    private Object getLockObject(Ivehicle vehicle) {  // Create and return a separate lock object for the given vehicle
        return vehicle;
    }


}

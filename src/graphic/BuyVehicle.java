//package graphic;
//
//import vehicless.Vehicle;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//import java.util.Vector;
//
//public class BuyVehicle {
//    private final JPanel panel;
//    private final BuyVehicleFrame buyVehiclesFrame;
//    private final Vector<Vehicle> vehicleList;
//    private final List<JPanel> buyPanelList;
//    private final List<JPanel> inventoryPanelList;
//    private final List<JPanel> testDrivePanelList;
//    private final UpdatingVehiclesList updatingVehiclesList;
//
//
//    public BuyVehicle(JPanel panel, BuyVehicleFrame buyVehiclesFrame, ButtonGroup buttonGroup, Vector<Vehicle> vehicleList, List<JPanel> buyPanelList, List<JPanel> inventoryPanelList, List<JPanel> testDrivePanelList){
//        this.panel = panel;
//        this.buyVehiclesFrame = buyVehiclesFrame;
//        this.vehicleList = vehicleList;
//        this.buyPanelList = buyPanelList;
//        this.inventoryPanelList = inventoryPanelList;
//        this.testDrivePanelList = testDrivePanelList;
//        this.updatingVehiclesList = new UpdatesListOfVehicles(buyPanelList, inventoryPanelList, testDrivePanelList, vehicleList);
//    }
//
//
//    public JButton buyButton(ButtonGroup buttonGroup) { // need to get -> ButtonModel selectedButton = buttonVehiclesGroup.getSelection();
//        System.out.println("the Tread in buyButton now is:" + Thread.currentThread().getName() + "\n");
//        JButton buyButton = new JButton("Buy Vehicle"); // Create the Buy Vehicle button
//        buyButton.setBounds(670, 500, 105, 65);
//        buyButton.setBackground(Color.white);
//        buyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ButtonModel selectedButton = buttonGroup.getSelection();
//                if (selectedButton == null) {
//                    JOptionPane.showMessageDialog(null, "Please select a vehicle");
//                    return;
//                }
//                String actionCommand = buttonGroup.getSelection().getActionCommand(); // Get the action command of the selected radio button
//                int index = Integer.parseInt(actionCommand); // Convert the action command to an integer index
//                Vehicle vehicle = vehicleList.get(index);
//                vehicle.setOnBuyingProcess(true);
//                if (vehicle.getOnTestDrive()) {
//                    int option = JOptionPane.showConfirmDialog(null, "This vehicle is on a test drive. Would you like to wait for him to return to complete the purchase?", "Confirmation", JOptionPane.YES_NO_OPTION);
//                    if (option == JOptionPane.YES_OPTION) {
//                        vehicle.getCarsObserverList().add(buyVehiclesFrame);
//                    } else JOptionPane.showMessageDialog(null, "select other vehicle");
//                } else {
//                    synchronized (getLockObject(vehicle)) { // Prevents other Threads from accessing this vehicle in the array
//                        new Thread(() -> { // anonymous Tread
//                            int sleepTime = (int) (Math.random() * 5) + 10; // Sleep for a random time between 5 and 10 seconds
//                            try {
//                                Thread.sleep(sleepTime * 1000L);
//                            } catch (InterruptedException ex) {
//                                ex.printStackTrace();
//                            }
//                            // Show confirmation window
//                            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this vehicle?", "Confirmation", JOptionPane.YES_NO_OPTION);
//                            if (option == JOptionPane.YES_OPTION) {
//                                vehicleList.remove(vehicle); // Remove the vehicle from the array
//                                JOptionPane.showMessageDialog(null, "Vehicle bought successfully"); // Show a message to the user
//                                updatingVehiclesList.updatingDataPleaseWait(Thread.currentThread());
//                            }
//                        }).start();
//                    }
//                }
//            }
//        });
//        return buyButton;
//    }
//
//    private Object getLockObject(Vehicle vehicle) {
//        return vehicle;
//    } // Create and return a separate lock object for the given vehicle
//}
